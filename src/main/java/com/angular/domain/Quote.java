package com.angular.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Lars on 2015-03-15.
 *
 * Originally going to return these instances form the <code>QuoteService</code>, but will save that for later.
 * (needs to handle the XML-filtering somehow..)
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
