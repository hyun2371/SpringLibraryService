package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.Fruit;
import com.group.libraryapp.domain.user.response.UserResponse;
import com.group.libraryapp.dto.calculator.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        String sql = "INSERT INTO USER (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    @GetMapping("/fruit")
    public Fruit fruit(){
        return new Fruit("바나나", 2000);
    }


    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM USER";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getName(), request.getId());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }
}
