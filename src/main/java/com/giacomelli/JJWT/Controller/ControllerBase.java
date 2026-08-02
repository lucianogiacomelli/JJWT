package com.giacomelli.JJWT.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("denyAll()") //Se aplica a todos los endpoints excepto a los PreAuthorize
@RequestMapping("base/")
public class ControllerBase {

    @GetMapping("hi-security")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<String> hello(){
        String hello = "Hello Security";
        return ResponseEntity.ok(hello);
    }

    @GetMapping("no-security")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> noHello(){
        String hello = "Hello No Security";
        return ResponseEntity.ok(hello);
    }




}