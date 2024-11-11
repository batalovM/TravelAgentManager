package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.Client;
import org.example.travelagentmanager.interfaces.ClientRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    private static final RowMapper<Client> clientRowMapper = new RowMapper<>() {
        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            Client client = new Client();
            client.setId(rs.getInt("id"));
            client.setLastname(rs.getString("lastname"));
            client.setFirstname(rs.getString("firstname"));
            client.setSurname(rs.getString("surname"));
            client.setDateOfBirth(rs.getDate("dateofbirth").toLocalDate());
            client.setPassportSeries(rs.getString("passportseries"));
            client.setPassportNumber(rs.getString("passportnumber"));
            client.setDateOfIssue(rs.getDate("dateofissue").toLocalDate());
            client.setIssueBy(rs.getString("issuedby"));
            client.setPhoto(rs.getString("photo"));
            return client;
        }
    };
    @Override
    public Optional<Client> findById(int id) {
        String sql = "SELECT * FROM clients WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, clientRowMapper, id));
    }

    @Override
    public List<Client> findAll() {
        String sql = "SELECT * FROM clients";
        System.out.println(sql);
        return jdbcTemplate.query(sql, clientRowMapper);
    }

    @Override
    public void save(Client client) {
        String sql = "INSERT INTO clients (lastname, firstname, surname, " +
                "dateofbirth, passportseries, passportnumber, dateofissue, " +
                "issuedby, photo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                client.getLastname(),
                client.getFirstname(),
                client.getSurname(),
                client.getDateOfBirth(),
                client.getPassportSeries(),
                client.getPassportNumber(),
                client.getDateOfIssue(),
                client.getIssueBy(),
                client.getPhoto());
    }

    @Override
    public void update(Client client, int id) {
        String sql = "UPDATE clients SET lastname = ?, firstname = ?, surname = ?, " +
                "dateofbirth = ?, passportseries = ?, passportnumber = ?, dateofissue = ?, issuedby = ?, photo = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                client.getLastname(),
                client.getFirstname(),
                client.getSurname(),
                client.getDateOfBirth(),
                client.getPassportSeries(),
                client.getPassportNumber(),
                client.getDateOfIssue(),
                client.getIssueBy(),
                client.getPhoto(),
                id
        );
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM clients WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
