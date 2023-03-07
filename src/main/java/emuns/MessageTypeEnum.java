package emuns;

/**
 * @author XQL
 * @version 1.0.0
 * @ClassName MessageTypeEnum
 * @Description TODO
 * @createTime 2022年05月15日 15:31:00
 */
public enum MessageTypeEnum {
    TEXT(1, "text"),
    IMAGE(2, "image");
    private int code;
    private String desc;

    MessageTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
