package com.giacomelli.JJWT.Controller;

import com.giacomelli.JJWT.Model.Permissions;
import com.giacomelli.JJWT.Model.Roles;
import com.giacomelli.JJWT.Service.IPermissionService;
import com.giacomelli.JJWT.Service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private IRolService roleService;

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/")
    public ResponseEntity<List<Roles>> getAllRoles() {
        List<Roles> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roles> getRoleById(@PathVariable Long id) {
        Optional<Roles> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity createRole(@RequestBody Roles role) {
        Set<Permissions> permissionList = new HashSet<>();
        Permissions readPermission;

        // Recuperar la Permission/s por su ID
        for (Permissions per : role.getPermissionSet()) {
            readPermission = permissionService.findById(per.getId()).orElse(null);
            if (readPermission != null) {
                //si encuentro, guardo en la lista
                permissionList.add(readPermission);
            }
        }

        role.setPermissionSet(permissionList);
        Roles newRole = roleService.createRoles(role);
        return ResponseEntity.ok(newRole);
    }
}
