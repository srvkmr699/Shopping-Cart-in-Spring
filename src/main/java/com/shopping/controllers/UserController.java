package com.shopping.controllers;

import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.entities.User;
import com.shopping.entities.VerificationToken;
import com.shopping.event.OnRegistrationCompleteEvent;
import com.shopping.exception.EmailExitsException;
import com.shopping.models.LoginDto;
import com.shopping.models.UserDto;
import com.shopping.service.IUserService;
import com.shopping.service.UserRegistrationEventService;

/**
 * 
 * @author akshay
 *
 */
@Controller("users")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private UserRegistrationEventService userRegistrationEventService;

	@RequestMapping("/signup")
	public String showRegistration(Model model) {
		model.addAttribute("user", new UserDto());
		return "registration";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerNewUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult,
			WebRequest request, Errors errors) {
		try {
			User user = new User();
			BeanUtils.copyProperties(userDto, user);
			if (bindingResult.hasErrors()) {
				return new ModelAndView("registration", "user", userDto);
			}
			user = userService.createUserAccount(user);

			String appUrl = request.getContextPath();
			userRegistrationEventService
					.confirmUserRegistrationEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));

		} catch (EmailExitsException emailExistsException) {
			return new ModelAndView("registration", "message", emailExistsException.getMessage());
		} catch (Exception me) {
			return new ModelAndView("email_error", "user", userDto);
		}
		return new ModelAndView("registration", "message", "Activation link has been sent to your e-mail");
	}

	@RequestMapping(value = "/regitration_confirm", method = RequestMethod.GET)
	public String confirmRegistration(@RequestParam("token") String token, ModelMap modelMap) {

		VerificationToken verificationToken = userService.getVerificatonToken(token);
		if (verificationToken == null) {
			modelMap.addAttribute("errorMessage", "You are not authorized");
			return "error_page";
		}
		User user = verificationToken.getUser();
		Calendar calendar = Calendar.getInstance();
		if (verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0) {
			modelMap.addAttribute("errorMessage", "Activation link has been expired");
			return "error_page";
		}
		user.setEnabled(true);
		userService.saveRegisteredUser(user);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("loginDto", new LoginDto());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(@ModelAttribute("loginDto") @Valid LoginDto loginDto, BindingResult bindingResult,
			HttpSession session) {

		if (bindingResult.hasErrors()) {
			return "login";
		}
		if (userService.isUserValid(loginDto)) {
			session.setAttribute("email", loginDto.getEmail());
			return "redirect:/view_products";
		}
		return "login";
	}
}
