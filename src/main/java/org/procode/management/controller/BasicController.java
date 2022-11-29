package org.procode.management.controller;

import javax.validation.Valid;

import org.procode.management.config.UserValidator;
import org.procode.management.model.EmployeeEntity;
import org.procode.management.model.UserEntity;
import org.procode.management.service.EmployeeService;
import org.procode.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BasicController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/")
    public String getIndex() {
        return "login-page";
    }

    @GetMapping("/register")
    public String registerNewUser(Model model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("employee", new EmployeeEntity());
        return "register-page";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute(value = "user") @Valid UserEntity newUser,
                                  BindingResult bindingResultNewUser,
                                  @ModelAttribute(value = "employee") @Valid EmployeeEntity newEmployee,
                                  BindingResult bindingResultNewEmployee) {
        userValidator.validate(newUser, bindingResultNewUser);

        if (bindingResultNewUser.hasErrors() || bindingResultNewEmployee.hasErrors()) {
            return "register-page";
        }

        newUser.setEmployee(newEmployee);
        employeeService.saveEmployee(newEmployee);
        userService.saveUser(newUser);

        return "redirect:/employees";
    }

    @GetMapping("/login")
    public String login() {
        return "login-page";
    }
}
