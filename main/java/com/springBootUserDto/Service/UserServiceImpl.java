package com.springBootUserDto.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBootUserDto.Entities.User;
import com.springBootUserDto.Exception.ResourceNotFoundException;
import com.springBootUserDto.Repository.UserRepository;
import com.springBootUserDto.UserResponse.UserDto;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(this::getUserDto).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		if (user.isPresent()) {
			return getUserDto(user.get());
		}
		else {
			throw new ResourceNotFoundException("User", "id", id);
		}
	}

	public UserDto getUserDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public User deleteById(Long id) {
		User user = this.userRepository.findById(id).get();
		this.userRepository.delete(user);
		return user;
	}

	@Override
	public User updateUser(long id, User user) {
		User userInfo = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		userInfo.setFirstName(user.getFirstName());
		userInfo.setLastName(user.getLastName());
		userInfo.setEmail(user.getEmail());
		userInfo.setPassword(user.getPassword());
		userInfo.setParents(user.getParents());
		userInfo.setAddressCode(user.getAddressCode());
		this.userRepository.save(userInfo);
		return userInfo;
	}

}
