package com.kevin.activitytracker.repository;

import java.util.Optional;

import com.kevin.activitytracker.model.ERole;
import com.kevin.activitytracker.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
