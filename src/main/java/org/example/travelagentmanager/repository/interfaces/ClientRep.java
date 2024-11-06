package org.example.travelagentmanager.repository.interfaces;

import org.example.travelagentmanager.model.City;
import org.example.travelagentmanager.model.Client;

import java.util.List;

/**
 * @author batal
 * @Date 06.11.2024
 */
public interface ClientRep {
    Client findById(int id);
    List<Client> findAll();
    void save(Client client);
    void update(Client client);
    void deleteById(int id);
}
