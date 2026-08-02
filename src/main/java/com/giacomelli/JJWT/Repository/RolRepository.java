package com.giacomelli.JJWT.Repository;

import com.giacomelli.JJWT.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Roles, Long> {
}
