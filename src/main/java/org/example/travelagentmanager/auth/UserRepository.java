//package org.example.travelagentmanager.auth;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author batal
// * @Date 10.10.2024
// */
//@Repository
//public class UserRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//    public UserRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    // Метод для поиска пользователя по username
//    public Optional<User> getByLogin(String login) {
//        String sql = "SELECT * FROM users WHERE username = ?";
//        try {
//            User user = jdbcTemplate.queryForObject(sql, new Object[]{login}, new UserRowMapper());
//            return Optional.ofNullable(user);
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//    }
//    public List<User> getAll() {
//        String sql = "SELECT * FROM users";
//        return jdbcTemplate.query(sql, new UserRowMapper());
//    }
//    // RowMapper для преобразования строки результата запроса в объект User
//    private static class UserRowMapper implements RowMapper<User> {
//        @Override
//        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new User(
//                    rs.getLong("id"),
//                    rs.getString("username"),
//                    rs.getString("password"),
//                    rs.getString("role")
//            );
//        }
//    }
//}
