package com.sancore.sancore_app.Service.Impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sancore.sancore_app.Persistence.DTO.UserRegisterDTO;
import com.sancore.sancore_app.Persistence.Entities.PasswordResetToken;
import com.sancore.sancore_app.Persistence.Entities.Role;
import com.sancore.sancore_app.Persistence.Entities.UserEntity;
import com.sancore.sancore_app.Persistence.Repository.PasswordResetTokenRepository;
import com.sancore.sancore_app.Persistence.Repository.UserRepository;
import com.sancore.sancore_app.Service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(@Lazy BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity saveUser(UserRegisterDTO registerDTO) {

        // Crear la entidad de usuario
        UserEntity user = new UserEntity(
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getNick(),
                registerDTO.getEmail(),
                passwordEncoder.encode(registerDTO.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));

        // Guardar el usuario en el repositorio
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(identifier)
                .orElseGet(() -> userRepository.findByNick(identifier)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario o contraseña inválidos")));

        return new User(user.getNick(), user.getPassword(), mapGrantedAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapGrantedAuthorities(Collection<Role> roles) {
        return roles.stream()
                .filter(role -> role.getNombre() != null && !role.getNombre().isEmpty())
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public void createPasswordResetTokenForUser(UserEntity user, String token) {
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setToken(token);
        myToken.setUser(user);
        myToken.setExpiryDate(new Date(System.currentTimeMillis() + 3600 * 1000)); // 1 hour expiry
        tokenRepository.save(myToken);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passToken = tokenRepository.findByToken(token);
        if (passToken == null) {
            return "invalidToken";
        }
        if (passToken.getExpiryDate().before(new Date())) {
            return "expired";
        }
        return null;
    }

    @Override
    public UserEntity getUserByPasswordResetToken(String token) {
        return tokenRepository.findByToken(token).getUser();
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void changeUserPassword(UserEntity user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

}
