package springboot_jpa.studentmanagement_JPA.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot_jpa.studentmanagement_JPA.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository usersRepository;

	//Login
	public User getUserByUserName(String name) {
		return usersRepository.findByUserName(name);
	}
	
	public Boolean getUserNameandPassword(String name, String password) {
		return usersRepository.existsByUserNameAndUserPassword(name, password);
	}
	
	// Search
	public List<User> getAllUsers() {
		List<User> userlist = (List<User>) usersRepository.findAll();
		return userlist;
	}

	public List<User> getUserById(String Id) {
		return usersRepository.findAllByUserID(Id);
	}

	public List<User> getUserByName(String name) {
		return usersRepository.findAllByUserName(name);
	}

	public List<User> getUserByIdOrName(String Id, String name) {
		return usersRepository.findAllByUserIDOrUserName(Id, name);
	}

	// Add
	public void addUser(User user) {
		usersRepository.save(user);
	}

	// Update
	 public User getUserbyUserId(String id) {
		 return usersRepository.findById(id).get();
	 }

	public void updateUser(User user) {
		usersRepository.save(user);
	}

	// Delete
	public void deleteUser(String Id) {
		usersRepository.deleteById(Id);
	}
}
