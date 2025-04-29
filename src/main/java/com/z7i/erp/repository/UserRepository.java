package com.z7i.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.z7i.erp.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByUsernameAndEmail(String username, String email);
    Optional<Users> findByUsernameOrEmail(String username, String email);
}
