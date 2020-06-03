package controller;

import domain.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IBaseService;
import service.IVideoService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("video")
public class VideoController extends BaseController{
    @Autowired
    IVideoService videoService;

    @RequestMapping("addPage")
    public String addPage(){
        return "X-admin/member-add";
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
    public String member_edit(HttpServletRequest request, String v_id){
        System.out.println("我进来了编辑页面，并且id是"+v_id);
        String id = v_id;
        Video video = videoService.findById(v_id);
        request.getSession().setAttribute("editVideoInfo",video);
        return "X-admin/video-edit";
    }
}
