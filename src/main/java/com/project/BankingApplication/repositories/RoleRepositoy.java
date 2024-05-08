package com.project.BankingApplication.repositories;

import com.project.BankingApplication.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositoy extends JpaRepository<Role, Long> {
}
