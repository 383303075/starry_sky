package util;

import java.util.Random;

public class GetAdminId {

    public static String getAdminId(){
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            rs.append(r.nextInt(10));
        }
        return rs.toString();
    }

}
