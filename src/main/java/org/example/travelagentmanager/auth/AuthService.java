package org.example.travelagentmanager.auth;

import org.springframework.stereotype.Service;

import java.util.*;

import java.util.Optional;
/**
 * @author batal
 * @Date 10.10.2024
 */
//@Service
//public class AuthService implements UserDetailsService {
//
//   private final UserRepository userRepository;
//
//    public AuthService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public Optional<User> findByUsername(String login) {
//        return userRepository.getByLogin(login);
//    }
//
//    public List<User> findAll() {
//        return userRepository.getAll();
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        Optional<User> u = findByUsername(login);
//        User user = u.orElseThrow(() ->
//                new UsernameNotFoundException(String.format("User %s is not found", login)));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getLogin(),
//                user.getPassword(),
//                true, // accountNonExpired
//                true, // credentialsNonExpired
//                true, // accountNonLocked
//                true, // enabled
//                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())) // Список ролей
//        );
//    }
//}
