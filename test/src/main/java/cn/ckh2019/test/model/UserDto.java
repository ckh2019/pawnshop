package cn.ckh2019.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Chen Kaihong
 * 2020-02-12 14:36
 */
public class UserDto {

    private Integer id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
