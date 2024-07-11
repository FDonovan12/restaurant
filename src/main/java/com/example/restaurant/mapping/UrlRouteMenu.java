package com.example.restaurant.mapping;

public interface UrlRouteMenu {

    String URL_MENU = "/menu";
    String URL_MENU_NEW = URL_MENU + "/new";
    String URL_MENU_EDIT = URL_MENU + "/edit";
    String URL_MENU_DELETE = URL_MENU + "/delete";

    String URL_ADMIN_MENU = "/admin" + URL_MENU;
    String URL_ADMIN_MENU_NEW = URL_ADMIN_MENU + "/new";
    String URL_ADMIN_MENU_EDIT = URL_ADMIN_MENU + "/edit";
    String URL_ADMIN_MENU_DELETE = URL_ADMIN_MENU + "/delete";
}
