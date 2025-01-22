package com.sancore.sancore_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sancore.sancore_app.Persistence.DTO.UserRegisterDTO;
import com.sancore.sancore_app.Service.IUserService;

@Controller
@RequestMapping("/register")
public class UserRegisterController {

    @Autowired
    private IUserService userService;

    @ModelAttribute("user")
    public UserRegisterDTO returnNewUserRegisterDTO() {
        return new UserRegisterDTO();
    }

    @GetMapping
    public String viewFormForRegister() {
        return "register-form";
    }

    @PostMapping
    public String registerUser(@Validated @ModelAttribute("user") UserRegisterDTO registerDTO, BindingResult result,
            Model model, RedirectAttributes redirectAttributes) {
        // Validación de coincidencia de email y confirmación
        if (!registerDTO.getEmail().equals(registerDTO.getConfirmEmail())) {
            result.rejectValue("confirmEmail", "error.user", "Los correos electrónicos no coinciden");
        }

        // Validación de coincidencia de contraseña y confirmación
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.user", "Las contraseñas no coinciden");
        }

        // Si hay errores de validación, vuelve al formulario
        if (result.hasErrors()) {
            model.addAttribute("status", "error");
            model.addAttribute("message", "Revisa los campos del formulario.");
            return "register-form";
        }

        // Intentar registrar al usuario
        try {
            userService.saveUser(registerDTO);

            // Redirigir al login con el mensaje de éxito
            redirectAttributes.addFlashAttribute("message", "Usuario registrado con éxito.");
            redirectAttributes.addFlashAttribute("status", "success");

            return "redirect:/login"; // Redirige a la página de login
        } catch (Exception e) {
            // Si ocurre un error al guardar el usuario
            model.addAttribute("status", "error");
            model.addAttribute("message", "Error al guardar el usuario: " + e.getMessage());
            return "register-form";
        }
    }

    @GetMapping("/register-form")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "register-form"; // Asegúrate de que la vista 'register.html' exista
    }

    @PostMapping("/login")
    public String login(RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            redirectAttributes.addAttribute("error", "Invalid credentials");
            return "redirect:/login"; // Redirige a la misma página con mensaje de error
        }

        // Redirige al home si la autenticación es exitosa
        return "redirect:/home";
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    public void testAuthentication(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("Autenticación exitosa");
        } catch (Exception e) {
            System.out.println("Error en la autenticación: " + e.getMessage());
        }
    }

}
