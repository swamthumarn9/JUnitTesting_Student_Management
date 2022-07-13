package springboot_jpa.studentmanagement_JPA;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import springboot_jpa.studentmanagement_JPA.dao.CourseRepository;
import springboot_jpa.studentmanagement_JPA.dao.CourseService;
import springboot_jpa.studentmanagement_JPA.model.Course;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CourseService courseService;

	@MockBean
	CourseRepository courseRepo;

	@Test
	public void setupaddcourseTest() throws Exception {
		this.mockMvc.perform(get("/setupaddcourse")).andExpect(status().isOk()).andExpect(view().name("BUD003"))
				.andExpect(model().attributeExists("Course"));
	}

	// testing addcourse method when validated fail
	@Test
	public void addCourseValidateTest() throws Exception {
		this.mockMvc.perform(post("/addcourse")).andExpect(status().isOk()).andExpect(view().name("BUD003"))
				.andExpect(model().attributeExists("error"));
	}

	// testing adduser method when validated pass
	@Test
	public void addCourseOkTest() throws Exception {
		Course c1 = new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courseService.addCourse(c1);
		this.mockMvc.perform(post("/addcourse").flashAttr("Course", c1)).andExpect(status().isOk())
				.andExpect(view().name("BUD003")).andExpect(model().attributeExists("success"));
	}

}
