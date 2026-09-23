package main.web;

import main.model.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController
{
    private final UserService userService;
    private final TransactionService transactionService;

    @Autowired
    public UserController(UserService userService, TransactionService transactionService)
    {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public String getUsers(Model model)
    {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users"; // users.html
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable long id, Model model)
    {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("transactions", transactionService.getTransactionsByUser(user));
        return "user-details"; //user-details.html
    }
}
