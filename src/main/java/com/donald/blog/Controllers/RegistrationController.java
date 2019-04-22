package com.donald.blog.Controllers;

import com.donald.blog.Models.dto.UserRegistrationDto;
import com.donald.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public String registration(Model model) {
        UserRegistrationDto userDto = new UserRegistrationDto();
        model.addAttribute("user", userDto);
        return "/registration";
    }

    @PostMapping(value = "/register")
    public String createNewUser(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                BindingResult bindingResult, Model model) {

        boolean exists = userService.findByEmail(userDto.getEmail()).isPresent();

        if (exists){
            bindingResult.rejectValue("email", null, "This email is already used !");
        }

        if (bindingResult.hasErrors()){

            return "/registration";
        }

        try {
            userService.save(userDto);
        }catch (Exception e){

            model.addAttribute("customError", e.getMessage());
            return "/registration";
        }

        model.addAttribute("customMsg", "User account has been created successfully !");
        return "/login";
    }
}
