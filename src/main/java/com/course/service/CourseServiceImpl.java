package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.course.model.Course;
import com.course.repository.CourseRepository;
@Service
public class CourseServiceImpl implements CourseService{
	@Autowired	
	CourseRepository courseRepository;

	@Override
	public void saveCourse(Course course) {
		courseRepository.save(course);
		
	}

	@Override
	public List<Course> getAllCourse() {
	List<Course>courses=	courseRepository.findAll();
	return courses;
		
	}

	@Override
	public Course getCourseById(Long id) {
		
		  return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

	@Override
	public void deleteCourseById(Long id) {
		courseRepository.deleteById(id);
		
	}

	@Override
	public Page<Course> findPaginated(int page, int size) {
			Pageable pageable=	PageRequest.of(page-1, size);
			return courseRepository.findAll(pageable);
		
	}

}
