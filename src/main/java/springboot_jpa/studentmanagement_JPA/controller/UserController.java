package springboot_jpa.studentmanagement_JPA.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springboot_jpa.studentmanagement_JPA.dao.UserService;
import springboot_jpa.studentmanagement_JPA.model.User;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping (value="/")
	public String Login(ModelMap model) {
		return "LGN001";
	}
	
	
	@GetMapping (value="/user")
	public String displayView(ModelMap model) {
		List<User> userlist=userService.getAllUsers();
		model.addAttribute("userlist",userlist);
		return "USR003";
	}
	
	
	
	@PostMapping(value="/searchuser")
	public String usersearch(@RequestParam("userID") String userID,@RequestParam("userName") String userName,
			ModelMap model) {
		List<User> searchlist = new ArrayList<>();
		 if(userID.isEmpty()&& userName.isEmpty()) {
			searchlist= userService.getAllUsers();
		}
		 else if(userID.isBlank()) {
			searchlist=userService.getUserByName(userName);
		}
		else if(userName.isBlank()) {
			searchlist=userService.getUserById(userID);
		} else {
			searchlist = userService.getUserByIdOrName(userID, userName);
		}		
		
		model.addAttribute("userlist", searchlist);
		return "USR003";
	}
	
	@GetMapping("/user/add-user")
	public ModelAndView addUser() {
		return new ModelAndView("USR001","user",new User());
	}
	
	@PostMapping(value="/adduser")
	public String addUser(@ModelAttribute("user") @Validated User uBean, BindingResult bs,
			ModelMap model) {
		if(bs.hasErrors()) {
			return "USR001";
		}
		if(uBean.getUserID().equals("") || uBean.getUserName().equals("") || uBean.getUserPassword().equals("") || 
				uBean.getUserConfirmPassword().equals("")|| uBean.getUserRole().equals("")) {
			model.addAttribute("error", "Fill Blanks!");			
			return "USR001";
			}else {
				User user=new User();
				user.setUserID(uBean.getUserID());
				user.setUserName(uBean.getUserName());
				user.setUserPassword(uBean.getUserPassword());
				user.setUserConfirmPassword(uBean.getUserConfirmPassword());
				user.setUserRole(uBean.getUserRole());
				
				if(!user.getUserPassword().equals(user.getUserConfirmPassword())) {
					model.addAttribute("error","Password do not match!");
					return "USR001";
				}else {
				userService.addUser(user);
					model.addAttribute("success", "Successful Register");
					
				}
				return "USR001";
			}
			
	}
	
	@GetMapping(value="/setupUpdateUser")
	public  ModelAndView updateUser(@RequestParam ("userId") String userID) {
		return new ModelAndView ("USR002", "User", userService.getUserbyUserId(userID));
	}
	
	@PostMapping(value="/updateuser")
	public String updateUser(@ModelAttribute("User") @Validated User User, BindingResult bs,
		ModelMap model) {
		
			if(bs.hasErrors()) {
				return "USR002";
			}
			
			if(User.getUserID().equals("") || User.getUserName().equals("") || User.getUserPassword().equals("") || 
					User.getUserConfirmPassword().equals("")||User.getUserRole().equals("") && 
					User.getUserPassword()!=User.getUserConfirmPassword()) {
				model.addAttribute("error", "Fill Blanks!");
				return "USR002";
				}else {
					User  user=new User();
					user.setUserID(User.getUserID());
					user.setUserName(User.getUserName());
					user.setUserPassword(User.getUserPassword());
					user.setUserConfirmPassword(User.getUserConfirmPassword());
					user.setUserRole(User.getUserRole());
					if(!user.getUserPassword().equals(user.getUserConfirmPassword())) {
						model.addAttribute("error","Password do not match!");
						return "USR002";
					}else {
						userService.updateUser(User);	
							model.addAttribute("success", "Successful Updated");						
					}
					return "USR002";	
				}	
		}
	
	@GetMapping(value="/deleteUser")
	public String deleteuser(@RequestParam ("userId") String userID,ModelMap model) {
		userService.deleteUser(userID);
		
		return "USR003";
	}
	
}
