package com.course.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.course.model.Course;
import com.course.repository.CourseRepository;
import com.course.service.CourseService;

import java.awt.print.Pageable;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;

@Controller
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@GetMapping("/add")
	public String addCourse(Model model) {
		Course course=new Course();
		model.addAttribute(course);
		return "addCourse";
		
	}
	
	@PostMapping("/save")
	public String saveCourse(Course course) {
		courseService.saveCourse(course);
		return "redirect:/";
	}
	

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable ("id")Long id, Model model) {
	Course  course=	courseService.getCourseById(id);
	model.addAttribute("course", course);
		return "update";
	}
	
	@PostMapping("/update/{id}")
		public String updateCourseById(@PathVariable("id") Long id, 
				@ModelAttribute ("course")Course course) {
	Course existingCourse=courseService.getCourseById(id);
	 existingCourse.setCourseName(course.getCourseName());
	 existingCourse.setTrainerName(course.getTrainerName());
	 existingCourse.setEmail(course.getEmail());
	 courseService.saveCourse(existingCourse);
		
		return "redirect:/";
	}
	 @GetMapping("/delete/{id}")
	    public String deleteCourse(@PathVariable("id") Long id) {
	        courseService.deleteCourseById(id);
	        return "redirect:/";  
	    }
	 @GetMapping("/")
	 public String viewHomePage(Model model,
             @RequestParam(name = "page", defaultValue = "1") int page,
             @RequestParam(name = "size", defaultValue = "5") int size) {
		 Page<Course> coursePage = courseService.findPaginated(page, size);
		 model.addAttribute("coursePage", coursePage);
		 model.addAttribute("currentPage", page);
		 model.addAttribute("totalPages", coursePage.getTotalPages());
		 model.addAttribute("totalItems", coursePage.getTotalElements());
		 return "homePage"; 
}
	 }
