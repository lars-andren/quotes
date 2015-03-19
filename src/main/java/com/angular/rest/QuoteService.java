package com.angular.rest;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.angular.db.CouchConnector;
import com.angular.domain.Quote;
import com.google.gson.Gson;

/**
 * Created by Lars on 2015-03-15.
 */
@Path("/quote")
public class QuoteService {

    @GET
    public Response getAllQuotes() {

        List<Quote> results = CouchConnector.getInstance().getAllItems();
        Gson gson = new Gson();
        String output = gson.toJson(results);

        System.out.println("_____REACHED QUOTE SERVICE________GET ALL");

        return Response.status(200).entity(output).build();
    }

    /**
     * Returns an array of all quotes that have been stored in the database.
     */
    @POST
    @Path("{name}/{quote}")
    public Response postQuote(@PathParam("name") String name, @PathParam("quote") String quote) {

        Gson gson = new Gson();

        Quote quoteEntry = new Quote();
        quoteEntry.setName(name);
        quoteEntry.setQuote(quote);

        boolean added = CouchConnector.getInstance().addQuoteSynch(quoteEntry);

        String output = "";
        if (added) {
            output = "[{\"message\": \"Quote successfully saved in the database.\"}]";
        }
        else {
            output = "[{\"message\": \"Quote was not saved in the database.\"}]";
        }
        return Response.status(200).entity(output).build();
    }
}
