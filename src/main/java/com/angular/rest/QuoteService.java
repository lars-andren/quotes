package com.angular.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.angular.domain.Quote;
import com.google.gson.Gson;
import javax.ws.rs.QueryParam;

/**
 * Created by Lars on 2015-03-15.
 */
@Path("/quote")
public class QuoteService {

    /**
     * Returns employee Details.
     */
    @GET
    @Path("/test")
    public Quote getQuoteTest() {
        Gson gson = new Gson();
        //String output = gson.toJson(new Employee(101, "Antony", "Wayne"));

       // String output = gson.toJson(new Quote("NAME", "QUOTE"));

        System.out.println("_____REACHED QUOTE SERVICE");

        Quote quote = new Quote();
        quote.setName("Frank Underwoord");
        quote.setQuote("Go fuck yourslf");
        return quote;
    }

    /**
     * Returns employee Details.
     */
    @GET
    @Path("/{param}")
    public Response getQuoteByName(@PathParam("param") String quoteID) {
        Gson gson = new Gson();
        //String output = gson.toJson(new Employee(101, "Antony", "Wayne"));

        String output = gson.toJson(new Quote());

        System.out.println("_____REACHED qUOTE SERVICE");

        return Response.status(200).entity(output).build();
    }

}
