package com.carregal.SpringSoap.beans.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.carregal.SpringSoap.beans.Course;
import com.carregal.SpringSoap.jaxb.CourseDetails;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	CourseDetails courseToCouseDetails(Course course);

	List<CourseDetails> courseToCouseDetails(List<Course> courses);
}
