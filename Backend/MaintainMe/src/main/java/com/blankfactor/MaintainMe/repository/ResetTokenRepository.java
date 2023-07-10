package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.PasswordResetToken;
import com.blankfactor.MaintainMe.entity.User;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query(value = "SELECT u.id FROM `user` u " +
            "JOIN password_reset_token prt ON u.id = prt.user_id " +
            "WHERE prt.token = :token", nativeQuery = true)
    Long getUserIdByToken(@Param("token") String token);


    @Query(value = "SELECT prt.* FROM password_reset_token prt " +
            "JOIN `user` u ON prt.user_id = u.id " +
            "WHERE u.email = :email", nativeQuery = true)
    PasswordResetToken getPasswordResetTokenByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "DELETE FROM password_reset_token WHERE user_id = :userId", nativeQuery = true)
    void deleteAllByUser_Id(@Param("userId") Long userId);

}
