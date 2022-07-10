package com.example.spring.controllers;

import com.example.spring.models.User;
import com.example.spring.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/db")
public class DatabaseController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public @ResponseBody Iterable<User> getDatabase(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<User> getUser(@PathVariable("id") Long id){
        return userRepository.findById(id);
    }
    @GetMapping("/add")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/add")
    public String addUserForm(@ModelAttribute User user, Model model){
        userRepository.save(user);
        return "redirect:/db/" + user.getId();
    }

}
