package graduate;

import com.jfinal.core.Controller;
import crawler.NotificationCrawler;
import model.HttpCode;
import ocr.AutoLogin;


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
        renderJson(code);
    }
}
