package com.spring.carregal.InitializrProject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootController {

	@GetMapping("/books")
	public List<Book> getAllBooks() {

		List<Book> librosBuenos = new ArrayList<>();
		librosBuenos.add(new Book("Los apuros", "Jose Luis Carregal"));
		return librosBuenos;
	}

}
