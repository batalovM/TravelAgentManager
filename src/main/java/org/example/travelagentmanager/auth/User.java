package org.example.travelagentmanager.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * @author batal
 * @Date 10.10.2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    
    public User(String username, String password) {
    }

    public User(String admin, String password, String roleAdmin) {
    }
}
