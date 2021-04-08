package com.example.daterequest.Bean;

public class Book {
    private String  id;
    private String catalog;

    public Book() {
    }



    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", catalog='" + catalog + '\'' +
                '}';
    }
}
