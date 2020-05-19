package util;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class VerificationCode {
    //放到session中key
    public static final String randomCodeKey = "randomcode_key";
    //随机产生的字符串
    private Random random = new Random();
    //字符串只能从以下字符中产生
    private String ranStr = "123456789abcdefghijklmnpqrstuvwxyz";
    //设置图片的长宽
    private int width = 120;
    private int height= 38;
    //设置干扰线数量
    private int lineSize = 40;
    //设置随机产生字符串的数量
    private int stringNum = 4;

    /*
    * 获得字体
    * */
    private Font getFont(){
        return new Font("宋体", Font.BOLD, 28);
    }

    /*
    * 获得颜色
    * */
    private Color getRandColor(int fc,int bc){
        if (fc>255)
            fc=255;
        if (bc>255)
            bc=255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }
    /*
    * 绘制干扰线
    * */
    private void drowLine(Graphics graphics){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int x1 = random.nextInt(13);
        int y1 = random.nextInt(15);
        graphics.drawLine(x,y,x+x1,y+y1);
    }
    /*
    * 获取随机的字符
    * */
    public String getRandomString(int num){
        return String.valueOf(ranStr.charAt(num));
    }
    /*
    * 绘制随机字符
    * */
    private String drowStr(Graphics graphics ,String randomString,int i){
        graphics.setFont(getFont());
        graphics.setColor(new Color(random.nextInt(101),
                                    random.nextInt(111),
                                    random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(ranStr.length())));
        randomString += rand;
        graphics.translate(random.nextInt(3),random.nextInt(3));
        graphics.drawString(rand,13*i,16);
        return randomString;
    }

    /*
    * 随机产生的图片
    * */

    public void getRandCode (HttpServletRequest request,HttpServletResponse response){
        //获取session
        HttpSession session = request.getSession();
        //BufferedImage类具有缓冲区的image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        //产生image对象的graphics对象，该对象可以在图像上进行各种绘画
        Graphics graphics = image.getGraphics();
        //绘制矩形边框
        graphics.fillRect(0,0,width,height);
        graphics.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,18));
        graphics.setColor(getRandColor(160,200));
        //绘制干扰线
        for (int i = 1;i<=lineSize;i++){
            drowLine(graphics);
        }
        //绘制随机字符
        String randomStr ="";
        for (int i=1; i<=stringNum;i++){
            randomStr = drowStr(graphics,randomStr,i);
        }
        //将生成的随机字符串保存到session中，
        //获得生成的验证码，然后跟用户输入的进行比较
        session.removeAttribute(randomCodeKey);
        session.setAttribute(randomCodeKey, randomStr);
        graphics.dispose();
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
