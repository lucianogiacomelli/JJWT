package com.giacomelli.JJWT.Service;

import com.giacomelli.JJWT.Model.UserSec;

import java.util.List;
import java.util.Optional;

public interface IUserSecService {
    public List findAll();
    public Optional findById(Long id);
    public UserSec save(UserSec userSec);
    public void deleteById(Long id);
    public void update(UserSec userSec);
    public String encriptPassword(String password);
}
