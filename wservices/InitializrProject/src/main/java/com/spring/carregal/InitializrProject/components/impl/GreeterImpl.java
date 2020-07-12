package com.spring.carregal.InitializrProject.components.impl;

import org.springframework.stereotype.Component;

import com.spring.carregal.InitializrProject.components.Greeter;

@Component("GoodGuy")
public class GreeterImpl implements Greeter {

	@Override
	public String greet() {

		return "hello my friend";
	}

}
