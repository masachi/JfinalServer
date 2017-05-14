package graduate;

import com.jfinal.core.Controller;
import crawler.NotificationCrawler;
import model.HttpCode;

/**
 * Created by sdlds on 2017/5/14.
 */
public class NotificationController extends Controller {
    public void index() {
        HttpCode code = new HttpCode();
        NotificationCrawler notification = new NotificationCrawler();
        if (notification.getNotification()) {
            code.setCode(200);
            code.setMessage("");
        }
        else{
            code.setCode(500);
            code.setMessage("Internal Error");
        }
        renderJson(code);
    }
}
