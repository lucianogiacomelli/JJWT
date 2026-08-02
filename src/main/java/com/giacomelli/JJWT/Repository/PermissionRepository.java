package com.giacomelli.JJWT.Repository;

import com.giacomelli.JJWT.Model.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Long> {

}
