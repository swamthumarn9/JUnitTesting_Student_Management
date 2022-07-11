package springboot_jpa.studentmanagement_JPA;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import springboot_jpa.studentmanagement_JPA.dao.UserRepository;
import springboot_jpa.studentmanagement_JPA.dao.UserService;
import springboot_jpa.studentmanagement_JPA.model.User;

@SpringBootTest
public class UserServiceTests {
	
	@Mock
	UserRepository userRepo;
	
	@InjectMocks
	UserService userService;
	
	@Test
	public void getUserByUserNameTest() {
		User setuser=new User();
		setuser.setUserID("USR001");
		setuser.setUserName("John");
		setuser.setUserPassword("123");
		setuser.setUserConfirmPassword("123");
		setuser.setUserRole("Admin");
		when(userRepo.findByUserName("John")).thenReturn(setuser);
		User getUser=userService.getUserByUserName("John");
		assertEquals("USR001",getUser.getUserID());
		assertEquals("123",getUser.getUserPassword());
		assertEquals("123",getUser.getUserConfirmPassword());
		assertEquals("Admin", getUser.getUserRole());
		
	}
	
	@Test
	public void getUserNameandPasswordTest() {
		User setuser=new User();
		setuser.setUserID("USER001");
		setuser.setUserName("John");
		setuser.setUserPassword("123");
		setuser.setUserConfirmPassword("123");
		setuser.setUserRole("Admin");
		//when(userRepo.findByUserName("John")).thenReturn(setuser);
		when(userRepo.existsByUserNameAndUserPassword("John","123")).thenReturn(true);
		
		assertTrue(setuser.getUserName().equals("John"));
		assertTrue(setuser.getUserPassword().equals("123"));
	}
	
	
	@Test
	public void getAllUsersTest() {
		List<User> setuserlist=new ArrayList<>();
		User user1=new User();
		user1.setUserID("USR001");
		user1.setUserName("John");
		user1.setUserPassword("123");
		user1.setUserConfirmPassword("123");
		user1.setUserRole("Admin");
		User user2=new User();
		user2.setUserID("USR002");
		user2.setUserName("Mike");
		user2.setUserPassword("123");
		user2.setUserConfirmPassword("123");
		user2.setUserRole("User");
		User user3=new User();
		user3.setUserID("USR003");
		user3.setUserName("Ron");
		user3.setUserPassword("111");
		user3.setUserConfirmPassword("111");
		user3.setUserRole("User");
		setuserlist.add(user1);
		setuserlist.add(user2);
		setuserlist.add(user3);
		when(userRepo.findAll()).thenReturn(setuserlist);
		List<User> getalluser=userService.getAllUsers();
		assertEquals(3, getalluser.size());
		verify(userRepo, times(1)).findAll();
	}
	
	
	@Test
	public void getUserByIdTest() {
		List<User> setuserlist=new ArrayList<>();
		User user1=new User();
		user1.setUserID("USR001");
		user1.setUserName("John");
		user1.setUserPassword("123");
		user1.setUserConfirmPassword("123");
		user1.setUserRole("Admin");
		setuserlist.add(user1);
		when(userRepo.findAllByUserID("USR001")).thenReturn(setuserlist);
		List<User> getUser=userService.getUserById("USR001");
		assertEquals(1, getUser.size());
		verify(userRepo, times(1)).findAllByUserID("USR001");
	}
	
	@Test
	public void getUserByNameTest() {
		List<User> setuserlist=new ArrayList<>();
		User user1=new User();
		user1.setUserID("USR001");
		user1.setUserName("John");
		user1.setUserPassword("123");
		user1.setUserConfirmPassword("123");
		user1.setUserRole("Admin");
		setuserlist.add(user1);
		when(userRepo.findAllByUserName("John")).thenReturn(setuserlist);
		List<User> getUser=userService.getUserByName("John");
		assertEquals(1, getUser.size());
		verify(userRepo, times(1)).findAllByUserName("John");
	}
	
	@Test
	public void getUserByIdOrNameTest() {
		List<User> setuserlist=new ArrayList<>();
		User user1=new User();
		user1.setUserID("USR001");
		user1.setUserName("John");
		user1.setUserPassword("123");
		user1.setUserConfirmPassword("123");
		user1.setUserRole("Admin");
		User user2=new User();
		user2.setUserID("USR002");
		user2.setUserName("Mike");
		user2.setUserPassword("123");
		user2.setUserConfirmPassword("123");
		user2.setUserRole("User");
		User user3=new User();
		user3.setUserID("USR003");
		user3.setUserName("Ron");
		user3.setUserPassword("111");
		user3.setUserConfirmPassword("111");
		user3.setUserRole("User");
		setuserlist.add(user1);
		setuserlist.add(user2);
		setuserlist.add(user3);
		when(userRepo.findAllByUserIDOrUserName("USR001","Ron")).thenReturn(setuserlist);
		List<User> getUser=userService.getUserByIdOrName("USR001","Ron");
		assertEquals(3, getUser.size());
		verify(userRepo, times(1)).findAllByUserIDOrUserName("USR001","Ron");
	}
	
	
	@Test
	public void addUserTest() {
		User setuser=new User();
		setuser.setUserID("USER001");
		setuser.setUserName("John");
		setuser.setUserPassword("123");
		setuser.setUserConfirmPassword("123");
		setuser.setUserRole("Admin");
		userService.addUser(setuser);
		verify(userRepo,times(1)).save(setuser);
	}
	
	@Test
	public void getUserbyUserIdTest() {
		User setuser=new User();
		setuser.setUserID("USR001");
		setuser.setUserName("John");
		setuser.setUserPassword("123");
		setuser.setUserConfirmPassword("123");
		setuser.setUserRole("Admin");
		when(userRepo.findById("USR001")).thenReturn(Optional.ofNullable(setuser));
		User getUser=userService.getUserbyUserId("USR001");
		assertEquals("USR001",getUser.getUserID());
		assertEquals("123",getUser.getUserPassword());
		assertEquals("123",getUser.getUserConfirmPassword());
		assertEquals("Admin", getUser.getUserRole());
	}
	
	@Test
	public void updateUserTest() {
		User setuser=new User();
		setuser.setUserID("USER001");
		setuser.setUserName("John");
		setuser.setUserPassword("123");
		setuser.setUserConfirmPassword("123");
		setuser.setUserRole("Admin");
		userService.updateUser(setuser);
		verify(userRepo,times(1)).save(setuser);
	}
	
	@Test
	public void deleteUserTest() {
		userService.deleteUser("USR001");
		verify(userRepo,times(1)).deleteById("USR001");
	}
	
	
}
