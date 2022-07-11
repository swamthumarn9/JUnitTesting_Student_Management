package springboot_jpa.studentmanagement_JPA.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/*

@Repository
public class CourseDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public int insertCourse(CourseRequestDTO coursedto) {
		int result=0;
		String sql="insert into courses (course_id, course_name) values (?,?)";
		result=jdbcTemplate.update(sql,
				coursedto.getCourseId(),
				coursedto.getCourseName());
		return result;
	}
	
	
	public int updateCourse(CourseRequestDTO coursedto) {
		int result=0;
		String sql="update courses set course_id=?, course_name=?";
		result=jdbcTemplate.update(sql,
				coursedto.getCourseId(),
				coursedto.getCourseName());
		return result;
	}
	
	
	public List<String> selectCoursebyStudentId(String Id) {
		
		String sql="select courses.course_name from courses join student_course "
				+ "on courses.course_id=student_course.course_id where student_course.student_id=?";
		return jdbcTemplate.query(sql, 
				(rs,rowNum)->
						rs.getString("course_name"),
							Id);
	}
	
	public List<CourseResponseDTO> selectCoursesByStudentId(String student_id){
		String sql = "select courses.course_name, courses.course_id from courses join student_course "
				+ "on courses.course_id=student_course.course_id where student_course.student_id=?";
		return jdbcTemplate.query(sql, (rs,rowNum) -> new CourseResponseDTO(rs.getString("course_id"),rs.getString("course_name")),student_id);
	}
	
	public List<CourseResponseDTO> selectAll(){
		
		String sql="select * from courses";		
		return jdbcTemplate.query(sql,
					(rs,rowNum)->new CourseResponseDTO(
							rs.getString("course_id"),
							rs.getString("course_name")
							));
	}
	
	
	public int insertID(String StudentId, String courseId ) {
		int result=0;
		String sql="insert into student_course (student_id, course_id) values (?,?)";
		
		
		return result;
	}
}
*/
