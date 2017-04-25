package mum.sched.login.service;

import java.util.List;

import mum.sched.entity.model.User;

public interface LoginService {
	List<User> getUsersByUsernameAndPassword(String username, String password);
}
