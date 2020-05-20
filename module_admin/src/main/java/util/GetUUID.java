package util;

import java.util.UUID;

public class GetUUID {
    /**
     * djfldshffkfjkdj
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
