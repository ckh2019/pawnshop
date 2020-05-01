package cn.ckh2019.pawnshop.commons.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Chen Kaihong
 * 2020-02-13 22:23
 */

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private String phone;
    private String email;
    private String password;
    private String role;

}
