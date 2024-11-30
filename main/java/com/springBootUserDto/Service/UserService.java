package com.springBootUserDto.Service;

import java.util.List;

import com.springBootUserDto.Entities.User;
import com.springBootUserDto.UserResponse.UserDto;

public interface UserService {
	public User saveUser(User user);
	public List<UserDto> getAllUsers();
	public UserDto getUserById(Long id);
	public User deleteById(Long id);
	public User updateUser(long id, User user);
}
