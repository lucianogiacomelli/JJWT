package com.giacomelli.JJWT.Repository;

import com.giacomelli.JJWT.Model.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSecRepository extends JpaRepository<UserSec, Long> {

    Optional<UserSec> findUserEntityByUsername(String username);


}
