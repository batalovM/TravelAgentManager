//package org.example.travelagentmanager.auth;
//
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Objects;
//import java.util.Optional;
//
//
///**
// * @author batal
// * @Date 10.10.2024
// */
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//    private AuthService service;
//
//    public AuthController(AuthService service) {
//        this.service = service;
//    }
//
//    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody Optional<User> getAuthUser() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth == null) {
//            return Optional.empty();
//        }
//        Object principal = auth.getPrincipal();
//        User user = (principal instanceof User) ? (User) principal : null;
//        return Objects.nonNull(user) ? this.service.findByUsername(user.getLogin()) : Optional.empty();
//    }
//
//}
