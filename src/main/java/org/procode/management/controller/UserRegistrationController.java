package org.procode.management.controller;

import lombok.RequiredArgsConstructor;
import org.procode.management.model.UserEntity;
import org.procode.management.model.UserRegistrationDto;
import org.procode.management.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistrationController {
	private final UserService userService;
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserEntity registrationDto) {
		userService.saveUser(registrationDto);
		return "redirect:/registration?success";
	}
}
