package com.angular.domain;

/**
 * Created by Lars on 2015-03-15.
 */
public class Quote {

    private String quote;
    private String name;

    public Quote(String name, String quote) {

        this.name = name;
        this.quote = quote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }




}
