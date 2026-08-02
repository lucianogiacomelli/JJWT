package com.giacomelli.JJWT.Service;

import com.giacomelli.JJWT.Model.UserSec;
import com.giacomelli.JJWT.Repository.UserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSecService implements IUserSecService {
    @Autowired
    private UserSecRepository userSecRepository;

    @Override
    public List findAll() {
        return userSecRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return userSecRepository.findById(id);
    }

    @Override
    public UserSec save(UserSec userSec) {
        return userSecRepository.save(userSec);
    }

    @Override
    public void deleteById(Long id) {
        userSecRepository.deleteById(id);
    }

    @Override
    public void update(UserSec userSec) {
        userSecRepository.save(userSec);
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
