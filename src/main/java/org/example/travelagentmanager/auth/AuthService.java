package org.example.travelagentmanager.auth;

import java.util.HashMap;
import java.util.Map;

/**

 * @author batal

 * @Date 11.12.2024 

 */
public class AuthService{

    private static final Map<String, User> users = new HashMap<>();

    static {
        // Пример добавления пользователей
        users.put("user", new User("user", "password", "ROLE_USER"));
        users.put("admin", new User("admin", "password", "ROLE_ADMIN"));
    }

    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Неверные данные
        }
}
