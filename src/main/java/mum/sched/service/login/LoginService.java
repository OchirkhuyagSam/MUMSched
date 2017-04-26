package mum.sched.service.login;

import java.util.List;

import mum.sched.model.entity.User;

public interface LoginService {

	List<User> getUsersByUsernameAndPassword(String username, String password);
}
