package controller;

import domain.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IBaseService;
import service.IVideoService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("video")
public class VideoController extends BaseController{
    @Autowired
    IVideoService videoService;

    @RequestMapping("addPage")
    public String addPage(){
        return "X-admin/member-add";
    }

    @RequestMapping("uploadVideo")
    public String uploadVideo(){
        return "X-admin/video-upload";
    }

//    @RequestMapping("/getAdminInfo")
//    public String getAdminInfo(){
//
//        return "";
//    }

    @Override
    public IBaseService getService() {
        return videoService;
    }

    @RequestMapping("editPage")
    public String member_edit(HttpServletRequest request, String id){
        System.out.println("我进来了编辑页面，并且id是"+id);
        String v_id = id ;
        Video video = videoService.findById(v_id);
        System.out.println("[][][][][][][][][][]"+video.toString());
        request.getSession().setAttribute("editVideoInfo",video);
        return "X-admin/video-edit";
    }

    @RequestMapping("getVideoInfo")
    @ResponseBody
    public Map getVideoInfo(HttpServletRequest request){
        Map map = new HashMap();
        Video video = (Video) request.getSession().getAttribute("editVideoInfo");
        System.out.println("0000000000000000000000000000==="+video);
        map.put("code","1");
        map.put("msg","");
        map.put("data",video);
        System.out.println("-=-=-=---=-=-=-="+video.toString());
        return map;
    }


}
