package com.sancore.sancore_app.Persistence.DTO;

import com.sancore.sancore_app.Service.IFieldsMatch;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@IFieldsMatch(field = "email", fieldMatch = "confirmEmail", message = "Los correos electrónicos no coinciden")
@IFieldsMatch(field = "password", fieldMatch = "confirmPassword", message = "Las contraseñas no coinciden")
public class UserRegisterDTO {

    private Long id;

    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 3, max = 20, message = "El usuario debe tener entre 3 y 20 caracteres")
    private String firstName;

    @NotEmpty(message = "El apellido es obligatorio")
    @Size(min = 3, max = 40, message = "El usuario debe tener entre 3 y 40 caracteres")
    private String lastName;

    @NotEmpty(message = "El usuario es obligatorio")
    @Size(min = 3, max = 20, message = "El usuario debe tener entre 3 y 20 caracteres")
    private String nick;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser válido")
    private String email;

    private String confirmEmail;

    @NotEmpty(message = "La contraseña es obligatoria")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{8,20}$", message = "La contraseña debe tener entre 8 y 20 caracteres, al menos un número, una minúscula, una mayúscula, un carácter especial.")
    private String password;

    private String confirmPassword;

    // Constructor principal
    public UserRegisterDTO(Long id, String firstName, String lastName, String nick, String email, String confirmEmail,
            String password, String confirmPassword) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nick = nick;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // Constructor vacío (para que el modelo pueda ser usado por el formulario)
    public UserRegisterDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
