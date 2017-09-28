package com.easyrong.wireless.finance.controller;

import com.easyrong.wireless.finance.entity.UserEntity;
import com.easyrong.wireless.finance.service.UserService;
import io.swagger.annotations.ApiOperation;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "默认", notes = "默认")
    public String welcome(Model model) {
        model.addAttribute("messages", "欢迎使用张家港市金融机构在线申报系统 V1.0");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "登录", notes = "登录")
    public String login(Model model) {
        model.addAttribute("user", new UserEntity());
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "首页", notes = "首页")
    public String index(Model model) {
        model.addAttribute("messages", "欢迎使用张家港市金融机构在线申报系统 V1.0");
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ApiOperation(value = "注册", notes = "注册页面")
    public String register(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ApiOperation(value = "注册", notes = "注册数据提交")
    public String registerUser(UserEntity user, Model model, HttpSession httpSession) {
        boolean verify = userService.registerUser(user);
        if (verify) {
            model.addAttribute("messages", "用户：" + user.getName() + " 注册成功，密码是：" + user.getPassword() + " 自动登录，session失效时间10分钟");
            httpSession.setAttribute("account", verify);
            httpSession.setMaxInactiveInterval(600);
            return "succeed";
        } else {
            model.addAttribute("messages", "用户：" + user.getName() + " 注册失败，用户名已被占用");
            return "result";
        }
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "登录数据提交")
    public String userLogin(UserEntity user, Model model, HttpSession httpSession) {
        boolean verify = userService.verifyUser(user);
        if (verify) {
            model.addAttribute("messages", "用户：" + user.getName() + " 登录成功，密码是：" + user.getPassword() + " session失效时间10分钟，可在 userLogin 接口中配置 ");
            httpSession.setAttribute("account", verify);
            //设置Session失效时间，单位秒
            httpSession.setMaxInactiveInterval(600);
            return "succeed";
        } else {
            model.addAttribute("messages", "用户：" + user.getName() + " 登录失败，用户名或密码错误");
            return "result";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation(value = "登出", notes = "登出清除Session")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("account");
        httpSession.invalidate();
        return "redirect:/";
    }
}
