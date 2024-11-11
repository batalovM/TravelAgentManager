package org.example.travelagentmanager.service;

import org.example.travelagentmanager.model.Client;
import org.example.travelagentmanager.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

import java.util.List;

/**
 * @author batal
 * @Date 10.10.2024
 */
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public Optional<Client> getClientById(int id) {
        return clientRepository.findById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void addClient(Client client) {
        clientRepository.save(client);
    }
    public void updateClient(Client client, int id) {
        Optional<Client> existingClient = clientRepository.findById(id);  // Проверяем, существует ли клиент
        if (existingClient.isPresent()) {
            clientRepository.update(client, id);  // Если клиент найден, обновляем его
        } else {
            throw new ClientNotFoundException(String.format("Клиент с ID : %d не найден", id));  // Если клиент не найден, выбрасываем исключение
        }
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ClientNotFoundException extends RuntimeException {
        public ClientNotFoundException(String message) {
            super(message);
        }
    }


    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}
