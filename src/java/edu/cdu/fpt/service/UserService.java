package edu.cdu.fpt.service;

import org.springframework.stereotype.Service;

import edu.cdu.fpt.model.User;

/**
 * services related to user
 * 
 * @author Kai Wang
 * 
 */
@Service
public class UserService {
	/**
	 * login
	 * 
	 * @param user
	 *            , user
	 * @return, is a valid user or not
	 */
	public boolean logIn(User user) {
		String name = user.getName();
		String password = user.getPassword();

		if (name.equals("test") && password.equals("test")) {
			return true;
		} else {
			return false;
		}
	}

}
