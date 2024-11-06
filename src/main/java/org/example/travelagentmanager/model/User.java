package org.example.travelagentmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @author batal
 * @Date 10.10.2024
 */
@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String password;
    private Set<String> role;

}
