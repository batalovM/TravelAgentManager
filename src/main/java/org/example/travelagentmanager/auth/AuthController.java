package org.example.travelagentmanager.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller("/")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String redirectToLogin(){
        return "/login";
    }
    @PostMapping("/login")
    public String redirectToPages(@RequestParam Map<String,String> input) {
        User user = authService.authenticate(input.get("username"), input.get("password"));
        if (user != null) {
            String role = user.getRole();
            switch (role) {
                case "client" -> {
                    return "/client";
                }
                case "director" -> {
                    return "/director";
                }
                case "manager" -> {
                    return "/manager";
                }
            }
        }
        return "/login"; // неверные данные
    }

}