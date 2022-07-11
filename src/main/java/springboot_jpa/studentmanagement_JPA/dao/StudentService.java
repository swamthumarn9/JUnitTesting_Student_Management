package springboot_jpa.studentmanagement_JPA.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot_jpa.studentmanagement_JPA.model.Student;
import springboot_jpa.studentmanagement_JPA.model.User;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	// Search
		public List<Student> getAllStudents() {
			/*
			 * List<Student> Studentlist = (List<Student>) return Studentlist;
			 */
			return studentRepository.findAll();
		}

		public List<Student> getStudentById(String Id) {
			return studentRepository.findAllByStudentId(Id);
		}

		public List<Student> getStudentByName(String name) {
			return studentRepository.findAllByStudentName(name);
		}

		public List<Student> getStudentByIdOrNameOrCourse(String Id, String name, String course) {
			return studentRepository.findDistinctByStudentIdContainingOrStudentNameContainingOrCourse_CourseNameContaining(Id, name, course);
		}
		
		
		//Add
		public void addStudent(Student student){
			studentRepository.save(student); 
		}
		
		
		//Update
		 public Student getStudentbyStudentId(String id) {
			 return studentRepository.findByStudentId(id);
		 }
		
		public void updateStudent(Student student) {
			studentRepository.save(student);
		}
		
		
		//Delete
		public void deleteStudent(String id) {
			studentRepository.deleteById(id);
		}
		

}
