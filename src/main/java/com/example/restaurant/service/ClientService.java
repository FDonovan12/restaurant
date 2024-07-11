package com.example.restaurant.service;

import com.example.restaurant.entity.Client;
import com.example.restaurant.repository.ClientRepository;
import com.example.restaurant.DTO.ClientDTO;
import com.example.restaurant.exception.NotFoundRestaurantException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientService implements DAOServiceInterface<Client> {

    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    public Client getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public Client getObjectById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElseThrow(() -> new NotFoundRestaurantException("Client", "id", id));
    }

    public Client getObjectBySlug(String slug) {
        Optional<Client> optionalClient = clientRepository.findBySlug(slug);
        return optionalClient.orElseThrow(() -> new NotFoundRestaurantException("Client", "slug", slug));
    }

    public Client persist(ClientDTO clientDTO) {
        return persist(clientDTO, null);
    }

    public Client persist(ClientDTO clientDTO, Long id) {
        Client client = new Client();
        if (id != null) {
            client = getObjectById(id);
        }

        return clientRepository.saveAndFlush(client);
    }

    public ClientDTO getDTOById(Long id) {
        Client client = getObjectById(id);
        ClientDTO dto = new ClientDTO();
        // dto.setName(client.getName());
        return dto;
    }
}
