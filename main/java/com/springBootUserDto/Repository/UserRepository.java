package com.springBootUserDto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBootUserDto.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
