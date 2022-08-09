package com.module.usermodule.user.repository;

import com.module.usermodule.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);

}
