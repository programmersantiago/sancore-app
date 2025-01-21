package com.sancore.sancore_app.Persistence.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sancore.sancore_app.Persistence.Entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByNick(String nick);
    Optional<UserEntity> findByNickOrEmail(String nick, String email);

}
