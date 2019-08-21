package com.scnu.lotterysystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/* *
* 获奖者实体类
* */
@Entity
@Table(name = "winner")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})：該註解用於取消綁定json
public class Winner  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable = false,length=20)
    private String winnerName;  //中奖者名字

    @NotNull
    @Column(nullable = false)
    private Integer winnerPhone;  //联系电话

    @NotBlank
    @Column(nullable = false,length=254)
    private String winnerAddress;  //家庭地址

    @Column
    private String prize;  //与prize关联

    @ManyToOne(targetEntity = User.class,cascade = CascadeType.DETACH , fetch = FetchType.EAGER)
    @JoinTable(name="user_winner",joinColumns = @JoinColumn(name="winner_id",referencedColumnName = "id",unique = true),
            inverseJoinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"))
    private User user;

    public Winner(){
    }

    public Winner(String winnerName,Integer winnerPhone, String winnerAddress) {
        this.winnerName = winnerName;
        this.winnerPhone=winnerPhone;
        this.winnerAddress = winnerAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public Integer getWinnerPhone() {
        return winnerPhone;
    }

    public void setWinnerPhone(Integer winnerPhone) {
        this.winnerPhone = winnerPhone;
    }

    public String getWinnerAddress() {
        return winnerAddress;
    }

    public void setWinnerAddress(String winnerAddress) {
        this.winnerAddress = winnerAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "id=" + id +
                ", winnerName='" + winnerName + '\'' +
                ", winnerPhone=" + winnerPhone +
                ", winnerAddress='" + winnerAddress + '\'' +
                ", prize='" + prize + '\'' +
                ", user=" + user +
                '}';
    }
}
