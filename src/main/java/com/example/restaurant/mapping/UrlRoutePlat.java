package com.example.restaurant.mapping;

public interface UrlRoutePlat {

    String URL_PLAT = "/plat";
    String URL_PLAT_NEW = URL_PLAT + "/new";
    String URL_PLAT_EDIT = URL_PLAT + "/edit";
    String URL_PLAT_DELETE = URL_PLAT + "/delete";

    String URL_ADMIN_PLAT = "/admin" + URL_PLAT;
    String URL_ADMIN_PLAT_NEW = URL_ADMIN_PLAT + "/new";
    String URL_ADMIN_PLAT_EDIT = URL_ADMIN_PLAT + "/edit";
    String URL_ADMIN_PLAT_DELETE = URL_ADMIN_PLAT + "/delete";
}
