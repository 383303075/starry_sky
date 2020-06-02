package domain;

/**
 * 视频表
 */
public class Video {
    private String v_id;
    private String v_name;
    //上传者id
    private String v_authorId;
    //视频简介
    private String v_description;
    //点击量
    private int v_click;
    //上传日期
    private String v_date;
    //封面图片路径
    private String v_image;
    //获赞数
    private String v_praise;
    //审查者id（管理员）
    private String v_checkerId;
    //是否通过
    private String v_pass;
    //存放文件路径
    private String v_route;

    private String v_type;

    private String v_number;

    private String v_tv;

    public String getV_number() {
        return v_number;
    }

    public void setV_number(String v_number) {
        this.v_number = v_number;
    }

    public String getV_tv() {
        return v_tv;
    }

    public void setV_tv(String v_tv) {
        this.v_tv = v_tv;
    }

    public String getV_type() {
        return v_type;
    }

    public void setV_type(String v_type) {
        this.v_type = v_type;
    }

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public String getV_authorId() {
        return v_authorId;
    }

    public void setV_authorId(String v_authorId) {
        this.v_authorId = v_authorId;
    }

    public String getV_description() {
        return v_description;
    }

    public void setV_description(String v_description) {
        this.v_description = v_description;
    }

    public int getV_click() {
        return v_click;
    }

    public void setV_click(int v_click) {
        this.v_click = v_click;
    }

    public String getV_date() {
        return v_date;
    }

    public void setV_date(String v_date) {
        this.v_date = v_date;
    }

    public String getV_image() {
        return v_image;
    }

    public void setV_image(String v_image) {
        this.v_image = v_image;
    }

    public String getV_praise() {
        return v_praise;
    }

    public void setV_praise(String v_praise) {
        this.v_praise = v_praise;
    }

    public String getV_checkerId() {
        return v_checkerId;
    }

    public void setV_checkerId(String v_checkerId) {
        this.v_checkerId = v_checkerId;
    }

    public String getV_pass() {
        return v_pass;
    }

    public void setV_pass(String v_pass) {
        this.v_pass = v_pass;
    }

    public String getV_route() {
        return v_route;
    }

    public void setV_route(String v_route) {
        this.v_route = v_route;
    }

    @Override
    public String toString() {
        return "Video{" +
                "v_id='" + v_id + '\'' +
                ", v_name='" + v_name + '\'' +
                ", v_authorId='" + v_authorId + '\'' +
                ", v_description='" + v_description + '\'' +
                ", v_click=" + v_click +
                ", v_date='" + v_date + '\'' +
                ", v_image='" + v_image + '\'' +
                ", v_praise='" + v_praise + '\'' +
                ", v_checkerId='" + v_checkerId + '\'' +
                ", v_pass='" + v_pass + '\'' +
                ", v_route='" + v_route + '\'' +
                ", v_type='" + v_type + '\'' +
                ", v_number='" + v_number + '\'' +
                ", v_tv='" + v_tv + '\'' +
                '}';
    }
}
