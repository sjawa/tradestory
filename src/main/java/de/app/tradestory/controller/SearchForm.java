package de.app.tradestory.controller;


public class SearchForm {

    private String query;

    public String getQuery() {
        if(query == null){
            return "";
        }
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
