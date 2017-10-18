package com.easyrong.wireless.ribbonclient.controller;

import com.easyrong.wireless.ribbonclient.base.BaseErrorInfo;
import com.easyrong.wireless.ribbonclient.base.BaseRspEntity;
import com.easyrong.wireless.ribbonclient.constants.UserErrorInfo;
import com.easyrong.wireless.ribbonclient.entity.UserEntity;
import com.easyrong.wireless.ribbonclient.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model) {
        model.addAttribute("messages", "欢迎使用张家港市金融机构在线申报系统 V1.0");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        UserEntity userEntity = new UserEntity();
        model.addAttribute("user", userEntity);
        return "login";
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userLogin(UserEntity user, Model model, HttpServletRequest request) {
        if (null == user) {
            model.addAttribute("messages", BaseErrorInfo.PARAMS_ERROR.getMessage());
            return "result";
        }

        if (null == user.getName()) {
            model.addAttribute("messages", UserErrorInfo.USRENAME_NULL.getMessage());
            return "result";
        }

        if (null == user.getPassword()) {
            model.addAttribute("messages", UserErrorInfo.PASSWORD_NULL.getMessage());
            return "result";
        }

        BaseRspEntity verify = new Gson().fromJson(userService.userLogin(user).toString(), BaseRspEntity.class);

        if (BaseErrorInfo.SUCCESS.getCode().equals(verify.getCode())) {
            model.addAttribute("messages", "用户：" + user.getName() + " 登录成功，密码是：" + user.getPassword() + " session失效时间10分钟，可在 userLogin 接口中配置 ");
            request.getSession().setAttribute("account", verify);
            return "succeed";
        } else {
            model.addAttribute("messages", "用户：" + user.getName() + " 登录失败，用户名或密码错误");
            return "result";
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("messages", "欢迎使用张家港市金融机构在线申报系统 V1.0");
        return "index";
    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public String userRegister(UserEntity user, Model model, HttpServletRequest request) {
        if (null == user) {
            model.addAttribute("messages", "注册失败, " + BaseErrorInfo.PARAMS_ERROR.getMessage());
            return "result";
        }

        if (null == user.getName()) {
            model.addAttribute("messages", "注册失败, " + UserErrorInfo.USRENAME_NULL.getMessage());
            return "result";
        }

        if (null == user.getPassword()) {
            model.addAttribute("messages", "注册失败, " + UserErrorInfo.PASSWORD_NULL.getMessage());
            return "result";
        }

        if (0L == user.getMobile()) {
            model.addAttribute("messages", "注册失败, " + UserErrorInfo.MOBILE_NULL.getMessage());
            return "result";
        }

        if (0 == user.getType()) {
            model.addAttribute("messages", "注册失败, " + UserErrorInfo.TYPE_NULL.getMessage());
            return "result";
        }

        BaseRspEntity verify = (BaseRspEntity) userService.userRegister(user);
        if (BaseErrorInfo.SUCCESS.getCode().equals(verify.getCode())) {
            model.addAttribute("messages", "用户：" + user.getName() + " 注册成功，密码是：" + user.getPassword() + " 自动登录，session失效时间10分钟");
            request.getSession().setAttribute("account", verify);
            return "succeed";
        } else {
            model.addAttribute("messages", "用户：" + user.getName() + " 注册失败，用户名已被占用");
            return "result";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("13888888888");
        userEntity.setPassword("xlh123456");
        model.addAttribute("user", userEntity);
        return "register";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        request.getSession().invalidate();
        return "redirect:/";
    }
}
