package org.example.travelagentmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author batal
 * @Date 10.10.2024
 */
@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String password;
    private String role;

}
