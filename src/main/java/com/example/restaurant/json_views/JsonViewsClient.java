package com.example.restaurant.json_views;

public class JsonViewsClient {

    public interface MinimalClient extends Id, Slug, Name, Reservations {
    }

    public interface Id {
    }
    public interface Slug {
    }

    public interface Name {
    }

    public interface Reservations {
    }
}