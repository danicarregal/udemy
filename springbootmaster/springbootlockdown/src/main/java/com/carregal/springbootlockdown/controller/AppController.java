package com.carregal.springbootlockdown.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.carregal.springbootlockdown.dao.TodoDaoService;
import com.carregal.springbootlockdown.entity.Todo;
import com.carregal.springbootlockdown.exception.TodoNotFoundException;

@Controller
@SessionAttributes("name")
public class AppController {

	@InitBinder
	// ver DOC:inicializa org.springframework.web.bind.WebDataBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

//	@Autowired
//	private LoginService loginService;

	@Autowired
	private TodoDaoService todoService;

//	@Autowired
//	private DummyService helloService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLogin(ModelMap model) {

		model.put("name", getLoggedUserName());
		return "welcome";
	}

	/*
	 * @PostMapping("/login") public String login(@RequestParam String
	 * name, @RequestParam String password, ModelMap model) {
	 * 
	 * if (loginService.validUser(name, password)) { model.put("name", name);
	 * model.put("password", password); return "welcome"; } else {
	 * model.put("error", "Usuario o password incorrectos"); return "login"; } }
	 */

	@GetMapping("/list")
	public String manageTodosList(ModelMap model) {

		List<Todo> todos = todoService.findAllTodos(getLoggedUserName());
		model.put("todos", todos);
		return "todos";
	}

	@GetMapping("/post-todo")
	public String showPostTodo(ModelMap model, @RequestParam(required = false) Integer id) {

		if (id == null) {

			model.put("todo", new Todo(0, getLoggedUserName(), "Default Description", new Date(), false));

		} else {

			Todo todo = todoService.findTodoById(id);
			if (todo == null) {

				throw new TodoNotFoundException("No existe el elemento");
			}
			model.put("todo", todo);
		}

		return "post-todo";
	}

	@PostMapping("/post-todo")
	public String postTodo(ModelMap model, @Valid Todo formTodo, BindingResult result) {

		if (result.hasErrors()) {
			// los errores se inyectan solos en el modelo, no los pongo yo
			return "post-todo";
		}

		todoService.saveTodo(formTodo);
		return "redirect:/list";
	}

	@GetMapping("/delete-todo-param")
	public String deleteTodoParam(@RequestParam(name = "id") Integer id) {

		todoService.deleteTodoById(id);
		return "redirect:/list";
	}

	@GetMapping("/throwit")
	public String produceError() {

		throw new TodoNotFoundException("error a propósito");
	}

	@GetMapping("/delete-todo-path/{id}")
	public String deleteTodoPath(@PathVariable Integer id) {

		todoService.deleteTodoById(id);
		return "redirect:/list";
	}

	private String getLoggedUserName() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {

			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
	}

	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response, Exception e) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e.getStackTrace());
		mv.addObject("url", request.getRequestURL());
		mv.setViewName("errorpage");
		return mv;
	}
}
