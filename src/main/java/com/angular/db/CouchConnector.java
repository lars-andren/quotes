package com.angular.db;

import com.angular.domain.Quote;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.error.DocumentAlreadyExistsException;
import com.couchbase.client.java.view.Stale;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Handles the insertions and reads from the Couchbase DB.
 *
 * Uses singleton <code>getInstance()</code> for access.
 */
public class CouchConnector {

    private static final CouchConnector couchConnector = new CouchConnector();

    public static CouchConnector getInstance() {
        return couchConnector;
    }

    /* Used for Amazon EC2 deployment tests*/
    //static Cluster cluster = CouchbaseCluster.create("52.16.203.26");
    //static Bucket bucket = cluster.openBucket("nameQuoteBucket");

    /* Default Cluster and Bucket, used for localhost test. */
    static Cluster cluster = CouchbaseCluster.create();
    static Bucket bucket = cluster.openBucket();


    /**
     * Simple synchronous addition of an entry to the database. If there is an entry with the same name (i.e. ID),
     * this entry will be updated with the new quote given in the <code>Quote</code> parameter.
     *
     * @param quote what the person said.
     *
     * @return <code>true</code> if the insertion/update was successful.
     *         <code>false</code> if something went wrong.
     */
    public boolean addQuoteSynch(Quote quote) {

        JsonDocument loaded = getItem(quote.getName());

        if (loaded == null) {
            try {
                bucket.upsert(createJsonDocument(quote));
            } catch (DocumentAlreadyExistsException e) {
                return false;
            }
        }
        else {
            JsonObject obj = loaded.content();
            obj.put("name", quote.getName());
            obj.put("quote", quote.getQuote());

            bucket.replace(loaded);
        }

        return true;
    }

    /**
     * Retrieves all the entries from the view "name_and_quote".
     *
     * NOTE: The query is done with <code>stale=false</code> which will update the view before the query is performed.
     * This will decrease performance.
     *
     * @return all name+quote pairs in the DB.
     */
    public List<Quote> getAllItems() {

        List<Quote> allQuotes = new ArrayList<Quote>();

        ViewResult result = bucket.query(
                ViewQuery
                        .from("nameAndQuote", "name_and_quote").stale(Stale.FALSE)
        );

        for(ViewRow row : result.allRows()){
            Quote quote = new Quote();
            quote.setName(row.key().toString());
            quote.setQuote(row.value().toString());
            allQuotes.add(quote);
        }

        return allQuotes;
    }

    public boolean deleteItem(String id) {
        if(id == null || id == "")
            throw new IllegalArgumentException("Illegal ID to delete");

        JsonDocument removed = bucket.remove(id);

        return (removed != null);
    }

    private JsonDocument getItem(String id) {

        JsonDocument response = null;
        try {
            response = bucket.get(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return response;
    }

    private JsonDocument createJsonDocument(Quote quote) {

        JsonObject jsonQuote = JsonObject.empty()
                .put("name", quote.getName())
                .put("quote", quote.getQuote());

        JsonDocument document = JsonDocument.create(quote.getName(), jsonQuote);

        return document;
    }

    /**
     * Should be called before application shutdown.
     */
    public void closeCouch() {
        cluster.disconnect();
    }
}