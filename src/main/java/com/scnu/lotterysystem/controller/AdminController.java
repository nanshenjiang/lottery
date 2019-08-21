package com.scnu.lotterysystem.controller;

import com.scnu.lotterysystem.entity.Authority;
import com.scnu.lotterysystem.entity.User;
import com.scnu.lotterysystem.service.AuthorityService;
import com.scnu.lotterysystem.service.PrizeService;
import com.scnu.lotterysystem.service.UserService;
import com.scnu.lotterysystem.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* *
 * 管理者的控制层设置
 * 其中包括了对user的增删改查
 * */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private PrizeService prizeService;
    @Autowired
    private WinnerService winnerService;

/*---------------------------------------------下面功能是管理使用者--------------------------------------------*/
    //建立user的list，注入到userModel中的userList中
    @GetMapping
    public ModelAndView getAllUser(Model model){
        //将所有返回的user数据插入到model中
        model.addAttribute("userList",userService.findAll());
        return new ModelAndView("admin/userList","userModel",model);
    }

    //进入添加或者修改的form表单
    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        model.addAttribute("userForm",new User());
        model.addAttribute("user",null);
        return new ModelAndView("admin/userForm","userModel",model);
    }

    //post请求进行保存user
    @PostMapping("/form")
    public ModelAndView saveUser(User user,Integer authorityId){
        List<Authority> authorities=new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);
        userService.saveOrUpdateUser(user);
        return new ModelAndView("redirect:/admin");
    }

    //获取修改界面
    @GetMapping("/form/{id}")
    public ModelAndView toUpdateUser(@PathVariable("id") Integer id,
                                     Model model){
        User user=userService.findById(id);
        model.addAttribute("user",user);
        return new ModelAndView("admin/userForm","userModel",model);
    }

    //put请求进行修改user
    @PutMapping("/form")
    public ModelAndView updateUser(User user,Integer authorityId){
        List<Authority> authorities=new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);
        userService.saveOrUpdateUser(user);
        return new ModelAndView("redirect:/admin");
    }

    //删除user
    @DeleteMapping("{id}")
    public ModelAndView deleteUser(@PathVariable("id") Integer id){
        userService.deleteById(id);
        return new ModelAndView("redirect:/admin");
    }

/*---------------------------------------------下面功能是奖品名单--------------------------------------------*/
    //建立奖品的list，注入到prizeModel中的prizeList中
    @GetMapping("/prize")
    public ModelAndView getAllPrice(Model model){
        //将所有返回的prize数据插入到model中
        model.addAttribute("prizeList",prizeService.findAll());
        return new ModelAndView("admin/prizeList","prizeModel",model);
    }

/*---------------------------------------------下面功能是获奖者名单--------------------------------------------*/
    //来到获奖者表单，进行查看
    @GetMapping("/winner")
    public ModelAndView getAllWinner(Model model){
        model.addAttribute("winnerList",winnerService.findAll());
        return new ModelAndView("admin/winnerList","winnerModel",model);
    }
}
