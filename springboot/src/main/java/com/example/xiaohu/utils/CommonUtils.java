package com.example.xiaohu.utils;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class CommonUtils {
    public static Double round(int bit,double number){
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.setScale(bit,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String getUid(){
        String uid = UUID.randomUUID().toString();
        uid = uid.replaceAll("-","");
        return uid;
    }

    public static String getSalt(){
        String base = "qwertyuioplkjhgfdsazxcvbnmQAZWSXEDCRFVTGBYHNUJMIKLOP0123456789";
        StringBuffer sb = new StringBuffer();
        Random rd = new Random();
        for(int i=0;i<8;i++) {
            sb.append(base.charAt(rd.nextInt(base.length())));
        }
        String salt = sb.toString();
        return salt;
    }
}
