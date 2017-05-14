package graduate;

import com.jfinal.core.Controller;

/**
 * Created by Masachi on 2017/5/12.
 */
public class TestController extends Controller {
    public void index() {
        renderText("Hello Maven Jfinal");
    }
}
