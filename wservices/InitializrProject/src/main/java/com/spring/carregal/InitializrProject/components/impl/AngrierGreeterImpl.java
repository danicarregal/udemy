package com.spring.carregal.InitializrProject.components.impl;

import org.springframework.stereotype.Component;

import com.spring.carregal.InitializrProject.components.Greeter;

@Component("BadGuy")
public class AngrierGreeterImpl implements Greeter {

	@Override
	public String greet() {

		return "Se you in hell motherfucker";
	}

}
