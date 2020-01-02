package com.codewindy.mongodb.service;

import java.util.List;

import com.codewindy.mongodb.pojo.User;

public interface UserService {

	public void saveUser(User user);

	public User getUser(String id);

	public List<User> getList();

}
