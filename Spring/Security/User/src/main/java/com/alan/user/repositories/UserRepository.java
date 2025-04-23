package com.alan.user.repositories;

import com.alan.user.model.entity.Role;
import com.alan.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
