package com.rytesoft.rytewebspringapp.controller;

import com.rytesoft.rytewebspringapp.model.User;
import com.rytesoft.rytewebspringapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/home")
    public String Home() throws Exception {

        return "index";
    }

    @GetMapping("/new")
    public String showNewUserForm(Model model) throws Exception {
        model.addAttribute("user", new User());
        return "new-user-form";
    }

    @PostMapping("/new")
    public String saveNewUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-user-form";
        }
        userService.saveUser(user);
        return "redirect:/users/userlist";
    }

    @GetMapping("/{id}")
    public String showUserDetails(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "user-not-found";
        }
        model.addAttribute("user", user);
        return "user-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "user-not-found";
        }
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-user";
        }
        userService.updateUser(id, user);
        return "redirect:/users/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users/userlist";
    }

    @GetMapping("/userlist")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsersOrderedByUsername();
        model.addAttribute("users", users);
        return "users-list";
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<User> searchResults = userService.searchUsersByUsername(searchTerm);
        model.addAttribute("searchResults", searchResults);
        return "search_results";
    }

}

