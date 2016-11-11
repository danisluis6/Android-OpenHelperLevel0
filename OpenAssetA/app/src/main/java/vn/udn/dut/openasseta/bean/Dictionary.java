package vn.udn.dut.openasseta.bean;

/**
 * Created by vuongluis on 11/11/2016.
 */

public class Dictionary {
    private int id;
    private String code;
    private String content;

    public Dictionary() {
    }

    public Dictionary(int id, String code, String content) {
        this.id = id;
        this.code = code;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
