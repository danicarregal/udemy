package com.carregal.springbootlockdown.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.carregal.springbootlockdown.entity.Todo;

@Repository
public class TodoDaoService {

	private static Map<String, List<Todo>> allTodos = new HashMap<>();

	private static int lastTodoId = 9;

	static {
		allTodos.put("daniel", new ArrayList<>());
		allTodos.put("danilo", new ArrayList<>());
		List<Todo> list1 = allTodos.get("daniel");
		List<Todo> list2 = allTodos.get("danilo");

		list1.add(new Todo(1, "daniel", "Aprende SpringBoot", new Date(), false));
		list1.add(new Todo(2, "daniel", "Aprende Autocontrol", new Date(), false));
		list1.add(new Todo(3, "daniel", "Aprende XML", new Date(), false));
		list2.add(new Todo(4, "danilo", "Aprende JSON", new Date(), false));
		list1.add(new Todo(5, "daniel", "Aprende youtube", new Date(), false));
		list2.add(new Todo(6, "danilo", "Aprende C++", new Date(), false));
		list1.add(new Todo(7, "daniel", "Aprende C#", new Date(), false));
		list2.add(new Todo(8, "danilo", "Aprende Docker", new Date(), false));
		list1.add(new Todo(9, "daniel", "Aprende Kubernetes", new Date(), false));
	}

	public List<Todo> findAllTodos(String user) {

		return allTodos.get(user);
	}

	public Todo findTodoById(Integer id) {

		Todo toret = null;
		for (List<Todo> list : allTodos.values()) {
			for (Todo todo : list) {
				if (todo.getId() == id) {
					toret = todo;
					break;
				}
			}
		}

		return toret;
	}

	private void insertNewTodo(Todo u) {

		u.setId(++lastTodoId);// grosero para pruebas
		if (allTodos.get(u.getUser()) == null) {
			allTodos.put(u.getUser(), new ArrayList<Todo>());
		}
		allTodos.get(u.getUser()).add(u);
	}

	public Todo saveTodo(Todo u) {

		if (u.getId() == 0) {

			insertNewTodo(u);

		} else {

			Todo existingTodo = findTodoById(u.getId());

			if (existingTodo == null) {

				insertNewTodo(u);

			} else {

				existingTodo.setDesc(u.getDesc());
				existingTodo.setTargetDate(u.getTargetDate());
				existingTodo.setDone(u.isDone());
				existingTodo.setUser(u.getUser());
			}
		}

		return u;
	}

	public Todo deleteTodoById(Integer TodoId) {

		Todo todo = findTodoById(TodoId);
		List<Todo> list = allTodos.get(todo.getUser());

		Iterator<Todo> TodoIterator = list.iterator();
		Todo Todo = null;
		while (TodoIterator.hasNext()) {
			Todo = TodoIterator.next();
			if (Todo.getId() == TodoId) {
				TodoIterator.remove();
			}
		}
		return Todo;
	}

}
