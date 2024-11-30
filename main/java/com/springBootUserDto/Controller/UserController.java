package com.springBootUserDto.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBootUserDto.Entities.User;
import com.springBootUserDto.Service.UserServiceImpl;
import com.springBootUserDto.UserResponse.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		try {
			User userInfo = this.userServiceImpl.saveUser(user);
			return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUserDto() {
		List<UserDto> userDto = this.userServiceImpl.getAllUsers();
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserByid(@PathVariable long id) {
		UserDto userDto = this.userServiceImpl.getUserById(id);
		if (userDto != null) {
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id) {
		try {
			this.userServiceImpl.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody User user) {
		try {
			User userInfo = this.userServiceImpl.updateUser(id, user);
			return new ResponseEntity<>(userInfo, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred while updating the user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
