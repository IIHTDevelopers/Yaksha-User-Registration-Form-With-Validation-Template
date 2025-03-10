package com.yaksha.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yaksha.assignment.models.User;
import com.yaksha.assignment.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// Show the registration form
	@GetMapping("/")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	// Handle form submission for user registration with validation
	@PostMapping("/user/register")
	public String registerUser(User user, BindingResult result, Model model) {
		// If there are validation errors, return the user to the registration form
		if (result.hasErrors()) {
			return "register";
		}

		// Call the service to save the user
		userService.registerUser(user);
		return "redirect:/user/success"; // Redirect to success page after successful registration
	}

	// Show success message after successful registration
	@GetMapping("/user/success")
	public String showSuccessPage() {
		return "registration-success";
	}
}
