package com.sancore.sancore_app.Controller;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sancore.sancore_app.Persistence.Entities.UserEntity;
import com.sancore.sancore_app.Service.IUserService;

@Controller
public class PasswordResetController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JavaMailSender mailSender;

    // Método para mostrar la página de "Olvidaste tu contraseña"
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgotPassword"; // Página para ingresar el correo
    }

    // Método para manejar el envío del enlace de restablecimiento de contraseña
    @PostMapping("/forgot-password")
    public String handleForgotPassword(@RequestParam("email") String email, Model model) {
        // Validación de email vacío
        if (email == null || email.isEmpty()) {
            model.addAttribute("error", "El correo electrónico no puede estar vacío.");
            return "forgotPassword"; // Vuelve a mostrar el formulario con el error
        }

        Optional<UserEntity> userOptional = userService.findUserByEmail(email);

        if (!userOptional.isPresent()) {
            model.addAttribute("error", "No se encontró un usuario con ese correo electrónico.");
            return "forgotPassword"; // Vuelve a mostrar el formulario con el error
        }

        UserEntity user = userOptional.get(); // Obtiene el usuario si está presente
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String resetUrl = "http://localhost:8080/reset-password?token=" + token; // Asegúrate de usar la URL correcta

        // Enviar el correo de restablecimiento de contraseña
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(email);
        emailMessage.setSubject("Restablecimiento de Contraseña");
        emailMessage.setText("Para restablecer tu contraseña, haz clic en el siguiente enlace:\n" + resetUrl);
        mailSender.send(emailMessage);

        return "redirect:/login?resetPasswordSent"; // Redirige con un mensaje de éxito
    }

    // Método para mostrar la página de restablecimiento de contraseña (donde se
    // ingresa la nueva contraseña)
    @GetMapping("/reset-password")
    public String showChangePasswordPage(@RequestParam("token") String token, Model model) {
        if (token == null || token.isEmpty()) {
            model.addAttribute("error", "El token de restablecimiento de contraseña está ausente.");
            return "resetPassword"; // Si el token falta, muestra un mensaje de error
        }

        // Validar el token
        String result = userService.validatePasswordResetToken(token);
        if (result != null) {
            model.addAttribute("error", "El token de restablecimiento de contraseña es inválido.");
            return "resetPassword"; // Si el token es inválido, muestra un mensaje de error
        }

        model.addAttribute("token", token); // Añadir el token al modelo para la vista
        return "resetPassword"; // Muestra el formulario para cambiar la contraseña
    }

    // Método para guardar la nueva contraseña
    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token, @RequestParam("password") String password,
            Model model) {
        if (password == null || password.isEmpty()) {
            model.addAttribute("error", "La contraseña no puede estar vacía.");
            return "resetPassword"; // Si la contraseña está vacía, vuelve a mostrar el formulario con un error
        }

        UserEntity user = userService.getUserByPasswordResetToken(token);
        if (user == null) {
            model.addAttribute("error", "El token de restablecimiento de contraseña es inválido o ha expirado.");
            return "resetPassword"; // Si el token no es válido o ha expirado, muestra un mensaje de error
        }

        userService.changeUserPassword(user, password); // Cambiar la contraseña
        return "redirect:/login?passwordResetSuccess"; // Redirige a login con éxito
    }
}
