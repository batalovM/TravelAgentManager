//package org.example.travelagentmanager.auth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import java.util.Optional;
///**
// * @author batal
// * @Date 10.10.2024
// */
//@Service
//public class AuthService implements UserDetailsService{
//    private final AuthRepository userRepository;
//    @Autowired
//    public AuthService(AuthRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);
//        if (user.isEmpty()) {
//            throw new UsernameNotFoundException("Пользователь не найден");
//        }
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRole().substring(5))
//                .build();
//    }
//}
