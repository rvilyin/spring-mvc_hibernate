package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class MyController {

	private final UserService userService;

	@Autowired
	public MyController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String showAllUsers(Model model) {
		List<User> allUsers = userService.getAllUsers();
		model.addAttribute("allUsers", allUsers);

		return "all-users";
	}

	@GetMapping("/addNewUser")
	public String addNewUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);

		return "user-info";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		System.out.println(user);
		userService.saveUser(user);

		return "redirect:/";
	}

	@GetMapping("/updateInfo")
	public String updateUser(@RequestParam("userId") int id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);

		return "user-info";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") int id) {
		userService.deleteUser(id);

		return "redirect:/";
	}
}