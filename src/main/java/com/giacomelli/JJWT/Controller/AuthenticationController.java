package com.giacomelli.JJWT.Controller;

import com.giacomelli.JJWT.Model.Dto.AuthLoginRequestDto;
import com.giacomelli.JJWT.Model.Dto.AuthLoginResponseDto;
import com.giacomelli.JJWT.Service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponseDto> login (@RequestBody @Valid AuthLoginRequestDto authLoginRequestDto){
        return new ResponseEntity<>(this.userDetailsService.loginUser(authLoginRequestDto), HttpStatus.OK);
    }

}
