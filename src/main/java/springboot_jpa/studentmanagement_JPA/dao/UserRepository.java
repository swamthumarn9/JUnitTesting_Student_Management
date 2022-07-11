package springboot_jpa.studentmanagement_JPA.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springboot_jpa.studentmanagement_JPA.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{
	
	
	List<User> findAllByUserID(String id);
	
	List<User> findAllByUserName(String name);
	
	List<User> findAllByUserIDOrUserName(String id,String name);
	
	User findByUserName(String Name);
	
	Boolean existsByUserNameAndUserPassword(String name,String password);
	
}
