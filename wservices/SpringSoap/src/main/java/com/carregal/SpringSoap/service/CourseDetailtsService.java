package com.carregal.SpringSoap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.carregal.SpringSoap.beans.Course;
import com.carregal.SpringSoap.beans.Status;
import com.carregal.SpringSoap.exceptions.CourseNullException;

@Service
public class CourseDetailtsService {

	private static Map<Integer, Course> courses;

	static {
		courses = new HashMap<Integer, Course>();
		courses.put(1, new Course(1, "Spring Core", "Introduction to Spring"));
		courses.put(2, new Course(2, "Spring Core Advanced", "A deep dive into Spring"));
		courses.put(3, new Course(3, "Spring Web Services", "Expose and consume services on the web"));
		courses.put(4, new Course(4, "Spring MicroServices", "The new trend in industry"));
		courses.put(5, new Course(5, "Spring Test", "Become a guru in testing techniques"));
		courses.put(6, new Course(6, "Java 8 features", "You need to know the new tools to get the max out of Java"));
	}

	// getDetails
	public Course findById(int id) {

		return courses.get(id);
	}

	// getAllCourses
	public List<Course> findAll() {

		return new ArrayList<>(courses.values());
	}

	// delete
	public Status deleteById(int id) {

		Status status = Status.FAILURE;

		if (courses.remove(id) != null) {
			status = Status.SUCCESS;
		} else {
			throw new CourseNullException("Identificador inválido:" + id);
		}

		return status;
	}

}
