package com.ee.cruddemo.repositories;

import com.ee.cruddemo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
