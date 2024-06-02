package com.course.service;

import com.course.model.Course;

import java.awt.print.Pageable;
import java.util.*;

import org.springframework.data.domain.Page;
public interface CourseService {
	
	void saveCourse(Course  course);

	List<Course>getAllCourse();

	Course getCourseById(Long id);

	void deleteCourseById(Long id);

	Page<Course> findPaginated(int page, int size);
	
	
}
