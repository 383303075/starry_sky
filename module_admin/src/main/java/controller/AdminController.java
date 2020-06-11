package controller;

import domain.Admin;
import domain.ResultInfo;
import domain.User;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IAdminService;
import service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @RequestMapping("/welcome")
    public String getWelcomePage(){
        return "X-admin/welcome";
    }

    @RequestMapping("/member-list")
    public String getMemberList(){
        return "X-admin/member-list";
    }

    @RequestMapping("/video-checked")
    public String getVideoChecked(){return "X-admin/video-checked";}

    @RequestMapping("/comment-checked")
    public String getCommentChecked(){return "X-admin/comment-checked";}

    @RequestMapping("/b")
    public String getReply(){
        return "X-admin/reply-checked";
    }

    @RequestMapping("/video-uncheck")
    public String getVideoUncheck(){return "X-admin/video-uncheck";}

    @RequestMapping("/admin-list")
    public String getAdminList(){return "X-admin/admin-list";}

    @RequestMapping("/changeName")
    public String getEditNamePage(){
        return "X-admin/update_aname";
    }

    @RequestMapping("/changePwd")
    public String getEditPwdPage(){
        return "X-admin/update_apwd";
    }

    @RequestMapping("/addPage")
    public String getAddPage(){
        return "X-admin/admin-add";
    }

    @RequestMapping("/editPage")
    public String member_edit(HttpServletRequest request,String id){
        System.out.println("我进来了编辑页面，并且id是"+id);
        String a_id = id;
        Admin admin = adminService.findById(a_id);
        request.getSession().setAttribute("editAdminInfo",admin);
        return "X-admin/admin-edit";
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
        } else if(password.equals(admin.getA_password())&&!admin.getA_available().equals("未启用")){
            System.out.println("登录成功");
            rs.setCode("666");
            rs.setMsg("登录成功");
            rs.setObject(admin);
            request.getSession().removeAttribute("LoginAdmin");
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
        rs.setCode("3");
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



    @RequestMapping("/updateSession")
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
    public Map editPassword(@RequestBody Map params,HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("LoginAdmin");
        System.out.println(admin.toString());
        String a_id = admin.getA_id();
        String param_id = (String) params.get("a_id");
        String old_pwd = (String) params.get("old_password");

        Map resultMap = new HashMap();

        if(!a_id.equals(param_id)) {
            resultMap.put("code", "0");
            resultMap.put("msg", "登录账户与修改账户不一致！请清除缓存后重试~");
            return resultMap;
        }

        if(!admin.getA_password().equals(old_pwd)){
            resultMap.put("code", "0");
            resultMap.put("msg", "密码错误");
            return resultMap;
        }

        int flag = adminService.editRow(params);

        if(flag > 0){
            resultMap.put("code","1");
            resultMap.put("msg","修改成功");
        }else{
            resultMap.put("code","0");
            resultMap.put("msg","修改失败");
        }
        return resultMap;
    }

    @RequestMapping("/deleteOneAdmin")
    @ResponseBody
    public  Map  deleteOneAdmin(HttpServletRequest request, @RequestBody String id){//1、通过requestBody接受参数
        System.out.println("============================================进入到删除方法");
        Admin loginAdmin = (Admin) request.getSession().getAttribute("LoginAdmin");
        Admin delAdmin = adminService.findById(id);
        String l_grant = loginAdmin.getA_grant();
        String a_grant = delAdmin.getA_grant();
        Map  resultMap  = new HashMap();



        if(!l_grant.equals("1")){
            resultMap.put("code","0");
            resultMap.put("msg","您无权进行管理员删除操作！");
            return resultMap;
        }else if(a_grant.equals("1")){
            resultMap.put("code","0");
            resultMap.put("msg","不可删除一级管理员！");
            return resultMap;
        }
        //2、调用后台业务逻辑
        int delFlag = getService().deleteOne(id);
        //3、处理返回数据

        if(delFlag > 0){
            resultMap.put("code","1");
            resultMap.put("msg","删除成功");
        }else{
            resultMap.put("code","0");
            resultMap.put("msg","删除失败");
        }
        return  resultMap;
    }

    @RequestMapping("/adminSwitch")
    @ResponseBody
    public Map switchAvailable(@RequestBody String id){
        String a_id = id;
        String a_available;
        Map resultMap = new HashMap();
        Map params = new HashMap();
        int flag;
        Admin admin = adminService.findById(a_id);

        if(admin.getA_grant().equals("1")){
            resultMap.put("code","0");
            resultMap.put("msg","禁止将一级管理员设为停用！");
            return resultMap;
        }

        params.put("a_id",a_id);
        if(admin!=null){
            if(admin.getA_available().equals("未启用")){
                a_available = "已启用";
                params.put("a_available",a_available);
                flag = adminService.editRow(params);
            }else{
                a_available = "未启用";
                params.put("a_available",a_available);
                flag = adminService.editRow(params);
            }
            if(flag>0){
                resultMap.put("code","1");
                resultMap.put("msg","切换成功");
            }
        }else{
            resultMap.put("code","0");
            resultMap.put("msg","切换失败");
        }
        return  resultMap;
    }

    @RequestMapping("/duplicateCheck")
    @ResponseBody
    public Map nameCheck(@RequestBody Map params) {
        System.out.println(params.toString());
        Map resultMap = new HashMap();
        Admin admin = adminService.findByName((String) params.get("a_name"));
        if (admin != null) {
            resultMap.put("code", "0");
            resultMap.put("msg", "该昵称已被占用，请重新输入");
        } else {
            resultMap.put("code", "1");
            resultMap.put("msg", "昵称可用");
        }
        return resultMap;
    }

    @RequestMapping("/getEditAdminInfo")
    @ResponseBody
    public Map getUserInfo(HttpServletRequest request){
        Map resultMap = new HashMap();
        Admin admin = (Admin) request.getSession().getAttribute("editAdminInfo");
        request.getSession().removeAttribute("editAdminInfo");
        if(admin==null){
            resultMap.put("code","0");
            resultMap.put("msg","查找信息失败");
            return resultMap;
        }
        resultMap.put("code","1");
        resultMap.put("msg","");
        admin.setA_password(null);
        resultMap.put("data",admin);
        System.out.println("-=-=-=---=-=-=-="+admin.toString());
        return resultMap;
    }

    @RequestMapping("/editOneAdmin")
    @ResponseBody
    public  Map  editOneAdmin(HttpServletRequest request, @RequestBody Map params){//1、通过requestBody接受参数
        System.out.println("============================================进入到修改方法");
        Admin admin = (Admin) request.getSession().getAttribute("LoginAdmin");
        String a_grant = admin.getA_grant();
        Map  resultMap  = new HashMap();

        if(!a_grant.equals("1")){
            resultMap.put("code","0");
            resultMap.put("msg","您无权对其他管理员账户进行更改操作！");
            return resultMap;
        }
        //2、调用后台业务逻辑
        int delFlag = getService().editRow(params);
        //3、处理返回数据

        if(delFlag > 0){
            resultMap.put("code","1");
            resultMap.put("msg","修改成功");
        }else{
            resultMap.put("code","0");
            resultMap.put("msg","修改失败");
        }
        return  resultMap;
    }



        @Override
    public IBaseService getService() {
        return adminService;
    }
}
