package springboot_jpa.studentmanagement_JPA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import springboot_jpa.studentmanagement_JPA.dao.CourseService;
import springboot_jpa.studentmanagement_JPA.model.Course;





@Controller
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@GetMapping (value="/setupaddcourse")
	public ModelAndView addcourse() {
		return new ModelAndView ("BUD003", "Course", new Course()); 
	}
	
	
	@PostMapping(value="/addcourse")
	public String addCourse(@ModelAttribute("Course") @Validated Course courseBean, BindingResult bs,
			ModelMap model) {
		if(bs.hasErrors()) {
			return "BUD003";
		}
		if(courseBean.getCourseId().equals("") || courseBean.getCourseName().equals("")) {
			model.addAttribute("error", "Fill Blanks!");
			return "BUD003";
			}else {
				Course course=new Course();
				course.setCourseId(courseBean.getCourseId());
				course.setCourseName(courseBean.getCourseName());
				
				courseService.addCourse(course);
				model.addAttribute("success", "Successful Register");
					
				}
				
				return "BUD003";	
			}	
		
}


