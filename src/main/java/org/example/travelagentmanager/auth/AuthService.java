package org.example.travelagentmanager.auth;/**

 * @author batal

 * @Date 11.12.2024 

 */
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public AuthService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setRole(rs.getString("role"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    public User authenticate(String username, String password) {
        String sql = "select * from users where username = ?";
        try {

            User user = jdbcTemplate.queryForObject(sql, userRowMapper, username);

            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
}
}
