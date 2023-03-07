package pojo;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @author XQL
 * @version 1.0.0
 * @ClassName user.java
 * @Description TODO
 * @createTime 2022年04月05日 21:34:00
 */
@ApiModel(value = "User",description = "用户表")
public class User implements Serializable {
    private static final long serialVersionUID = 777489398091713539L;
    /**
     * 用户编号
     */
    @ApiModelProperty(name = "id",notes = "用户编号",dataType = "Integer",required = true)
    private Integer id;
    /**
     * 用户昵称
     */
    @ApiModelProperty(name = "username",notes = "用户昵称",dataType = "String",required = true)
    private String username;
    /**
     * 用户密码
     */
    @ApiModelProperty(name = "password",notes = "用户密码",dataType = "String",required = true)
    private String password;
    /**
     * 用户性别
     */
    @ApiModelProperty(name = "sex",notes = "用户性别",dataType = "String",required = true)
    private String sex;
    /**
     * 用户电话
     */
    @ApiModelProperty(name = "tel",notes = "用户电话",dataType = "String",required = true)
    private String tel;
    /**
     * 用户邮箱
     */
    @ApiModelProperty(name = "email",notes = "用户邮箱",dataType = "String",required = true)
    private String email;
    /**
     * 用户是否配对
     */
    @ApiModelProperty(name = "isMate",notes = "用户是否配对",dataType = "Integer",required = true)
    private Integer isMate;
    /**
     * 用户头像
     */
    @ApiModelProperty(name = "avatar",notes = "用户头像",dataType = "String",required = true)
    private String avatar;
    /**
     * 最后登录时间
     */
    @ApiModelProperty(name = "lastTime",notes = "最后登录时间",dataType = "Date",required = true)
    private Date lastTime;


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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsMate() {
        return isMate;
    }

    public void setIsMate(Integer isMate) {
        this.isMate = isMate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", isMate=" + isMate +
                ", avatar='" + avatar + '\'' +
                ", lastTime=" + lastTime +
                '}';
    }
}