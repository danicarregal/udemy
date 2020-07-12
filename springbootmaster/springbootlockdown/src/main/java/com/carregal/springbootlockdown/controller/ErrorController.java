package com.carregal.springbootlockdown.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {

	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response, Exception e) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e.getStackTrace());
		mv.addObject("url", request.getRequestURL());
		mv.setViewName("errorpage");
		return mv;
	}

}
