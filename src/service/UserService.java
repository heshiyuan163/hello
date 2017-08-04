package service;

import java.util.List;

import pojo.User;

public interface UserService {
	
	public User getUserByUsername(String username);
	public List<User> getUserListByAge(Integer age);
	

}
