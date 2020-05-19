package domain;

/**
 * 回复表
 */
public class Reply {
    private String r_id;
    private String v_id;
    private String r_authorId;
    //获赞数
    private String r_praise;
    //回复内容
    private String r_content;
    private String r_date;

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getR_authorId() {
        return r_authorId;
    }

    public void setR_authorId(String r_authorId) {
        this.r_authorId = r_authorId;
    }

    public String getR_praise() {
        return r_praise;
    }

    public void setR_praise(String r_praise) {
        this.r_praise = r_praise;
    }

    public String getR_content() {
        return r_content;
    }

    public void setR_content(String r_content) {
        this.r_content = r_content;
    }

    public String getR_date() {
        return r_date;
    }

    public void setR_date(String r_date) {
        this.r_date = r_date;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "r_id='" + r_id + '\'' +
                ", v_id='" + v_id + '\'' +
                ", r_authorId='" + r_authorId + '\'' +
                ", r_praise='" + r_praise + '\'' +
                ", r_content='" + r_content + '\'' +
                ", r_date='" + r_date + '\'' +
                '}';
    }
}
