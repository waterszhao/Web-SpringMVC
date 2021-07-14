package com.waters.controller;

import com.waters.pojo.User;
import com.waters.service.UserService;
import com.waters.utils.EncodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 用户控制器，主要用于接受前端的请求
 * 完成操作后返回相应的视图
 *
 * 功能包括用户注册，修改信息，以及设置一些cookies登录信息
 * */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //cookie作用域
    @Autowired
    String domain;

    //注册
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "/user/register";
    }

    @RequestMapping("/register")
    public String register(User user, Model model, HttpServletResponse httpServletResponse, HttpSession httpSession) {

        User user1 = userService.queryByName(user.getUserName());

        if (user1 != null) {
            model.addAttribute("error", "用户名已存在！");
            model.addAttribute("user", user);
            return "/user/register";
        }
        user.setControlLevel(1);
        user.setPassword(EncodeUtils.encoderByMd5(user.getPassword()));
        userService.insert(user);
        User user2 = userService.queryByName(user.getUserName());
        setCookies(httpServletResponse, user2, 60 * 60 * 24);
        httpSession.setAttribute("control", user.getControlLevel());
        return "redirect: /book/allBook";
    }

    // 转到登录界面
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "user/login";
    }

    // 登录并设置cookie
    @RequestMapping("/login")
    public String login(String userName, String password, Model model, HttpServletResponse httpServletResponse, HttpSession httpSession) {
        User user = userService.queryByName(userName);
        if (user != null && user.getPassword().equals(EncodeUtils.encoderByMd5(password))) {
            setCookies(httpServletResponse, user, 60 * 60 * 24);
            httpSession.setAttribute("control", user.getControlLevel());
            return "redirect: /book/allBook";
        }
        model.addAttribute("error", "用户名或密码错误");
        return "/user/login";
    }

    //登出并回收cookie
    @RequestMapping("/logout")
    public String logout(HttpServletResponse httpServletResponse, User user, HttpSession httpSession) {

        setCookies(httpServletResponse, user, 0);
        httpSession.removeAttribute("control");
        return "redirect: /book/allBook";
    }

    //转到忘记密码页面
    @RequestMapping("/forgetPassword")
    public String forgetPassword() {
        return "user/forgetPassword";
    }

    //使用邮箱和手机号修改密码
    @RequestMapping("updatePassword")
    public String updatePassword(User user, Model model, HttpServletResponse httpServletResponse, HttpSession httpSession) {
        User user1 = userService.queryByName(user.getUserName());
        if (user1 == null) {
            model.addAttribute("error", "用户名不存在");
            return "/user/forgetPassword";
        }

        if (!user.getEmail().equals(user1.getEmail()) || !user.getTelephone().equals(user1.getTelephone())) {
            model.addAttribute("error", "手机号或者邮箱错误");
            return "/user/forgetPassword";
        }

        user1.setPassword(EncodeUtils.encoderByMd5(user.getPassword()));
        user1.setUserName(user.getUserName());
        userService.update(user1);

        setCookies(httpServletResponse, user1, 60 * 60 * 24);
        httpSession.setAttribute("control", user.getControlLevel());
        return "redirect: /book/allBook";
    }

    //用户主动修改信息，转到修改页面
    @RequestMapping("/toUpdateUser")
    public String toUpdateUser(Model model, int userID) {
        User user = userService.query(userID);
        model.addAttribute("user", user);

        return "user/updateUser";
    }

    //修改
    @RequestMapping("/updateUser")
    public String updateUser(User user, Model model, HttpServletResponse httpServletResponse, HttpSession httpSession) {
        User user1 = userService.queryByName(user.getUserName());

        if (user1 != null && !user.getUserName().equals(user1.getUserName())) {
            model.addAttribute("error", "用户名已存在！");
            model.addAttribute("user", user);
            return "/user/updateUser";
        }

        User user2 = userService.query(user.getUserID());
        user2.setUserName(user.getUserName());
        user2.setPassword(EncodeUtils.encoderByMd5(user.getPassword()));

        userService.update(user2);
        setCookies(httpServletResponse, user2, 60 * 60 * 24);
        httpSession.setAttribute("control", user.getControlLevel());
        return "redirect: /book/allBook";
    }

    //设置cookie
    private void setCookies(HttpServletResponse httpServletResponse, User user, int maxAge) {
        Cookie usr = new Cookie("userID", user.getUserID() + "");
        usr.setPath("/");
        usr.setDomain(domain);
        usr.setMaxAge(maxAge);//1 day
        httpServletResponse.addCookie(usr);

        Cookie userName = new Cookie("userName", user.getUserName());
        userName.setMaxAge(maxAge);
        userName.setPath("/");
        userName.setDomain(domain);
        httpServletResponse.addCookie(userName);
    }
}
