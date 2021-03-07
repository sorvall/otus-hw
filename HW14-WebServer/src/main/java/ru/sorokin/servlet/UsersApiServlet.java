package ru.sorokin.servlet;

import com.google.gson.Gson;
import ru.sorokin.entity.User;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sorokin.service.UserService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class UsersApiServlet extends HttpServlet {

    private final UserService userService;
    private final Gson gson;

    public UsersApiServlet(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Reader reader = new InputStreamReader(new ByteArrayInputStream(req.getInputStream().readAllBytes()), StandardCharsets.UTF_8);
        User user = gson.fromJson(reader, User.class);
        userService.saveUser(user);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = userService.getAll();
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        out.print(gson.toJson(users));
    }

}
