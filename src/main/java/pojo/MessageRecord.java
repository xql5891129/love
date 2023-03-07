package pojo;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @author XQL
 * @version 1.0.0
 * @ClassName MessageRecord.java
 * @Description TODO
 * @createTime 2022年05月13日 15:35:00
 */
@ApiModel(value = "MessageRecord",description = "消息记录表")
public class MessageRecord implements Serializable {
    private static final long serialVersionUID = 777489398091713539L;

    /**
     * 消息编号
     */
    @ApiModelProperty(name = "id",notes = "消息编号",dataType = "Integer",required = true)
    private Integer id;
    /**
     * 用户编号
     */
    @ApiModelProperty(name = "user_id",notes = "用户编号",dataType = "Integer",required = true)
    private Integer userId;
    /**
     * 消息类型
     */
    @ApiModelProperty(name = "messageType",notes = "消息类型",dataType = "Integer",required = true)
    private Integer messageType;
    /**
     * 消息内容
     */
    @ApiModelProperty(name = "content",notes = "消息内容",dataType = "String",required = true)
    private String content;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime",notes = "创建时间",dataType = "Date",required = true)
    private Date createTime;

    public MessageRecord(Integer id, Integer userId, Integer messageType, String content, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.messageType = messageType;
        this.content = content;
        this.createTime = createTime;
    }

    public static MessageRecordBuilder messageRecordBuilder(){
        return new MessageRecordBuilder();
    }

    public static class MessageRecordBuilder{
        private Integer id;
        private Integer userId;
        private Integer messageType;
        private String content;
        private Date createTime;

        public MessageRecordBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public MessageRecordBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public MessageRecordBuilder messageType(Integer messageType) {
            this.messageType = messageType;
            return this;
        }

        public MessageRecordBuilder content(String content) {
            this.content = content;
            return this;
        }

        public MessageRecordBuilder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public MessageRecord build(){
            return new MessageRecord(id, userId, messageType, content, createTime);
        }
    }


    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public Integer getUserId() {return userId;}

    public void setUserId(Integer userId) {this.userId = userId;}

    public Integer getMessageType() {return messageType;}

    public void setMessageType(Integer messageType) {this.messageType = messageType;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public Date getCreateTime() {return createTime;}

    public void setCreateTime(Date createTime) {this.createTime = createTime;}
}
