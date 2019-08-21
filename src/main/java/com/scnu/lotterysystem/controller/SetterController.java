package com.scnu.lotterysystem.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scnu.lotterysystem.entity.Prize;
import com.scnu.lotterysystem.entity.User;
import com.scnu.lotterysystem.service.PrizeService;
import com.scnu.lotterysystem.service.UserService;
import com.scnu.lotterysystem.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/* *
 * 奖品设置者的控制层设置
 * 其中包含了奖品的增删改查
 * 还有对获奖者名单的查看和删除获奖者
 * */
@Controller
@RequestMapping("/setter")
public class SetterController {

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private WinnerService winnerService;

    @Autowired
    private UserDetailsService userDetailsService;

/*---------------------------------------------下面功能是奖品名单--------------------------------------------*/

    //建立奖品的list，注入到prizeModel中的prizeList中
    @GetMapping("/prize/{username}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView getAllPrice(@PathVariable("username") String username,
                                    Model model){
        User user = (User)userDetailsService.loadUserByUsername(username);
        //将user存储在model中
        model.addAttribute("user",user);
        //将所有返回的prize数据插入到model中
        model.addAttribute("prizeList",prizeService.findByUser(user));
        return new ModelAndView("prizeSetter/prizeList","prizeModel",model);
    }

    //进入添加的form表单
    @GetMapping("/prize/{username}/form")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView createForm(@PathVariable("username") String username,
                                   Model model){
        User user = (User)userDetailsService.loadUserByUsername(username);
        //将user存储在model中
        model.addAttribute("user",user);
        model.addAttribute("prizeForm",new Prize());
        model.addAttribute("prize",null);
        return new ModelAndView("prizeSetter/prizeForm","prizeModel",model);
    }

    //post请求进行保存prize
    @PostMapping("/prize/{username}/form")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView savePrize(@PathVariable("username") String username,
                                  Prize prize){
        User user = (User)userDetailsService.loadUserByUsername(username);
        prize.setUser(user);
        prizeService.savePrize(prize);
        return new ModelAndView("redirect:/setter/prize/"+username);
    }

    //获取修改prize界面
    @GetMapping("/prize/{username}/form/{id}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView toUpdatePrize(@PathVariable("id") Integer id,
                                      @PathVariable("username") String username,
                                      Model model){
        User user = (User)userDetailsService.loadUserByUsername(username);
        Prize prize=prizeService.getPrizeById(id);
        //将user存储在model中
        model.addAttribute("user",user);
        model.addAttribute("prize",prize);
        return new ModelAndView("prizeSetter/prizeForm","prizeModel",model);
    }

    //put请求进行修改prize
    @PutMapping("/prize/{username}/form")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView updatePrize(@PathVariable("username") String username,
                                    Prize prize){
        User user = (User)userDetailsService.loadUserByUsername(username);
        prize.setUser(user);
        prizeService.updatePrize(prize);
        return new ModelAndView("redirect:/setter/prize/"+username);
    }

    //删除prize
    @DeleteMapping("/prize/{username}/{id}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView deletePrize(@PathVariable("id") Integer id,
                                    @PathVariable("username") String username){
        prizeService.removePrize(id);
        return new ModelAndView("redirect:/setter/prize/"+username);
    }

/*---------------------------------------------下面功能是获奖者名单--------------------------------------------*/

    //来到获奖者表单，进行查看
    @GetMapping("/winner/{username}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView getAllWinner(Model model,
                                     @PathVariable("username") String username){
        User user = (User)userDetailsService.loadUserByUsername(username);
        //将user存储在model中
        model.addAttribute("user",user);
        model.addAttribute("winnerList",winnerService.findByUser(user));
        return new ModelAndView("prizeSetter/winnerList","winnerModel",model);
    }

    //进行删除操作，重定向到表单
    @DeleteMapping("/winner/{username}/{id}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView deleteWinner(@PathVariable("id") Integer id,
                                     @PathVariable("username") String username){
        winnerService.removeWinner(id);
        return new ModelAndView("redirect:/setter/winner/"+username);
    }
}
