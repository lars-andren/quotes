package com.angular.db;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.error.DocumentAlreadyExistsException;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Handles the insertions and reads from the Couchbase DB.
 * Use <code>getInstance()</code> for access.
 */
public class CouchConnector {

    /**
     * Instance.
     */
    private static final CouchConnector couchConnector = new CouchConnector();

    /**
     *
     * @return
     */
    public static CouchConnector getInstance() {
        return couchConnector;
    }

    static Cluster cluster = CouchbaseCluster.create();
    static Bucket bucket = cluster.openBucket();

    /**
     * Simple synchronous addition of an entry to the database. If there is an entry with tha same name (i.e. ID),
     * this entry will be updated with the quote given as parameter.
     *
     * @param name  the name of the person.
     * @param quote what the person said.
     *
     * @return <code>true</code> if the insertion/update was successful.
     *         <code>false</code> if something went wrong.
     */
    public boolean addDocumentSynch(String name, String quote) {

        JsonDocument loaded = getItem(name);

        if (loaded == null) {
            try {
                bucket.upsert(createJsonDocument(name, quote));
            } catch (DocumentAlreadyExistsException e) {
                return false;
            }
        }
        else {
            loaded.content().put(name, quote);
            bucket.replace(loaded);
        }

        return true;
    }

    /**
     *
     * @return
     */
    public List<String> getAllItems() {

        List<String> allQuotes = new ArrayList<String>();

        ViewResult result = bucket.query(
                ViewQuery
                        .from("nameAndQuote", "name_and_quote")
        );

        for(ViewRow row : result.allRows()){
            allQuotes.add(row.toString());
        }

        return allQuotes;

    }

    /**
     *
     * @param id
     * @return
     */
    private JsonDocument getItem(String id) {

        JsonDocument response = null;
        try {
            response = bucket.get(id);
        } catch (NoSuchElementException e) {
            System.out.println("ERROR: No element with message: "
                    + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Helper method that creates a JsonDocument containing a JsonObject with a "name" and "quote".
     *
     * @param name  name of the person, also the ID of the JsonDocument.
     * @param quote what the person said.
     * @return  a JsonDocument with the <code>name</code> as ID.
     */
    private JsonDocument createJsonDocument(String name, String quote) {

        JsonObject person = JsonObject.empty()
                .put("name", name)
                .put("quote", quote);

        JsonDocument document = JsonDocument.create(name, person);

        return document;
    }

    /**
     * Should be called before application shutdown.
     */
    public void closeCouch() {
        cluster.disconnect();
    }
}