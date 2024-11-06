package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.Client;
import org.example.travelagentmanager.repository.interfaces.ClientRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author batal
 * @Date 10.10.2024
 */
@Repository
public class ClientRepository implements ClientRep {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final RowMapper<Client> cleintRowMapper = new RowMapper<>() {
        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            Client client = new Client();
            client.setId(rs.getInt("id"));
            client.setLastname(rs.getString("lastname"));
            client.setFirstname(rs.getString("firstname"));
            client.setSurname(rs.getString("surname"));
            client.setDateOfBirth(rs.getDate("dateofbirth"));
            client.setPassportSeries(rs.getString("passportseries"));
            client.setPassportNumber(rs.getString("passportnumber"));
            client.setDateOfIssue(rs.getDate("dateofissue"));
            client.setIssueBy(rs.getString("issueby"));
            client.setPhoto(rs.getString("photo"));
            return client;
        }
    };
    @Override
    public Client findById(int id) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return List.of();
    }

    @Override
    public void save(Client client) {

    }

    @Override
    public void update(Client client) {

    }

    @Override
    public void deleteById(int id) {

    }
}
