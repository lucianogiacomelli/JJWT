package com.giacomelli.JJWT.Service;

import com.giacomelli.JJWT.Model.Dto.AuthLoginRequestDto;
import com.giacomelli.JJWT.Model.Dto.AuthLoginResponseDto;
import com.giacomelli.JJWT.Model.UserSec;
import com.giacomelli.JJWT.Repository.UserSecRepository;
import com.giacomelli.JJWT.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserSecRepository userSecRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserSec userSec = userSecRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user with username: "+username));


        // SimpleGrantedAuthority es la clase que Spring Security usa para manejar permisos
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userSec.getRolesList().stream()
                .forEach(role ->
                        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));
        /*
        Al poner delante "ROLE_", Spring Security reconoce que es un Rol automaticamente.
        Si no se pusiera estaríamos guardando un permiso mas
         */
        userSec.getRolesList().stream()
                .flatMap( role -> role.getPermissionSet().stream())
                .forEach( permissions -> authorityList.add(new SimpleGrantedAuthority(permissions.getPermissionName())));

        return new User(
                userSec.getUsername(),
                userSec.getPassword(),
                userSec.isEnabled(),
                userSec.isAccountNonExpired(),
                userSec.isCredentialsNonExpired(),
                userSec.isAccountNonLocked(),
                authorityList);
    }

    public AuthLoginResponseDto loginUser(AuthLoginRequestDto authLoginRequestDto){
        String username = authLoginRequestDto.username();
        String password = authLoginRequestDto.password();

        Authentication authentication = this.authenticate (username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accesToken = jwtUtils.createToken(authentication);

        AuthLoginResponseDto authLoginResponseDto = new AuthLoginResponseDto(username, "Login OK", accesToken, true );
        return authLoginResponseDto;
    }

    public Authentication authenticate (String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid Username or Password");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Username or password");
        }
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }




}
