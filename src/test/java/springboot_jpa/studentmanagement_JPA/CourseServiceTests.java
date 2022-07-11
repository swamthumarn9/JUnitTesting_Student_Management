package springboot_jpa.studentmanagement_JPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import springboot_jpa.studentmanagement_JPA.dao.CourseRepository;
import springboot_jpa.studentmanagement_JPA.dao.CourseService;
import springboot_jpa.studentmanagement_JPA.model.Course;
import springboot_jpa.studentmanagement_JPA.model.User;

@SpringBootTest
public class CourseServiceTests {
	@Mock
	CourseRepository courseRepo;
	
	@InjectMocks
	CourseService courseService;
	
	@Test
	public void addCourseTest() {
		Course setcourse=new Course();
		setcourse.setCourseId("COU001");
		setcourse.setCourseName("Java");
		courseService.addCourse(setcourse);
		verify(courseRepo,times(1)).save(setcourse);
	}
	
	@Test
	public void getAllCourseTest() {
		List<Course> courselist=new ArrayList<>();
		Course course1=new Course();
		course1.setCourseId("COU001");
		course1.setCourseName("Java");
		Course course2=new Course();
		course2.setCourseId("COU002");
		course2.setCourseName("PHP");
		Course course3=new Course();
		course3.setCourseId("COU003");
		course3.setCourseName("Python");
		courselist.add(course1);
		courselist.add(course2);
		courselist.add(course3);
		when(courseRepo.findAll()).thenReturn(courselist);
		List<Course> getallcourse=courseService.getAllCourse();
		assertEquals(3, getallcourse.size());
		verify(courseRepo, times(1)).findAll();
	}
	
	
	@Test
	public void getCourseByIdTest() {
		List<Course> courselist=new ArrayList<>();
		Course course1=new Course();
		course1.setCourseId("COU001");
		course1.setCourseName("Java");
		Course course2=new Course();
		course2.setCourseId("COU002");
		course2.setCourseName("PHP");
		Course course3=new Course();
		course3.setCourseId("COU003");
		course3.setCourseName("Python");
		courselist.add(course1);
		courselist.add(course2);
		courselist.add(course3);
		when(courseRepo.findAllByCourseId("COU001")).thenReturn(courselist);
		List<Course> getcourselist=courseService.getCourseById("COU001");
		assertEquals(3,getcourselist.size());
		verify(courseRepo, times(1)).findAllByCourseId("COU001");
	}
	
}
