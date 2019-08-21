package com.scnu.lotterysystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/* *
* 奖品实体类
* */
@Entity
@Table(name="prize")
//必须要有这一行注释，在prize时候会关联user，user又会关联authority，导致序列化出问题
@JsonIgnoreProperties( "user")
public class Prize  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增主键
    private Integer id;  //id

    @NotBlank(message = "奖品类型不能为空")   //放在string类上
    @Column(nullable = false,length=50)
    private String prizeLevel;  //奖品的类型,如一等奖，二等奖等

    @NotBlank(message = "奖品名字不能为空")   //放在string类上
    @Column(nullable = false,length=50)
    private String prizeName;  //奖品的名字

    @NotNull(message = "奖品的数量不能为空")   //放于基本类上
    @Column(nullable = false)
    private Integer prizeNum;   //奖品的数量

    @ManyToOne(targetEntity = User.class,cascade = CascadeType.DETACH , fetch = FetchType.EAGER)
//    @JoinColumn(name="user_id",nullable = false)
    @JoinTable(name="user_prize",joinColumns = @JoinColumn(name="prize_id",referencedColumnName = "id",unique = true),
               inverseJoinColumns = @JoinColumn(name="user_id",referencedColumnName = "id",nullable = false))
    private User user;

    public Prize(){
    }

    public Prize(String prizeLevel, String prizeName, Integer prizeNum) {
        this.prizeLevel = prizeLevel;
        this.prizeName = prizeName;
        this.prizeNum = prizeNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrizeLevel() {
        return prizeLevel;
    }

    public void setPrizeLevel(String prizeLevel) {
        this.prizeLevel = prizeLevel;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Integer getPrizeNum() {
        return prizeNum;
    }

    public void setPrizeNum(Integer prizeNum) {
        this.prizeNum = prizeNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "id=" + id +
                ", prizeName='" + prizeName + '\'' +
                ", prizeNum=" + prizeNum +
                ", user=" + user +
                '}';
    }
}
