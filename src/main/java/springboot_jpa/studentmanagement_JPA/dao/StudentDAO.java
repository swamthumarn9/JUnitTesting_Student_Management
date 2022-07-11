package springboot_jpa.studentmanagement_JPA.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


/*
@Repository
public class StudentDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	public int insertStudent(StudentRequestDTO studentdto) {
		int result=0;
		String sql="insert into students (student_id, student_name, dob, "
				+ "gender, phone, education) values (?,?,?,?,?,?)";
		result=jdbcTemplate.update(sql,
				studentdto.getStudentId(),studentdto.getStudentName(),studentdto.getDob(),
				studentdto.getGender(),studentdto.getPhone(), studentdto.getEducation());
		return result;
	}
	
	public int addCourseForStrudent(String student_id, String course_id) {
		int result=0;
		String sql = "insert into student_course (student_id, course_id) values(?, ?)";
		result=jdbcTemplate.update(sql,student_id,course_id);
		return result;
	}
	
	public int updateStudent(StudentRequestDTO studentdto) {
		int result=0;
		String sql="update students set student_name=?, dob=?, gender=?, phone=?, education=? where student_id=?";
		
		result=jdbcTemplate.update(sql,
				studentdto.getStudentName(),studentdto.getDob(),
				studentdto.getGender(),studentdto.getPhone(), studentdto.getEducation(), studentdto.getStudentId());
		return result;
	}
	
	public int updateStudentCourse(StudentRequestDTO studentdto) {
		int result=0;
		String sql="update student_course set student_id=?, course_id? where student_id=?";
		
		result=jdbcTemplate.update(sql,
							studentdto.getStudentId(),
							studentdto.getCourse(),
				studentdto.getStudentId());
		return result;
	}
	
	
	public int deleteStudent(StudentRequestDTO studentdto) {
		int result=0;
		String sql="delete from students where student_id=?";
		result=jdbcTemplate.update(sql,	studentdto.getStudentId());
		return result;
	}
	
	public int deleteStudentCourseByStudentId(String student_id) {
		int result=0;
		String sql="delete from student_course where student_id=?";
		result=jdbcTemplate.update(sql,student_id);
		return result;
	}
	
	
	//DDL
	public StudentResponseDTO selectOne(StudentRequestDTO studentdto) {
		
		String sql="select * from students where student_id=?";
		return jdbcTemplate.queryForObject(sql,
				(rs,rowNum)->
					new StudentResponseDTO(rs.getString("student_id"),
							rs.getString("student_name"),
							rs.getString("dob"),
							rs.getString("gender"),
							rs.getString("phone"),
							rs.getString("education")),							
					studentdto.getStudentId());
	}
	
	
	public StudentResponseDTO selectStudentById(String studentId) {
		
		String sql="select * from students where student_id=?";
		return jdbcTemplate.queryForObject(sql,
				(rs,rowNum)->
					new StudentResponseDTO(rs.getString("student_id"),
							rs.getString("student_name"),
							rs.getString("dob"),
							rs.getString("gender"),
							rs.getString("phone"),
							rs.getString("education")),
					studentId);
		
	}
	
		
	public StudentResponseDTO selectStudentByName(String Name) {
		
		String sql="select * from students where student_name=?";
		return jdbcTemplate.queryForObject(sql,
				(rs,rowNum)->
					new StudentResponseDTO(rs.getString("student_id"),
							rs.getString("student_name"),
							rs.getString("dob"),
							rs.getString("gender"),
							rs.getString("phone"),
							rs.getString("education")),
					Name);
	}
	
	
	public List<StudentResponseDTO> selectStudentListByIdOrNameOrCourse(String id, String name, String course) {
		
		String sql = "select distinct students.student_id, students.student_name from student_course join students on student_course.student_id = students.student_id "
				+ "join courses on student_course.course_id = courses.course_id where students.student_id like ? or students.student_name like ? or courses.course_name like ?";
		return jdbcTemplate.query(sql,
				(rs,rowNum)->
					new StudentResponseDTO(
								rs.getString("student_id"),
								rs.getString("student_name")),
								id,"%"+name+"%","%"+course+"%"
				);
	}
	
	
	public List<StudentResponseDTO> selectAll(){
		
		String sql="select * from students";
		return jdbcTemplate.query(sql,
				(rs,rowNum)->
					new StudentResponseDTO(rs.getString("student_id"),
							rs.getString("student_name"),
							rs.getString("dob"),
							rs.getString("gender"),
							rs.getString("phone"),
							rs.getString("education"))
							);
	}	
	
	
}
*/
