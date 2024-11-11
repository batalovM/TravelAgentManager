package org.example.travelagentmanager.interfaces;

import org.example.travelagentmanager.model.Client;

import java.util.List;
import java.util.Optional;

/**
 * @author batal
 * @Date 06.11.2024
 */
public interface ClientRep {
    Optional<Client> findById(int id);
    List<Client> findAll();
    void save(Client client);
    void update(Client client, int id);
    void deleteById(int id);
}
