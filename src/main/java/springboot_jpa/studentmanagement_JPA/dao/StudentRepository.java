package springboot_jpa.studentmanagement_JPA.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springboot_jpa.studentmanagement_JPA.model.Student;



@Repository
public interface StudentRepository extends CrudRepository<Student, String>{
	
	List<Student> findAll();
	List<Student> findAllByStudentId(String id);
	
	List<Student> findAllByStudentName(String name);
	
	List<Student> findAllByStudentIdOrStudentName(String id,String name);
	
	List<Student> findDistinctByStudentIdContainingOrStudentNameContainingOrCourse_CourseNameContaining(String studentId, String studentName, String courseName);
	
	Student findByStudentId(String id);
	
	
}
