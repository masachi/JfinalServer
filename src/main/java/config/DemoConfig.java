package config;

import com.jfinal.config.*;
import graduate.LoginController;
import graduate.NotificationController;
import graduate.TestController;
import model.Notification;

/**
 * Created by Masachi on 2017/5/14.å
 */
public class DemoConfig extends JFinalConfig {
        public void configConstant(Constants me) {
            me.setDevMode(true);
        }
        public void configRoute(Routes me) {
            me.add("/", TestController.class);
            me.add("/login", LoginController.class);
            me.add("/notification", NotificationController.class);
        }
        public void configPlugin(Plugins me) {

        }
        public void configInterceptor(Interceptors me) {

        }
        public void configHandler(Handlers me) {

        }

}
