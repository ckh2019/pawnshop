package cn.ckh2019.pawnshop.commons.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author Chen Kaihong
 * 2020-02-22 13:37
 */
@TableName("business")
@Data
public class Business {

    @TableId(type = IdType.AUTO)
    private Integer bid;

    private String phone;

    private String corporation;

    private String credit;

    private String email;

    /**
     * 店名
     */
    private String name;

    /**
     * 当铺logo
     */
    private String logo;

    @TableField("creditPhoto")
    private String creditPhoto;

    @TableField("idCardPhoto")
    private String idCardPhoto;

    private Integer status;

 /*   public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditPhoto() {
        return creditPhoto;
    }

    public void setCreditPhoto(String creditPhoto) {
        this.creditPhoto = creditPhoto;
    }

    public String getIdCardPhoto() {
        return idCardPhoto;
    }

    public void setIdCardPhoto(String idCardPhoto) {
        this.idCardPhoto = idCardPhoto;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Business{" +
                "bid=" + bid +
                ", phone='" + phone + '\'' +
                ", corporation='" + corporation + '\'' +
                ", credit='" + credit + '\'' +
                ", email='" + email + '\'' +
                ", creditPhoto='" + creditPhoto + '\'' +
                ", idCardPhoto='" + idCardPhoto + '\'' +
                ", status=" + status +
                '}';
    }*/
}
