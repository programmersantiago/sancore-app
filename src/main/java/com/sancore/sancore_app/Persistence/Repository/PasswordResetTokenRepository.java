package com.sancore.sancore_app.Persistence.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sancore.sancore_app.Persistence.Entities.PasswordResetToken;
import com.sancore.sancore_app.Persistence.Entities.UserEntity;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(UserEntity user);
}
