package graduate;

import com.jfinal.core.Controller;
import crawler.NotificationCrawler;
import model.HttpCode;
import model.Notification;
import model.NotificationList;

import java.util.ArrayList;

/**
 * Created by sdlds on 2017/5/14.
 */
public class NotificationController extends Controller {
    public void index() {
        NotificationList list = new NotificationList();
        NotificationCrawler notification = new NotificationCrawler();
        ArrayList<Notification> body = new ArrayList<Notification>();
        body = notification.getNotification();
        if (body.size() != 0) {
            list.setCode(200);
            list.setMessage("");
            list.setBody(body);
        }
        else{
            list.setCode(500);
            list.setMessage("Internal Error");
            list.setBody(body);
        }
        renderJson(list);
    }
}
