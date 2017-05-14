package graduate;

import com.jfinal.core.Controller;
import model.HttpCode;

/**
 * Created by sdlds on 2017/5/14.
 */
public class VerifyController extends Controller{
    public void index(){
        HttpCode code = new HttpCode();
        code.setCode(200);
        code.setMessage("");
    }
}
