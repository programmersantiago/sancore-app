package com.sancore.sancore_app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sancore.sancore_app.Persistence.DTO.UserRegisterDTO;

@Controller
public class RegisterController {

    @GetMapping("/register-form")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "register"; // Asegúrate de tener una vista 'register.html' en
                           // 'src/main/resources/templates'
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserRegisterDTO()); // Usa UserRegisterDTO si es lo que necesitas
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    
}
