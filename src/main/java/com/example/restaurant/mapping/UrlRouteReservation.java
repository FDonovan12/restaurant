package com.example.restaurant.mapping;

public interface UrlRouteReservation {

    String URL_RESERVATION = "/reservation";
    String URL_RESERVATION_NEW = URL_RESERVATION + "/new";
    String URL_RESERVATION_EDIT = URL_RESERVATION + "/edit";
    String URL_RESERVATION_DELETE = URL_RESERVATION + "/delete";

    String URL_ADMIN_RESERVATION = "/admin" + URL_RESERVATION;
    String URL_ADMIN_RESERVATION_NEW = URL_ADMIN_RESERVATION + "/new";
    String URL_ADMIN_RESERVATION_EDIT = URL_ADMIN_RESERVATION + "/edit";
    String URL_ADMIN_RESERVATION_DELETE = URL_ADMIN_RESERVATION + "/delete";
}
