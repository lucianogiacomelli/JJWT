package com.giacomelli.JJWT.Service;

import com.giacomelli.JJWT.Model.Permissions;
import com.giacomelli.JJWT.Repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permissions> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permissions> findById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permissions createPermission(Permissions permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permissions updatePermission(Permissions permission) {
        return permissionRepository.save(permission);
    }
}
