package domain;

public class Admin {
    private String a_id;
    private String a_name;
    private String a_password;
    private String a_grant;

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_password() {
        return a_password;
    }

    public void setA_password(String a_password) {
        this.a_password = a_password;
    }

    public String getA_grant() {
        return a_grant;
    }

    public void setA_grant(String a_grant) {
        this.a_grant = a_grant;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "a_id='" + a_id + '\'' +
                ", a_name='" + a_name + '\'' +
                ", a_password='" + a_password + '\'' +
                ", a_grant='" + a_grant + '\'' +
                '}';
    }
}
