package org.example.travelagentmanager.service;

import org.example.travelagentmanager.model.Client;
import org.example.travelagentmanager.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Client getClientById(int id) {
        return clientRepository.findById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public void updateClient(Client client) {
        clientRepository.update(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}
