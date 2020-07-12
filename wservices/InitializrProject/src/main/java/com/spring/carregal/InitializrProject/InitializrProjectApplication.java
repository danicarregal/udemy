package com.spring.carregal.InitializrProject;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.spring.carregal.InitializrProject.components.Greeter;

@SpringBootApplication
public class InitializrProjectApplication {

	@Autowired
	private Calendar calendario;

	// el motivo por el que no se usa autowired para hacer los ejemplos con la
	// @SpringBootApplication es
	// porque en el método main no se puede hacer uso de instancias de cosas porque
	// es estático
	public static void main(String[] args) {

		ApplicationContext c = SpringApplication.run(InitializrProjectApplication.class, args);

		Calendar hoy = (Calendar) c.getBean("calendario");
		// si fuese una interfaz....

		Greeter greeter = (Greeter) c.getBean("GoodGuy");

		System.out.println("Hoy estamos a " + hoy.getTime());
		System.out.println(greeter.greet());
	}

}
