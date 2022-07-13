package springboot_jpa.studentmanagement_JPA;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import springboot_jpa.studentmanagement_JPA.dao.CourseService;
import springboot_jpa.studentmanagement_JPA.dao.StudentService;
import springboot_jpa.studentmanagement_JPA.model.Course;
import springboot_jpa.studentmanagement_JPA.model.Student;
import springboot_jpa.studentmanagement_JPA.model.User;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	StudentService studentService;

	@MockBean
	CourseService courseService;

	@Test
	public void studentdisplayViewTest() throws Exception {
		studentService.getAllStudents();
		this.mockMvc.perform(get("/students")).andExpect(status().isOk()).andExpect(view().name("STU003"))
				.andExpect(model().attributeExists("student"));
	}

	@Test
	public void studentsearchBlankTest() throws Exception {
		List<Student> studentlist = new ArrayList<>();
		List<Course> courselist = new ArrayList<>();
		Student s1 = new Student();
		s1.setStudentId("STU001");
		s1.setStudentName("Jone");
		s1.setDob("22.11.2008");
		s1.setGender("Male");
		s1.setPhone("09111112232");
		Course c1 = new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courselist.add(c1);
		s1.setCourse(courselist);
		studentlist.add(s1);
		when(studentService.getAllStudents()).thenReturn(studentlist);
		this.mockMvc.perform(post("/searchstudent").param("studentId", "").param("studentName", "").param("course", ""))
				.andExpect(status().isOk()).andExpect(view().name("STU003"))
				.andExpect(model().attributeExists("student"));
	}

	@Test
	public void studentsearchTest() throws Exception {
		List<Student> studentlist = new ArrayList<>();
		List<Course> courselist = new ArrayList<>();
		Student s1 = new Student();
		s1.setStudentId("STU001");
		s1.setStudentName("Jone");
		s1.setDob("22.11.2008");
		s1.setGender("Male");
		s1.setPhone("09111112232");
		Course c1 = new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courselist.add(c1);
		s1.setCourse(courselist);
		studentlist.add(s1);
		when(studentService.getStudentByIdOrNameOrCourse("STU001", "Jone", "Java")).thenReturn(studentlist);
		this.mockMvc
				.perform(post("/searchstudent").param("studentId", "STU001").param("studentName", "Jone")
						.param("course", "Java"))
				.andExpect(status().isOk()).andExpect(view().name("STU003"))
				.andExpect(model().attributeExists("student"));
	}

	@Test
	public void setupaddstudentTest() throws Exception {
		this.mockMvc.perform(get("/setupaddstudent")).andExpect(status().isOk()).andExpect(view().name("STU001"))
				.andExpect(model().attributeExists("student"));
	}

	// testing addstudent method when validated fail
	@Test
	public void addstudentValidateTest() throws Exception {
		this.mockMvc.perform(post("/addstudent")).andExpect(status().isOk()).andExpect(view().name("STU001"))
				.andExpect(model().attributeExists("error"));
	}

	// testing addstudent method when validated pass 
	@Test
	public void addstudentOkTest() throws Exception {
		List<Course> courselist = new ArrayList<>();
		Student s1 = new Student();
		s1.setStudentId("STU001");
		s1.setStudentName("Jone");
		s1.setDob("22.11.2008");
		s1.setGender("Male");
		s1.setPhone("09111112232");
		s1.setEducation("IT Diploma");
		Course c1 = new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courselist.add(c1);
		s1.setCourse(courselist);
		this.mockMvc.perform(post("/addstudent").flashAttr("student", s1))
				.andExpect(status().is(302))
				//.andExpect(model().attributeExists("success"))
				.andExpect(redirectedUrl("/setupaddstudent"));
	}

	@Test
	public void setupupdateStudentTest() throws Exception {
		List<Course> courselist = new ArrayList<>();
		when(courseService.getAllCourse()).thenReturn(courselist);
		when(studentService.getStudentbyStudentId("STU001")).thenReturn(new Student());
		this.mockMvc.perform(get("/setupUpdateStudent").param("studentId", "STU001")).andExpect(status().isOk())
				.andExpect(view().name("STU002")).andExpect(model().attributeExists("student"));
	}

	// testing updatestudent method when validated fail
	@Test
	public void updateStudentValidateTest() throws Exception {
		this.mockMvc.perform(post("/updatestudent")).andExpect(status().isOk())
				.andExpect(model().attributeExists("error"))
				.andExpect(view().name("STU002"));
	}

	// testing updatestudent method when validated pass
	@Test
	public void updateStudentOkTest() throws Exception {
		List<Course> courselist = new ArrayList<>();
		Student s1 = new Student();
		s1.setStudentId("STU001");
		s1.setStudentName("Jone");
		s1.setDob("22.11.2008");
		s1.setGender("Male");
		s1.setPhone("09111112232");
		s1.setEducation("IT Diploma");
		Course c1 = new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courselist.add(c1);
		s1.setCourse(courselist);
		this.mockMvc.perform(post("/updatestudent").flashAttr("student", s1)).andExpect(status().is(302))	
		.andExpect(redirectedUrl("/students"));
	}
	
	
	@Test
	public void deletestudentTest() throws Exception {
		this.mockMvc.perform(get("/deleteStudent").param("studentId", "STU001"))
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("/students"));
	}
}
