package com.sancore.sancore_app.Service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.sancore.sancore_app.Persistence.DTO.UserRegisterDTO;
import com.sancore.sancore_app.Persistence.Entities.UserEntity;

public interface IUserService extends UserDetailsService {

    public UserEntity saveUser(UserRegisterDTO registerDTO) throws Exception;

    void createPasswordResetTokenForUser(UserEntity user, String token);

    String validatePasswordResetToken(String token);

    UserEntity getUserByPasswordResetToken(String token);

    Optional<UserEntity> findUserByEmail(String email);

    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException;

    void changeUserPassword(UserEntity user, String newPassword);

}
