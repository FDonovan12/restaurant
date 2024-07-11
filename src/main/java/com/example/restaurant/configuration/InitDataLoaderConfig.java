package com.example.restaurant.configuration;

import com.example.restaurant.entity.Client;
import com.example.restaurant.entity.Plat;
import com.example.restaurant.entity.Reservation;
import com.example.restaurant.repository.ClientRepository;
import com.example.restaurant.repository.PlatRepository;
import com.example.restaurant.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private ClientRepository clientRepository;
    private ReservationRepository reservationRepository;
    private PlatRepository platRepository;

    @Override
    public void run(String... args) {
//        createPlat();
//        createClient();
        System.out.println("end init loader");
    }

    private void createPlat(){
        List<String> platsName = new ArrayList<String>(Arrays.asList(
                "lasagne","boeuf bourguignon"));
        platRepository.saveAllAndFlush(platsName.stream().map(Plat::new).toList());
    }

    private void createClient(){
        List<String> clientsName = new ArrayList<String>(Arrays.asList(
                "Jean","Michel"));
        clientRepository.saveAllAndFlush(clientsName.stream().map(Client::new).toList());
    }
}
