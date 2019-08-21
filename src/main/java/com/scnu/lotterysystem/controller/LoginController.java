package com.scnu.lotterysystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/* *
* 登录页的控制层设置
* */
@Controller
public class LoginController {

    @GetMapping({"/login","/"})
    public String loginPage(){
        return "login";
    }

    //发生账号密码错误时候
    @GetMapping("/login-error")
    public String errorMsg(Model model){
        model.addAttribute("loginError",true);
        model.addAttribute("errorMsg","账号密码错误!");
        return "login";
    }

    /* *
     * 该方法是根据不同用户权限跳转至不同用户所需界面
     * admin和user两种用户为主
     * */
    @GetMapping("/dispath")
    public String dispath(){
        //获取当前用户的权限
        Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities());

        //设置变量存储路径地址
        String s="";
        if(roles.contains("ROLE_ADMIN")){
            s="redirect:/admin";
        }else if(roles.contains("ROLE_USER")){
            s="forward:/setter/prize";
        }else if(roles.contains("ROLE_WINNER")){
            s="redirect:/winner/form";
        }else if(roles.contains("ROLE_VISITOR")){
            s="redirect:/lottery";
        }
        //根据权限跳转
        return s;
    }

    //获取该url添加用户名上去
    @GetMapping("/setter/prize")
    public String prizeSetter(){
        //获取当前用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return "forward:/setter/prize/"+currentPrincipalName;
    }

    @GetMapping("/setter/winner")
    public String winnerSetter(){
        //获取当前用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return "forward:/setter/winner/"+currentPrincipalName;
    }
}
