package com.carregal.springbootlockdown.dao;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean validUser(String user, String pwd) {

		return "daniel".equals(user) && "dani01".equals(pwd);
	}
}
