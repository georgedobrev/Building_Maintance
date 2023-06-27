package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.PasswordResetToken;
import com.blankfactor.MaintainMe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query(value = "SELECT u.* FROM `user` u " +
            "JOIN password_reset_token prt ON u.id = prt.user_id " +
            "WHERE prt.token = :token", nativeQuery = true)
    User getUserByToken(@Param("token") String token);

    @Query(value = "SELECT prt.* FROM password_reset_token prt " +
            "JOIN `user` u ON prt.user_id = u.id " +
            "WHERE u.email = :email", nativeQuery = true)
    PasswordResetToken getPasswordResetTokenByEmail(@Param("email") String email);


}
