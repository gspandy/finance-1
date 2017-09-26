package com.easyrong.wireless.finance.controller;

import com.easyrong.wireless.finance.entity.UserEntity;
import com.easyrong.wireless.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String welcome() {
        return "index";
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
    public String registerUser(UserEntity user, Model model, HttpSession httpSession) {
        boolean verify = userService.registerUser(user);
        if (verify) {
            model.addAttribute("messages", "用户：" + user.getName() + " 注册成功，密码是：" + user.getPassword() + " 自动登录，session失效时间1分钟");
            httpSession.setAttribute("account", verify);
            httpSession.setMaxInactiveInterval(60);
            return "succeed";
        } else {
            model.addAttribute("messages", "用户：" + user.getName() + " 注册失败，用户名已被占用");
            return "result";
        }
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userLogin(UserEntity user, Model model, HttpSession httpSession) {
        boolean verify = userService.verifyUser(user);
        if (verify) {
            model.addAttribute("messages", "用户：" + user.getName() + " 登录成功，密码是：" + user.getPassword() + " session失效时间1分钟");
            httpSession.setAttribute("account", verify);
            httpSession.setMaxInactiveInterval(60);
            return "succeed";
        } else {
            model.addAttribute("messages", "用户：" + user.getName() + " 登录失败，用户名或密码错误");
            return "result";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("account");
        httpSession.invalidate();
        return "redirect:/";
    }
}
