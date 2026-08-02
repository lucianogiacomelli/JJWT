package com.giacomelli.JJWT.Controller;


import com.giacomelli.JJWT.Model.Permissions;
import com.giacomelli.JJWT.Service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/")
    public ResponseEntity<List<Permissions>> getAllPermissions(){
        List<Permissions> permissionList = permissionService.getAllPermissions();
        return ResponseEntity.ok(permissionList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permissions> getPermissionById(@PathVariable Long id){
        Optional<Permissions> permission = permissionService.findById(id);
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        //Si tiene un objeto dentro del optional devuelve 200, sino 404
    }

    @PostMapping("/create")
    public ResponseEntity<Permissions> createPermissions(@RequestBody Permissions permissions){
        Permissions newPermission = permissionService.createPermission(permissions);
        return ResponseEntity.ok(newPermission);
    }
}
