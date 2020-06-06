package controller;

import domain.Admin;
import domain.ResultInfo;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IAdminService;
import service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController{
    @Autowired
    IAdminService adminService;

    @RequestMapping("/login")
    public String getLoginPage(){
        return "X-admin/login";
    }

    @RequestMapping("/index")
    public String getHomePage(){
        return "X-admin/index";
    }

    @RequestMapping("/member-list")
    public String getMemberList(){
        return "X-admin/member-list";
    }

    @RequestMapping("/video-checked")
    public String getVideoChecked(){return "X-admin/video-checked";}

    @RequestMapping("/comment-checked")
    public String getCommentChecked(){return "X-admin/comment-checked";}

    @RequestMapping("/video-uncheck")
    public String getVideoUncheck(){return "X-admin/video-uncheck";}


    @RequestMapping("/changeName")
    public String getEditNamePage(){
        return "X-admin/update_aname";
    }

    @RequestMapping("/changePwd")
    public String getEditPwdPage(){
        return "X-admin/update_apwd";
    }

    @RequestMapping("/loginCheck")
    @ResponseBody
    public Object loginCheck(@RequestBody Map map, HttpServletRequest request){
        System.out.println("===============进入logincheck,"+map.toString());
        //获取传过来的账号密码
        String a_id = (String) map.get("adminid");
        String password = (String) map.get("password");

        Admin admin = adminService.findById(a_id);

        ResultInfo rs = new ResultInfo();

        if(admin==null){
            rs.setCode("000");
            rs.setMsg("登录失败，账号不存在");
            return rs;
        } else if(password.equals(admin.getA_password())){
            System.out.println("登录成功");
            rs.setCode("666");
            rs.setMsg("登录成功");
            rs.setObject(admin);
            request.getSession().setAttribute("LoginAdmin",admin);
            return rs;
        }else{
            rs.setCode("000");
            rs.setMsg("登录失败，密码错误");
            return rs;
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Object logout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        ResultInfo rs = new ResultInfo();
        rs.setCode("333");
        rs.setMsg("退出成功");
        return rs;
    }


    @RequestMapping("/getAdminInfo")
    @ResponseBody
    public Admin getAdminInfo(HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("LoginAdmin");
        admin.setA_password(null);
        return admin;
    }

    @RequestMapping("updateSession")
    @ResponseBody
    public void updateSession(HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("LoginAdmin");
        String a_id = admin.getA_id();
        Admin newAdmin = adminService.findById(a_id);
        request.getSession().removeAttribute("LoginAdmin");
        request.getSession().setAttribute("LoginAdmin",newAdmin);
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public int editPassword(){

        return 0;
    }

    @Override
    public IBaseService getService() {
        return adminService;
    }
}
