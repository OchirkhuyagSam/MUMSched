package mum.sched.service.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.sched.model.entity.User;
import mum.sched.repository.user.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getUsersByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

}
