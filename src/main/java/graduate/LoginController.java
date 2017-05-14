package graduate;

import com.jfinal.core.Controller;
import model.HttpCode;
import ocr.AutoLogin;


/**
 * Created by Masachi on 2017/5/12.
 */
public class LoginController extends Controller {
    public void index() throws InterruptedException {
        HttpCode code = new HttpCode();
            String username = getPara("username");
            String password = getPara("password");
            AutoLogin autoLogin = new AutoLogin();
            autoLogin.loginToRemoteServer("1","2");
            code.setCode(200);
            code.setMessage("");
            renderJson(code);
    }
}
