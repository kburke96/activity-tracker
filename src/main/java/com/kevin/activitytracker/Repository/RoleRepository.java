package com.kevin.activitytracker.Repository;

import java.util.Optional;

import com.kevin.activitytracker.Model.ERole;
import com.kevin.activitytracker.Model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
