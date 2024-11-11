package org.example.travelagentmanager.controllers;

import org.example.travelagentmanager.model.Client;
import org.example.travelagentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author batal
 * @Date 10.10.2024
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Получить всех клиентов
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Получить клиента по ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> getClientById(@PathVariable int id) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(client);
    }

    // Добавить нового клиента
    @PostMapping("/addClients")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    // Обновить клиента
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable int id, @RequestBody Client client) {
        clientService.updateClient(client, id);
        return ResponseEntity.ok(client);
    }

    // Удалить клиента
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
