package com.quotes.quotes.Quote;

public class Show {

    private String name;
    private int id;


    public Show(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

}