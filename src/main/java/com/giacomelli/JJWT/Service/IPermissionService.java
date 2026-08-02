package com.giacomelli.JJWT.Service;


import com.giacomelli.JJWT.Model.Permissions;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    List<Permissions> getAllPermissions();
    Optional<Permissions> findById(Long id);
    Permissions createPermission(Permissions permission);
    void deleteById(Long id);
    Permissions updatePermission(Permissions permission);

}
