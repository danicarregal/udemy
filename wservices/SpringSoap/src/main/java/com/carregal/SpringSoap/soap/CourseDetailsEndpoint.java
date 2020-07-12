package com.carregal.SpringSoap.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.carregal.SpringSoap.beans.Course;
import com.carregal.SpringSoap.beans.mappers.CourseMapper;
import com.carregal.SpringSoap.beans.mappers.StatusMapper;
import com.carregal.SpringSoap.jaxb.DeleteCourseDetailsRequest;
import com.carregal.SpringSoap.jaxb.DeleteCourseDetailsResponse;
import com.carregal.SpringSoap.jaxb.GetAllCoursesDetailsRequest;
import com.carregal.SpringSoap.jaxb.GetAllCoursesDetailsResponse;
import com.carregal.SpringSoap.jaxb.GetCourseDetailsRequest;
import com.carregal.SpringSoap.jaxb.GetCourseDetailsResponse;
import com.carregal.SpringSoap.service.CourseDetailtsService;

@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailtsService service;

	@Autowired
	CourseMapper courseMapper;

	@Autowired
	StatusMapper statusMapper;

	// method
	// input - GetCourseDetailsRequest
	// output - GetCourseDetailsResponse

	// GetCourseDetailsRequest
	@PayloadRoot(namespace = "http://carregal.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsResquest(@RequestPayload GetCourseDetailsRequest request) {

		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		/*
		 * CourseDetails course = new CourseDetails();
		 * course.setDescription("A good course con JAXB");
		 * course.setId(request.getId()); course.setName("Java SOAP Services");
		 * response.setCourseDetails(course);
		 */
		response.setCourseDetails(courseMapper.courseToCouseDetails(service.findById(request.getId())));
		return response;
	}

	// GetCourseDetailsRequest
	@PayloadRoot(namespace = "http://carregal.com/courses", localPart = "GetAllCoursesDetailsRequest")
	@ResponsePayload
	public GetAllCoursesDetailsResponse processAllCourseDetailsResquest(
			@RequestPayload GetAllCoursesDetailsRequest request) {

		GetAllCoursesDetailsResponse response = new GetAllCoursesDetailsResponse();
		List<Course> courses = service.findAll();
		for (Course course : courses) {
			response.getCourseDetails().add(courseMapper.courseToCouseDetails(course));
		}

		return response;
	}

	// GetCourseDetailsRequest
	@PayloadRoot(namespace = "http://carregal.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsResquest(@RequestPayload DeleteCourseDetailsRequest request) {

		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setResult(statusMapper.statusToOpStatus(service.deleteById(request.getId())));
		return response;
	}

}
