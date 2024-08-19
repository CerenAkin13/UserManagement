package org.example;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<String> users = new ArrayList<>();

    @GetMapping
    public List<String> getUsers() {
        return users;
    }

    @PostMapping
    public void addUser(@RequestBody String user) {
        users.add(user);
    }
}
