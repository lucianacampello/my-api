package com.myapi.repository;

import com.myapi.model.user.dto.UserListDTO;
import com.myapi.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query("Select new " + UserListDTO.CLASS_PATH + "(u.name, u.email, (SELECT COUNT(*) FROM CreditCard c where " +
            "c.user.id = u.id)) FROM User u ORDER BY u.name")
    List<UserListDTO> findAllCustom();
}
