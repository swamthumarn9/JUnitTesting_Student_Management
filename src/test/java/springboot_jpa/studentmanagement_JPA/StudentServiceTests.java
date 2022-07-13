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

import springboot_jpa.studentmanagement_JPA.dao.StudentRepository;
import springboot_jpa.studentmanagement_JPA.dao.StudentService;
import springboot_jpa.studentmanagement_JPA.model.Course;
import springboot_jpa.studentmanagement_JPA.model.Student;
import springboot_jpa.studentmanagement_JPA.model.User;

@SpringBootTest
public class StudentServiceTests {

	@Mock
	StudentRepository studentRepo;
	
	@InjectMocks
	StudentService studentService;
	
	@Test
	public void getAllStudentsTest() {
		List<Student> studentlist=new ArrayList<>();
		List<Course> courselist=new ArrayList<>();
		Student s1=new Student();
		s1.setStudentId("STU001");
		s1.setStudentName("Jone");
		s1.setDob("13.09.2008");
		s1.setGender("Female");
		s1.setPhone("09111112232");		
		Course c1=new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courselist.add(c1);
		s1.setCourse(courselist);
		
		Student s2=new Student();
		s2.setStudentId("STU001");
		s2.setStudentName("Ryan");
		s2.setDob("22.11.2008");
		s2.setGender("Male");
		s2.setPhone("09111112232");		
		Course c2=new Course();
		c2.setCourseId("COU002");
		c2.setCourseName("Python");
		courselist.add(c2);
		s1.setCourse(courselist);		
		studentlist.add(s1);
		studentlist.add(s2);
		when(studentRepo.findAll()).thenReturn(studentlist);
		List<Student> getallstudent=studentService.getAllStudents();
		assertEquals(2, getallstudent.size());
		verify(studentRepo, times(1)).findAll();		
	}
	
	@Test
	public void getStudentByIdTest() {
		List<Student> studentlist=new ArrayList<>();
		List<Course> courselist=new ArrayList<>();
		Student s1=new Student();
		s1.setStudentId("STU001");
		s1.setStudentName("Jone");
		s1.setDob("22.11.2008");
		s1.setGender("Male");
		s1.setPhone("09111112232");		
		Course c1=new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courselist.add(c1);
		s1.setCourse(courselist);
		studentlist.add(s1);
		when(studentRepo.findAllByStudentId("STU001")).thenReturn(studentlist);
		List<Student> getStudent=studentService.getStudentById("STU001");
		assertEquals(1, getStudent.size());
		verify(studentRepo, times(1)).findAllByStudentId("STU001");
	}
	
	@Test
	public void getStudentByNameTest() {
		List<Student> studentlist=new ArrayList<>();
		List<Course> courselist=new ArrayList<>();
		Student s1=new Student();
		s1.setStudentId("STU001");
		s1.setStudentName("Jone");
		s1.setDob("22.11.2008");
		s1.setGender("Male");
		s1.setPhone("09111112232");		
		Course c1=new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courselist.add(c1);
		s1.setCourse(courselist);
		studentlist.add(s1);
		when(studentRepo.findAllByStudentName("Jone")).thenReturn(studentlist);
		List<Student> getStudent=studentService.getStudentByName("Jone");
		assertEquals(1, getStudent.size());
		verify(studentRepo, times(1)).findAllByStudentName("Jone");
	}
	
	@Test
	public void getStudentByIdOrNameOrCourseTest() {
		List<Student> studentlist=new ArrayList<>();
		List<Course> courselist=new ArrayList<>();
		Student s1=new Student();
		s1.setStudentId("STU001");
		s1.setStudentName("Jone");
		s1.setDob("13.09.2008");
		s1.setGender("Female");
		s1.setPhone("09111112232");		
		Course c1=new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courselist.add(c1);
		s1.setCourse(courselist);
		Student s2=new Student();
		s2.setStudentId("STU001");
		s2.setStudentName("Ryan");
		s2.setDob("22.11.2008");
		s2.setGender("Male");
		s2.setPhone("09111112232");		
		Course c2=new Course();
		c2.setCourseId("COU002");
		c2.setCourseName("Python");
		courselist.add(c2);
		s1.setCourse(courselist);		
		studentlist.add(s1);
		studentlist.add(s2);
		when(studentRepo.findDistinctByStudentIdContainingOrStudentNameContainingOrCourse_CourseNameContaining("STU001","Ryan","Java")).thenReturn(studentlist);
		List<Student> getallstudent=studentService.getStudentByIdOrNameOrCourse("STU001","Ryan","Java");
		assertEquals(2, getallstudent.size());
		verify(studentRepo, times(1)).findDistinctByStudentIdContainingOrStudentNameContainingOrCourse_CourseNameContaining("STU001","Ryan","Java");
	}
	
	@Test
	public void addStudentTest() {	
		Student s1=new Student();
		s1.setStudentId("STU001");
		s1.setStudentName("Jone");
		s1.setDob("22.11.2008");
		s1.setGender("Male");
		s1.setPhone("09111112232");
		List<Course> courselist=new ArrayList<>();
		Course c1=new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courselist.add(c1);
		s1.setCourse(courselist);
		studentService.addStudent(s1);
		verify(studentRepo,times(1)).save(s1);
	}
	
	@Test
	public void getStudentbyStudentIdTest() {
		List<Course> courselist=new ArrayList<>();
		Student s1=new Student();
		s1.setStudentId("STU001");
		s1.setStudentName("Jone");
		s1.setDob("22.11.2008");
		s1.setGender("Male");
		s1.setPhone("09111112232");		
		Course c1=new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courselist.add(c1);
		s1.setCourse(courselist);
		when(studentRepo.findByStudentId("STU001")).thenReturn(s1);
		Student getStudent=studentService.getStudentbyStudentId("STU001");		
		assertEquals("STU001",getStudent.getStudentId());
		assertEquals("Jone",getStudent.getStudentName());
		assertEquals("22.11.2008",getStudent.getDob());
		assertEquals("Male", getStudent.getGender());
		assertEquals("09111112232", getStudent.getPhone());
		assertEquals(courselist, getStudent.getCourse());
		verify(studentRepo, times(1)).findByStudentId("STU001");
	}
	
	@Test
	public void updateStudentTest() {
		List<Course> courselist=new ArrayList<>();
		Student s1=new Student();
		s1.setStudentId("STU001");
		s1.setStudentName("Jone");
		s1.setDob("22.11.2008");
		s1.setGender("Male");
		s1.setPhone("09111112232");		
		Course c1=new Course();
		c1.setCourseId("COU001");
		c1.setCourseName("Java");
		courselist.add(c1);
		s1.setCourse(courselist);
		studentService.updateStudent(s1);
		verify(studentRepo, times(1)).save(s1);
	}
	
	@Test
	public void deleteStudentTest() {
		studentService.deleteStudent("USR001");
		verify(studentRepo,times(1)).deleteById("USR001");
	}
}

