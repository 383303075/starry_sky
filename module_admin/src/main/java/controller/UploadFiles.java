package controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("upload")
public class UploadFiles {

    @RequestMapping(value = "/uploadVideo",method = RequestMethod.POST)
    @ResponseBody
    public Map uploadVideo(@RequestParam("file") MultipartFile files[], HttpServletRequest request){
        //上传路径：读取当前controller的绝对路径，看是否存在一个叫做uploadfiles的文件或者文件夹
        String path = request.getServletContext().getRealPath("uploadfiles");
        System.out.println("文件上传路径是："+path);
        //将读取的文件或者文件夹实例化/初始化为java的对象，使得我们能够获得文件/文件夹的相关信息
        File uploadfiles = new File(path);
        //判断该文件/或者文件夹存不存在：不存在则创建
        if (!uploadfiles.exists()){
            uploadfiles.mkdir();
        }
        System.out.println("==========="+files.length);
        Map resultMap  = new HashMap();//初始化返回的结果
        //初始化一个list用来装载上传上来的文件
        List<String> uploadFiles = new ArrayList<String>();
        //初始化一个文件名字
        String newFileName = "";
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getOriginalFilename();
            System.out.println("原始文件名:" + fileName);
            /*
            UUID.randomUUID()是jdk提供的生成随机字符串的方法
            该方法会生成一个带"-"符号的16进制的32位字符串
            所以需要把字符串中的"-"去掉，使用replace()方法
             */
            //给上传上来的文件重新取一个名字
            newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            newFileName = newFileName.replace("-", "");
            System.out.println("上传后文件名:" + newFileName);
            //上传的文件不为空，我们就开始复制文件
            if (!files[i].isEmpty()) {
                try {
                    //创建一个文件输出流的对象
                    FileOutputStream fileOutputStream = new FileOutputStream(path + "/" + newFileName);
                    //创建一个文件输入对象：将我们的上传的文件读入到程序中
                    InputStream in = files[i].getInputStream();
                    byte[] bytes = new byte[2048];//1024*2
                    int b = 0;
                    while ((b = in.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0, b);
                    }
                    fileOutputStream.close();
                    in.close();
                    resultMap.put("code","0");
                    resultMap.put("msg","上传成功");
                    resultMap.put("path","/uploadfiles/"+newFileName);
                } catch (FileNotFoundException e) {
                    resultMap.put("code","1");
                    resultMap.put("msg","上传失败");
                    e.printStackTrace();
                } catch (IOException e) {
                    resultMap.put("code","1");
                    resultMap.put("msg","上传失败");
                    e.printStackTrace();
                }
            }
            uploadFiles.add(newFileName);
        }
        return resultMap;
//        return "/uploadfiles/"+newFileName;
    }




    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public Map uploadImage(@RequestParam("file") MultipartFile files[], HttpServletRequest request){
        //上传路径：读取当前controller的绝对路径，看是否存在一个叫做uploadfiles的文件或者文件夹
        String path = request.getServletContext().getRealPath("user_icon");
        System.out.println("头像上传路径是："+path);
        //将读取的文件或者文件夹实例化/初始化为java的对象，使得我们能够获得文件/文件夹的相关信息
        File uploadImages = new File(path);
        //判断该文件/或者文件夹存不存在：不存在则创建
        if (!uploadImages.exists()){
            uploadImages.mkdir();
        }
        System.out.println("==========="+files.length);
        Map resultMap  = new HashMap();//初始化返回的结果
        //初始化一个list用来装载上传上来的文件
        List<String> uploadFiles = new ArrayList<String>();
        //初始化一个文件名字
        String newFileName = "";
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getOriginalFilename();
            System.out.println("原始头像文件名:" + fileName);
            /*
            UUID.randomUUID()是jdk提供的生成随机字符串的方法
            该方法会生成一个带"-"符号的16进制的32位字符串
            所以需要把字符串中的"-"去掉，使用replace()方法
             */
            //给上传上来的文件重新取一个名字
            newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            newFileName = newFileName.replace("-", "");
            System.out.println("上传后文件名:" + newFileName);
            //上传的文件不为空，我们就开始复制文件
            if (!files[i].isEmpty()) {
                try {
                    //创建一个文件输出流的对象
                    FileOutputStream fileOutputStream = new FileOutputStream(path + "/" + newFileName);
                    //创建一个文件输入对象：将我们的上传的文件读入到程序中
                    InputStream in = files[i].getInputStream();
                    byte[] bytes = new byte[2048];//1024*2
                    int b = 0;
                    while ((b = in.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0, b);
                    }
                    fileOutputStream.close();
                    in.close();
                    resultMap.put("code","0");
                    resultMap.put("msg","上传成功");
                    resultMap.put("path","/user_icon/"+newFileName);
                } catch (FileNotFoundException e) {
                    resultMap.put("code","1");
                    resultMap.put("msg","上传失败");
                    e.printStackTrace();
                } catch (IOException e) {
                    resultMap.put("code","1");
                    resultMap.put("msg","上传失败");
                    e.printStackTrace();
                }
            }
            uploadFiles.add(newFileName);
        }
        return resultMap;
//        return "/uploadfiles/"+newFileName;
    }
}
