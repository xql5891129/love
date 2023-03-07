package pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * mate
 * @author 
 */
public class Mate implements Serializable {
    /**
     * 配对编号
     */
    private Integer id;

    /**
     * 配对人编号
     */
    private Integer userId;

    /**
     * 被配对人编号
     */
    private Integer mateId;

    /**
     * 配对时间
     */
    private Timestamp time;

    private static final long serialVersionUID = 1L;

    public Mate(Integer id, Integer userId, Integer mateId, Timestamp time) {
        this.id = id;
        this.userId = userId;
        this.mateId = mateId;
        this.time = time;
    }

    public Mate(Integer id1, Integer id2, Timestamp time) {
        this.userId = id1;
        this.mateId = id2;
        this.time = time;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMateId() {
        return mateId;
    }

    public void setMateId(Integer mateId) {
        this.mateId = mateId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Mate{" +
                "id=" + id +
                ", userId=" + userId +
                ", mateId=" + mateId +
                ", time=" + time +
                '}';
    }
}