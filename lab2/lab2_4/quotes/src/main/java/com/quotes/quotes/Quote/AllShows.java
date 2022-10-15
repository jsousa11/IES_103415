package com.quotes.quotes.Quote;

public class AllShows {

    private String name;
    private int id;


    public AllShows(int id, String name) {
        this.name = name;
        this.id = id;
    }


    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }
}

