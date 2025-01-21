package com.sancore.sancore_app.Persistence.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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

    @NotEmpty(message = "La contraseña es obligatoria")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{8,20}$", message = "La contraseña debe tener al menos un número, una letra minúscula, una letra mayúscula, un carácter especial (incluyendo '.'), y debe tener entre 8 y 20 caracteres sin espacios.")
    private String password;

    public UserRegisterDTO(Long id, String firstName, String lastName, String nick, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nick = nick;
        this.email = email;
        this.password = password;
    }

    public UserRegisterDTO(String firstName, String lastName, String nick, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nick = nick;
        this.email = email;
        this.password = password;
    }

    public UserRegisterDTO(String email) {
        this.email = email;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
