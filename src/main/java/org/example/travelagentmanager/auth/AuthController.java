//package org.example.travelagentmanager.auth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
///**
// * @author batal
// * @Date 10.10.2024
// */
//@RestController
//public class AuthController {
//
//    private final AuthService authService;
//    @Autowired
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//    @GetMapping("/api/home")
//    public Map<String, String> home(Authentication authentication) {
//        Map<String, String> response = new HashMap<>();
//
//        if (authentication != null) {
//            String role = authentication.getAuthorities().iterator().next().getAuthority();
//
//            response.put("username", authentication.getName());
//            response.put("role", role);
//
//            switch (role) {
//                case "ROLE_DIRECTOR":
//                    response.put("redirect", "/director/home");
//                    break;
//                case "ROLE_MANAGER":
//                    response.put("redirect", "/manager/home");
//                    break;
//                case "ROLE_CLIENT":
//                    response.put("redirect", "/client/home");
//                    break;
//                default:
//                    response.put("redirect", "/error");
//            }
//        } else {
//            response.put("error", "Unauthorized");
//        }
//
//        return response;
//    }
//
//    @GetMapping("/api/login")
//    public Map<String, String> login() {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "Please log in");
//        return response;
//    }
//
//    @GetMapping("/api/logout")
//    public Map<String, String> logout() {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "Successfully logged out");
//        return response;
//    }
//}
