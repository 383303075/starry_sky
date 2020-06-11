package domain;

/**
 * 评论表
 */
public class Comment {
    private String c_id;
    private String v_id;
    private String c_authorId;
    private String c_praise;
    private String c_content;
    private String c_date;

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getC_authorId() {
        return c_authorId;
    }

    public void setC_authorId(String c_authorId) {
        this.c_authorId = c_authorId;
    }

    public String getC_praise() {
        return c_praise;
    }

    public void setC_praise(String c_praise) {
        this.c_praise = c_praise;
    }

    public String getC_content() {
        return c_content;
    }

    public void setC_content(String c_content) {
        this.c_content = c_content;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "c_id='" + c_id + '\'' +
                ", v_id='" + v_id + '\'' +
                ", c_authorId='" + c_authorId + '\'' +
                ", c_praise='" + c_praise + '\'' +
                ", c_content='" + c_content + '\'' +
                ", c_date='" + c_date + '\'' +
                '}';
    }
}
