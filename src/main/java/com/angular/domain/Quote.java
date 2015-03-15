package com.angular.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Lars on 2015-03-15.
 */
@XmlRootElement
public class Quote {

    private String quote;
    private String name;

    public Quote() {}

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
