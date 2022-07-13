package springboot_jpa.studentmanagement_JPA;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import com.mysql.cj.Session;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import springboot_jpa.studentmanagement_JPA.dao.UserRepository;
import springboot_jpa.studentmanagement_JPA.dao.UserService;
import springboot_jpa.studentmanagement_JPA.model.User;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	@MockBean
	UserRepository userRepo;

	@Test
	public void loginViewTest() throws Exception {
		this.mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("LGN001"))
				.andExpect(model().attributeExists("ubean"));

	}

	// Test login if username and password is true
	@Test
	public void setloginTest() throws Exception {
		// when(userRepo.existsByUserNameAndUserPassword("John","123")).thenReturn(true);
		User user = new User();
		user.setUserID("USR001");
		user.setUserName("John");
		user.setUserPassword("123");
		user.setUserConfirmPassword("123");
		user.setUserRole("Admin");
		when(userService.getUserNameandPassword(user.getUserName(), user.getUserPassword())).thenReturn(true);
		when(userService.getUserByUserName(user.getUserName())).thenReturn(user);
		session.setAttribute("userdata", user);
		this.mockMvc.perform(post("/login").param("userName", "John").param("userPassword", "123").session(session))
				.andExpect(status().isOk()).andExpect(view().name("MNU001"));
	}

}
