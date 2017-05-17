package graduate;

import com.jfinal.core.Controller;
import config.DBconn;
import crawler.NotificationCrawler;
import model.HttpCode;
import ocr.AutoLogin;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Created by Masachi on 2017/5/12.
 */
public class LoginController extends Controller {
    public void index() {
        HttpCode code = new HttpCode();
        String username = getPara("username");
        String password = getPara("password");
//        if (notification.getNotification()) {
//            code.setCode(200);
//            code.setMessage("");
//        }
//        else{
//            code.setCode(500);
//            code.setMessage("Internal Error");
//        }

        try{
            Statement selectUser = DBconn.connection.createStatement();
            String sql = "select * from user where username = " + username;
            ResultSet resultSet = selectUser.executeQuery(sql);
            if(resultSet.next()){
                if(resultSet.getString("password").equals(string2MD5(password))) {
                    code.setCode(200);
                    code.setMessage("");
                }
                else{
                    code.setCode(500);
                    code.setMessage("Password Incorrect");
                }
            }
            else{
                code.setCode(500);
                code.setMessage("No User");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        renderJson(code);
    }

    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
