package com.giacomelli.JJWT.Service;

import com.giacomelli.JJWT.Model.Permissions;
import com.giacomelli.JJWT.Model.Roles;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    List<Roles> getAllRoles();
    Optional<Roles> findById(Long id);
    Roles createRoles(Roles roles);
    void deleteById(Long id);
    Roles updateRoles(Roles roles);
}
