package com.yaksha.assignment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.yaksha.assignment.models.User;

@Service
public class UserService {

	// Simulating an in-memory list to store registered users
	private List<User> users = new ArrayList<>();

	/**
	 * Registers a new user and stores it in the in-memory list.
	 *
	 * @param user - The user to be registered.
	 */
	public String registerUser(User user) {
		// Validate the user data
		String validationMessage = validateUser(user);
		if (!validationMessage.isEmpty()) {
			return validationMessage; // Return validation error message if any
		}

		// Save user in memory (in a real-world scenario, this would save the user to a
		// database)
		user.setId(generateUserId());
		users.add(user);
		System.out.println("User registered: " + user.getName());
		return "User registered successfully!";
	}

	/**
	 * Validates the user details such as name, email, and password.
	 *
	 * @param user - The user to be validated.
	 * @return Validation error message if any, empty string if valid.
	 */
	private String validateUser(User user) {
		// Validate name
		if (user.getName() == null || user.getName().trim().isEmpty()) {
			return "Name cannot be empty";
		}

		// Validate email
		if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
			return "Email cannot be empty";
		} else if (!isValidEmail(user.getEmail())) {
			return "Email is invalid";
		}

		// Validate password
		if (user.getPassword() == null || user.getPassword().length() < 6) {
			return "Password must be at least 6 characters long";
		}

		return ""; // No validation errors
	}

	/**
	 * Validates the email format using a regular expression.
	 *
	 * @param email - The email to be validated.
	 * @return true if the email is valid, false otherwise.
	 */
	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Generates a new unique user ID.
	 *
	 * @return A unique user ID.
	 */
	private long generateUserId() {
		return users.size() + 1; // Just a simple ID generation logic for demo
	}

	/**
	 * Retrieves a list of all registered users.
	 *
	 * @return List of all registered users.
	 */
	public List<User> getAllUsers() {
		return users;
	}

	/**
	 * Finds a user by their ID.
	 *
	 * @param id - The ID of the user to be fetched.
	 * @return The user with the specified ID, or null if not found.
	 */
	public User getUserById(long id) {
		return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
	}

	/**
	 * Updates an existing user's details.
	 *
	 * @param updatedUser - The user with updated information.
	 * @return true if the user was updated, false otherwise.
	 */
	public String updateUser(User updatedUser) {
		// Validate the updated user
		String validationMessage = validateUser(updatedUser);
		if (!validationMessage.isEmpty()) {
			return validationMessage; // Return validation error message if any
		}

		User existingUser = getUserById(updatedUser.getId());

		if (existingUser == null) {
			return "User not found"; // User not found
		}

		existingUser.setName(updatedUser.getName());
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setPassword(updatedUser.getPassword());
		return "User updated successfully";
	}

	/**
	 * Deletes a user by their ID.
	 *
	 * @param id - The ID of the user to be deleted.
	 * @return true if the user was deleted, false otherwise.
	 */
	public String deleteUser(long id) {
		User user = getUserById(id);
		if (user != null) {
			users.remove(user);
			return "User deleted successfully";
		}
		return "User not found"; // User not found
	}
}
