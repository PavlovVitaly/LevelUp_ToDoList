package com.levelup.web.Controllers;

import com.levelup.web.Beans.UsersBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UsersController {
    @Autowired
    private UsersBean bean;

    @GetMapping("/")
    public String index(ModelMap model) {
        model.addAttribute("usersBean", bean);
        return "index";
    }
}
