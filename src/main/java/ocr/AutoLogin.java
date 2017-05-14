package ocr;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Masachi on 2017/4/18.
 */
public class AutoLogin {
    private static WebClient wc = new WebClient(BrowserVersion.CHROME);
    public static List<String> ua = new ArrayList<String>();
    private String URL = "http://202.118.201.228/academic/common/security/login.jsp";
    private HtmlPage page;

    private static void ReadUA() {
        java.io.File file = new java.io.File("ua/user_agents");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String temp = "";
            while ((temp = reader.readLine()) != null) {
                ua.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setClient() throws Exception{
        Random random = new Random();
        Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true
        wc.getOptions().setCssEnabled(false); //禁用css支持
//        wc.getOptions().setProxyConfig(new ProxyConfig("185.10.17.134",3128));
        wc.getCookieManager().setCookiesEnabled(true);
        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        wc.getOptions().setPrintContentOnFailingStatusCode(false);
        wc.getOptions().setTimeout(100000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待

        wc.waitForBackgroundJavaScript(600*1000);
        wc.setAjaxController(new NicelyResynchronizingAjaxController());

        wc.waitForBackgroundJavaScript(1000*3);
        wc.setJavaScriptTimeout(0);
        //wc.addRequestHeader("User-Agent", ua.get(random.nextInt(9800)));
//        System.out.println(page);
    }

    public String loginToRemoteServer(String username,String password){
        String result = "";
        try {
            setClient();
            getPage();
            HtmlTextInput usernameElement = page.getElementByName("j_username");
            HtmlPasswordInput passwordElement = page.getElementByName("j_password");
            HtmlTextInput captureElement = page.getElementByName("j_captcha");
            HtmlElement loginButton = page.getElementByName("button1");
            HtmlImage captureImg = (HtmlImage) page.getElementByName("jcaptcha");
            // TODO: 2017/4/4 htmlimage下载成图片然后OCR
            captureImg.saveAs(new File("file/temp.jpg"));
            System.out.println(VerifyCode.getVerifyCode());
            usernameElement.focus();
            usernameElement.type(username);
            passwordElement.focus();
            passwordElement.type(password);
            captureElement.focus();
            captureElement.type(VerifyCode.getVerifyCode().replace(" ",""));
            HtmlPage realPage = (HtmlPage) loginButton.click();
            wc.waitForBackgroundJavaScript(100000);
            System.out.println(realPage.getTitleText());
            return realPage.getTitleText();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private void getPage(){
        try{
            page = wc.getPage(URL);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
