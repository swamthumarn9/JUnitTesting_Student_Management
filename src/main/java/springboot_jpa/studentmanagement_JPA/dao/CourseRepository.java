package springboot_jpa.studentmanagement_JPA.dao;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springboot_jpa.studentmanagement_JPA.model.Course;



@Repository
public interface CourseRepository extends CrudRepository<Course, String>{
	
	List<Course> findAllByCourseId(String id);
	
}
