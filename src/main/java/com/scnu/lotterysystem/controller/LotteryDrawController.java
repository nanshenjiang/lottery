package com.scnu.lotterysystem.controller;

import com.scnu.lotterysystem.entity.Message;
import com.scnu.lotterysystem.entity.Prize;
import com.scnu.lotterysystem.entity.User;
import com.scnu.lotterysystem.service.PrizeService;
import com.scnu.lotterysystem.util.PrizeDrawUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Iterator;

/* *
* 抽奖控制层，即正在抽奖时候的页面控制
* */
@Controller
@RequestMapping("/lottery")
public class LotteryDrawController {
    @Autowired
    private PrizeService prizeService;

    @Autowired
    private PrizeDrawUtil prizeDrawUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/{username}")
    public ModelAndView mainPage(Model model,
                                 @PathVariable("username") String username) {
        User user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user",user);
        return new ModelAndView("lottery/main","userModel",model);
    }

    //synchronized是处理高并发
    @GetMapping("/{username}/start")
    public synchronized ModelAndView lotteryDraw(Model model,
                                                 @PathVariable("username") String username){
        User user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user",user);
        Collection<Prize> prizes=prizeService.findByUser(user);
        //遍历，假设不过审或者奖品数量为0便在collection中删除
        //由于collection遍历删除的时候有锁的问题，所以要使用迭代器
        for(Iterator<Prize> it = prizes.iterator(); it.hasNext();){
            if(it.next().getPrizeNum()==0){
                it.remove();
            }
        }
        Message message =prizeDrawUtil.luckStarPrizeDraw(prizes);
        //中奖了
        if(message.isVariable()==true){
            //通过奖品名字查询奖品，同时该奖品数量减一
            Prize prize = prizeService.findByPrizeName(message.getMessage());
            prizeService.minusPrize(prize);
            //将中奖信息注入到model中
            model.addAttribute("prizeInfo",true);
            model.addAttribute("prize",message.getMessage());
            return new ModelAndView("lottery/lotteryAnswer","prizeModel",model);
        }
        //没中奖
        model.addAttribute("prizeInfo",false);
        model.addAttribute("prize","未中奖");
        return new ModelAndView("lottery/lotteryAnswer","prizeModel",model);
    }
}
