package org.example.travelagentmanager.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @author batal
 * @Date 11.12.2024
 */
@RestController
public class AuthController {
    private final AuthService authService = new AuthService();

    // Страница для аутентификации (можно создать отдельный JSON-ответ для отправки на фронтенд)
    @GetMapping("/login")
    public ResponseEntity<String> loginPage() {
        return ResponseEntity.ok("Используйте POST-запрос для входа с логином и паролем.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        User user = authService.authenticate(authRequest.getUsername(), authRequest.getPassword());

        if (user != null) {
            // Если аутентификация прошла успешно, проверяем роль и перенаправляем
            String redirectUrl = "/user-dashboard"; // По умолчанию перенаправление для обычного пользователя

            if ("ROLE_ADMIN".equals(user.getRole())) {
                redirectUrl = "/admin-dashboard"; // Для админа
            }

            // Перенаправляем на соответствующий URL в зависимости от роли
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(redirectUrl));
            return ResponseEntity.status(302).headers(headers).build();
        }

        // Если неверный логин/пароль, возвращаем ошибку
        return ResponseEntity.status(401).body("Неверные данные для входа");
    }
}

