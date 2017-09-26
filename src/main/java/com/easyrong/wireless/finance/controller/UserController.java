package com.easyrong.wireless.finance.controller;

import com.easyrong.wireless.finance.entity.UserEntity;
import com.easyrong.wireless.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "欢迎使用张家港市金融机构在线申报系统 V1.0";
    }

    @RequestMapping("/notVerify")
    @ResponseBody
    public String notVerify() {
        return "用户名或者密码错误";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserEntity());
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", new UserEntity());
        return "index";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public String registerUser(UserEntity user, Model model) {
        return userService.registerUser(user);
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userLogin(UserEntity user, Model model) {
        boolean verify = userService.verifyUser(user);
        if (verify) {
            model.addAttribute("name", user.getName());
            model.addAttribute("password", user.getPassword());
            return "result";
        } else {
            return "redirect:/notVerify";
        }
    }
}
