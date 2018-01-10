package QingXiao.util;

/**
 * Created by xpb on 2017/10/21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class IdFactory {


    /*
  生成每个用户注册时的唯一ID,UUID
  */
    public static  String getUUID(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        System.out.println("此次注册的UUID结果为"+str+","+temp);
        return  temp;
    }


    public String toString(){
        String result=null;
        InputStream inputStream = null;
        //inputStream = responseBody.byteStream();
        BufferedReader br = null;
        br=new BufferedReader(new InputStreamReader(inputStream));
        String string =null;
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        string=sb.toString();
        return string;
    }
}
