package com.example.spring.controller;

import com.example.spring.model.User;
import com.example.spring.repository.UserRepository;
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
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<User> getUser(@PathVariable("id") Long id){
        return userRepository.findById(id);
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/add")
    public String addUserForm(@ModelAttribute User user, Model model){
        userRepository.save(user);
        return "redirect:/db/" + user.getId();
    }

}
