package com.example.restaurant.mapping;

public interface UrlRouteClient {

    String URL_CLIENT = "/client";
    String URL_CLIENT_NEW = URL_CLIENT + "/new";
    String URL_CLIENT_EDIT = URL_CLIENT + "/edit";
    String URL_CLIENT_DELETE = URL_CLIENT + "/delete";

    String URL_ADMIN_CLIENT = "/admin" + URL_CLIENT;
    String URL_ADMIN_CLIENT_NEW = URL_ADMIN_CLIENT + "/new";
    String URL_ADMIN_CLIENT_EDIT = URL_ADMIN_CLIENT + "/edit";
    String URL_ADMIN_CLIENT_DELETE = URL_ADMIN_CLIENT + "/delete";
}
