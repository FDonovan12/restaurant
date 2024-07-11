package com.example.restaurant.json_views;

import com.example.restaurant.json_views.JsonViewsClient.*;
import com.example.restaurant.json_views.JsonViewsPlat.*;
import com.example.restaurant.json_views.JsonViewsReservation.*;

public class JsonViews {

    public interface PlatListJsonViews extends MinimalPlat { }
    public interface PlatShowJsonViews extends MinimalPlat { }

    public interface ClientListJsonViews extends MinimalClient { }
    public interface ClientShowJsonViews extends MinimalClient { }

    public interface ReservationListJsonViews extends MinimalReservation { }
    public interface ReservationShowJsonViews extends MinimalReservation { }
}
