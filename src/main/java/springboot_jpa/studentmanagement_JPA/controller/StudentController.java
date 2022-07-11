package springboot_jpa.studentmanagement_JPA.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springboot_jpa.studentmanagement_JPA.dao.CourseService;
import springboot_jpa.studentmanagement_JPA.dao.StudentService;
import springboot_jpa.studentmanagement_JPA.model.Course;
import springboot_jpa.studentmanagement_JPA.model.Student;




@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	
	@GetMapping (value="/students")
	public ModelAndView displayView(ModelMap model) {	
		
	 	List<Student> studentlist = studentService.getAllStudents();
		
		return new ModelAndView ("STU003", "student", studentlist);
	} 
	
	
	@PostMapping(value="/searchstudent")
	public String studentsearch(@RequestParam("studentId") String studentId,@RequestParam("studentName") String studentName, 
			@RequestParam("course") String course, ModelMap model) {
			String stuId= studentId.isBlank()?"@#$%": studentId;
			String stuName=studentName.isBlank()?"@#$%" :studentName;
			String stuCourse=course.isBlank()?"@#$%" : course;
			List<Student> studentlist = new ArrayList<Student>();
			
			
			if(studentId.isBlank() && studentName.isBlank() && course.isBlank()) {
				studentlist=studentService.getAllStudents();
				model.addAttribute("student", studentlist);
				return "STU003";
			}
			else{
				studentlist=studentService.getStudentByIdOrNameOrCourse(stuId, stuName, stuCourse);
				
				if(studentlist.size()==0) {
					studentlist=studentService.getAllStudents();
					model.addAttribute("student", studentlist);				
					return"STU003";
				}else {
					model.addAttribute("student", studentlist);				
					return"STU003";
				}
				
			}
				
		}
	
	

	@GetMapping(value="/setupaddstudent")
	public ModelAndView addstudent(ModelMap model) {
		model.addAttribute("courselist",courseService.getAllCourse());
		return new ModelAndView ("STU001", "student", new Student());
		
	}
	
	
	@PostMapping(value="/addstudent")
	public String addstudent(@ModelAttribute("student") @Validated Student student, BindingResult bs, 
			ModelMap model) {
		
		
		if(student.getStudentId().equals("") || student.getStudentName().equals("") || student.getDob().equals("") || 
				student.getGender().equals("")||student.getPhone().equals("") || student.getEducation().equals("")) {
			
			model.addAttribute("error", "Fill Blanks!");
			return "STU001";
			}else {
		
				studentService.addStudent(student);
				
				model.addAttribute("success", "Successful Register");
			}
			return "/setupaddstudent";
	}
	
	
	@GetMapping(value="/setupUpdateStudent")
	public  ModelAndView updateUser(@RequestParam ("studentId") String studentId, ModelMap model, HttpSession session) {		
		Student dto = new Student();
		dto.setStudentId(studentId);
		Student student = studentService.getStudentbyStudentId(studentId);
		
		model.addAttribute("list", courseService.getAllCourse());
		return new ModelAndView ("STU002", "student", student);
	}
	
	
	
	@PostMapping(value="/updatestudent")
	public String updateUser(@ModelAttribute("student") @Validated Student student, BindingResult bs,
		ModelMap model) {
		
			if(bs.hasErrors()) {
				return "USR002";
			}
			
			if(student.getStudentId().equals("") || student.getStudentName().equals("") || student.getDob().equals("") || 
					student.getGender().equals("")||student.getPhone().equals("") || student.getEducation().equals("")) {
				
				model.addAttribute("error", "Fill Blanks!");
				return "STU002";
				}else {
					studentService.updateStudent(student);				
					
					model.addAttribute("success", "Successful Update");
					return "redirect:/students";
				}
		}
	
	@RequestMapping(value="/deleteStudent", method=RequestMethod.GET)
	public String deletestudent(@RequestParam ("studentId") String studentId,ModelMap model) {
		
		studentService.deleteStudent(studentId);
		return "redirect:/students";
	}
	
}

