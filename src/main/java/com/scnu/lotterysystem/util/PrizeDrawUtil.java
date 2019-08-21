package com.scnu.lotterysystem.util;

import com.scnu.lotterysystem.entity.Message;
import com.scnu.lotterysystem.entity.Prize;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Random;

/* *
* 辅助函数，用于进行抽奖的函数
* 传入数据库中通过审核的奖品集合
* 返回是否中奖，及中几等奖的信息
* */
@Component  //注入bean
public class PrizeDrawUtil {

    //抽奖系统，根据传入的奖品list进行抽奖
    //返回的数据有中奖奖品和未中奖
    public Message luckStarPrizeDraw(Collection<Prize> prizes){
        //记录所有prize数量
//        int prize_num=0;
//        Random r=new Random();
//        int size=prizes.size();
//        for(Prize prize:prizes){
//            prize_num=prize.getPrizeNum();
//        }
//        //在区间*10000中随机抽取一个数，该数代表获奖
//        int randPrizeNum=r.nextInt(prize_num*10000);
//        //求得平均每个区间的数量
//        int averageNum=prize_num*10000/size;
//        //由于下方需要和averageNum进行运算，数值会变，新增一个数代替
//        int calculationNum=averageNum;
//        for(Prize prize:prizes){
//            //在区间内随机取一个数，若与randPrize相等，则获奖
//            int rPrize=r.nextInt(calculationNum-prize.getPrizeNum());
//            //中奖直接返回
//            if(randPrizeNum>=rPrize&&randPrizeNum<=(rPrize+prize.getPrizeNum())){
//                return new Message(true,prize.getPrizeName());
//            }
//            //否则继续随机抽奖
//            calculationNum+=averageNum;
//        }
//        return new Message(false,"未中奖");
        //用于测试，假设中奖并返回中奖信息
        return new Message(true,"固态硬盘");
    }

}
