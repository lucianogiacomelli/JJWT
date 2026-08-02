package com.giacomelli.JJWT.Service;

import com.giacomelli.JJWT.Model.Roles;
import com.giacomelli.JJWT.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IRolService{
    @Autowired
    private RolRepository roleRepository;

    @Override
    public List getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Roles createRoles(Roles role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Roles updateRoles(Roles role) {
        return roleRepository.save(role);
    }
}
