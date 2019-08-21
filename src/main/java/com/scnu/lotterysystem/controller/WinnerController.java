package com.scnu.lotterysystem.controller;

import com.scnu.lotterysystem.entity.User;
import com.scnu.lotterysystem.entity.Winner;
import com.scnu.lotterysystem.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/* *
 * 获奖者的控制层设置
 * 就是来到获奖者信息填写的form表单中
 * */
@Controller
@RequestMapping("/winner")
public class WinnerController {
    @Autowired
    private WinnerService winnerService;

    @Autowired
    private UserDetailsService userDetailsService;

    //获奖后来到添加信息表单
    @GetMapping("/{username}/form/{prize}")
    public ModelAndView toWinnerForm(Model model,
                                     @PathVariable("prize") String prize,
                                     @PathVariable("username") String username){
        User user = (User)userDetailsService.loadUserByUsername(username);
        Winner winner=new Winner();
        winner.setPrize(prize);
        model.addAttribute("winner",winner);
        model.addAttribute("user",user);
        return new ModelAndView("prizeWinner/winnerForm","winnerModel",model);
    }

    //进行添加信息，后跳转至结束界面
    @PostMapping("/{username}/form")
    public ModelAndView saveWinner(Winner winner,
                                   @PathVariable("username") String username){
        User user = (User)userDetailsService.loadUserByUsername(username);
        winner.setUser(user);
        winnerService.saveWinner(winner);
        return new ModelAndView("prizeWinner/endding");
    }
}
