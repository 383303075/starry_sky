package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IBaseService;
import service.IVideoService;

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
}
