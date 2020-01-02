package com.codewindy.mongodb.service.impl;

import java.util.List;

import com.codewindy.mongodb.pojo.User;
import com.codewindy.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		mongoTemplate.save(user);
	}

	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, User.class);
	}

	@Override
	public List<User> getList() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(User.class, "T_User");
	}

}
