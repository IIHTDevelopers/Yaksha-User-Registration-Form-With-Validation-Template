package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yaksha.assignment.models.User;
import com.yaksha.assignment.service.UserService;
import com.yaksha.assignment.utils.CustomParser;

public class UserControllerTest {

	private UserService userService;

	@BeforeEach
	public void setup() {
		// Initialize the mock service before each test
		userService = mock(UserService.class);
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testJspTagsAndHtmlTagPresenceInRegisterJsp() throws Exception {
		String filePath = "src/main/webapp/WEB-INF/views/register.jsp";

		// Check for form submission and input elements in register.jsp
		boolean hasFormTag = CustomParser.checkJspTagPresence(filePath, "<form");
		boolean hasInputTags = CustomParser.checkJspTagPresence(filePath, "<form:input");
		boolean hasPasswordTags = CustomParser.checkJspTagPresence(filePath, "<form:password");
		boolean hasErrorTags = CustomParser.checkJspTagPresence(filePath, "<form:errors");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(), hasFormTag && hasInputTags && hasPasswordTags && hasErrorTags, businessTestFile);
	}

	@Test
	public void testJspTagsAndHtmlTagPresenceInRegistrationSuccessJsp() throws Exception {
		String filePath = "src/main/webapp/WEB-INF/views/registration-success.jsp";

		// Check for success message and link to go back to home
		boolean hasSuccessMessage = CustomParser.checkJspTagPresence(filePath, "<h1>Registration Successful!</h1>");
		boolean hasBackLink = CustomParser.checkJspTagPresence(filePath, "<a href=\"/\">Back to Home</a>");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(), hasSuccessMessage && hasBackLink, businessTestFile);
	}

	@Test
	public void testFormActionForUserRegistrationInRegisterJsp() throws Exception {
		String filePath = "src/main/webapp/WEB-INF/views/register.jsp";

		// Check for correct form action tag for user registration submission
		boolean hasFormAction = CustomParser.checkJspTagPresence(filePath, "action=\"/user/register\"");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(), hasFormAction, businessTestFile);
	}

	@Test
	public void testUserRegistrationRedirectAfterSuccess() throws Exception {
		// Simulate a user registration action and check for the redirect
		String expectedRedirectUrl = "/user/success";
		String actualRedirectUrl = "/user/success"; // Assuming successful registration redirects to this URL

		// Check if the redirect URL after user registration matches
		yakshaAssert(currentTest(), expectedRedirectUrl.equals(actualRedirectUrl), businessTestFile);
	}

	@Test
	public void testUserRegistrationRedirectAfterSuccess2() throws Exception {
		// Simulate a user registration action and check for the redirect
		String expectedRedirectUrl = "/user/success";
		String actualRedirectUrl = "/user/success"; // Assuming successful registration redirects to this URL

		// Check if the redirect URL after user registration matches
		yakshaAssert(currentTest(), expectedRedirectUrl.equals(actualRedirectUrl), businessTestFile);
	}

	// Testing the service methods

	@Test
	public void testRegisterUser() throws IOException {
		User user = new User();
		user.setName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setPassword("password123");

		// Mock the service method to simulate the behavior
		when(userService.registerUser(user)).thenReturn("User registered successfully!");

		// Call the service method
		String result = userService.registerUser(user);

		// Validate that the result is the expected success message
		yakshaAssert(currentTest(), "User registered successfully!".equals(result), businessTestFile);
	}

	@Test
	public void testUpdateUser() throws IOException {
		User user = new User();
		user.setId(1);
		user.setName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setPassword("newpassword123");

		// Mock the service method to simulate the behavior of updating the user
		when(userService.updateUser(user)).thenReturn("User updated successfully");

		// Call the service method
		String result = userService.updateUser(user);

		// Validate that the result is the expected success message
		yakshaAssert(currentTest(), "User updated successfully".equals(result), businessTestFile);
	}

	@Test
	public void testGetUserById() throws IOException {
		User user = new User();
		user.setId(1);
		user.setName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setPassword("password123");

		// Mock the service method to simulate retrieving the user by ID
		when(userService.getUserById(1)).thenReturn(user);

		// Call the service method
		User result = userService.getUserById(1);

		// Validate that the user returned is the same as the mock user
		yakshaAssert(currentTest(), user.equals(result), businessTestFile);
	}

	@Test
    public void testDeleteUser() throws IOException {
        // Mock the service method to simulate deleting the user by ID
        when(userService.deleteUser(1)).thenReturn("User deleted successfully");

        // Call the service method
        String result = userService.deleteUser(1);

        // Validate that the result is the expected success message
        yakshaAssert(currentTest(), "User deleted successfully".equals(result), businessTestFile);
    }
}
