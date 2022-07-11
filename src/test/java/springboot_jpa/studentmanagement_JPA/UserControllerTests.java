package springboot_jpa.studentmanagement_JPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import springboot_jpa.studentmanagement_JPA.dao.UserRepository;
import springboot_jpa.studentmanagement_JPA.dao.UserService;
import springboot_jpa.studentmanagement_JPA.model.User;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	
	@MockBean
	UserRepository userRepo;
	
	@Test
	public void LoginTest() throws Exception{
		this.mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("LGN001"));
	}
	
	@Test
	public void displayUserViewTest() throws Exception{
		List<User> userlist=new ArrayList<>();
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
		userlist.add(user1);
		userlist.add(user2);
		when(userService.getAllUsers()).thenReturn(userlist);
		this.mockMvc.perform(get("/user"))
		.andExpect(status().isOk())
		.andExpect(view().name("USR003"))
		.andExpect(model().attributeExists("userlist"));
	}
	
	@Test
	public void usersearchTest() throws Exception{
		List<User> userlist=new ArrayList<>();
		User user1=new User();
		user1.setUserID("USR001");
		user1.setUserName("John");
		user1.setUserPassword("123");
		user1.setUserConfirmPassword("123");
		user1.setUserRole("Admin");
		userlist.add(user1);
		when(userService.getAllUsers()).thenReturn(userlist);
		when(userService.getUserByName("John")).thenReturn(userlist);
		when(userService.getUserById("USR001")).thenReturn(userlist);
		when(userService.getUserByIdOrName("USR001", "John")).thenReturn(userlist);
		this.mockMvc.perform(post("/searchuser")
		.param("userID", "USR001")
		.param("userName", "John"))
		.andExpect(status().isOk())
		.andExpect(view().name("USR003"))
		.andExpect(model().attributeExists("userlist"));
	}
	
	@Test
	public void setupaddUserTest() throws Exception{
		this.mockMvc.perform(get("/user/add-user"))
		.andExpect(status().isOk())
		.andExpect(view().name("USR001"));
	}
	
	//testing adduser method when validated fail
	@Test
	public void addUserValidateTest() throws Exception{
		this.mockMvc.perform(post("/adduser"))
		.andExpect(status().isOk())
		.andExpect(view().name("USR001"))
		.andExpect(model().attributeExists("error"));
	}
	
	//testing adduser method when validated pass	
		@Test
		public void addUserOkTest() throws Exception {
			User user1=new User();
			user1.setUserID("USR001");
			user1.setUserName("John");
			user1.setUserPassword("123");
			user1.setUserConfirmPassword("123");
			user1.setUserRole("Admin");			
			userService.addUser(user1);
			this.mockMvc.perform(post("/adduser").flashAttr("user", user1))
			.andExpect(status().isOk())
			.andExpect(view().name("USR001"))
			.andExpect(model().attributeExists("success"));
		}
		
		@Test
		public void setupUpdateUserTest() throws Exception{
			when(userService.getUserbyUserId("USR001")).thenReturn(new User());			
			this.mockMvc.perform(get("/setupUpdateUser").param("userId","USR001"))
			.andExpect(status().isOk())
			.andExpect(view().name("USR002"))
			.andExpect(model().attributeExists("User"));	
		}
		
		//testing updateuser method when validated fail
		@Test
		public void updateUserValidateTest() throws Exception{
			this.mockMvc.perform(post("/updateuser"))
			.andExpect(status().isOk())
			.andExpect(view().name("USR002"))
			.andExpect(model().attributeExists("error"));
		}
		
		//testing adduser method when validated pass	
				@Test
		public void updateUserOkTest() throws Exception {
			User user1=new User();
			user1.setUserID("USR001");
			user1.setUserName("John");
			user1.setUserPassword("123");
			user1.setUserConfirmPassword("123");
			user1.setUserRole("Admin");			
			userService.updateUser(user1);
			this.mockMvc.perform(post("/updateuser").flashAttr("User", user1))
			.andExpect(status().isOk())
			.andExpect(view().name("USR002"))
			.andExpect(model().attributeExists("success"));
		}
	
}
