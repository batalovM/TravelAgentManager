package org.example.travelagentmanager.service;

import org.example.travelagentmanager.model.User;
import org.example.travelagentmanager.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
/**
 * @author batal
 * @Date 10.10.2024
 */
@Service
public class AuthService implements UserDetailsService {

   private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String login) {
        return userRepository.getByLogin(login);
    }

    public List<User> findAll() {
        return userRepository.getAll();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> u = findByUsername(login);
        User user = u.orElseThrow(() ->
                new UsernameNotFoundException(String.format("User %s is not found", login)));

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                true, // accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                true, // enabled
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())) // Список ролей
        );
    }
}
