package crawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import model.Notification;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sdlds on 2017/5/14.
 */
public class NotificationCrawler {
    private static WebClient wc = new WebClient(BrowserVersion.CHROME);
    public static List<String> ua = new ArrayList<String>();
    private String baseUrl = "http://202.118.201.228";
    private String URL = "http://202.118.201.228/homepage/index.do";
    private HtmlPage page;

    public boolean getNotification(){
        try{
            setClient();
            page = wc.getPage(URL);
            Document doc = Jsoup.parse(page.asXml());
            ArrayList<Notification> notification = new ArrayList<Notification>();
            Elements results = doc.select(".fun_4_article_list").select(".body").select("tr");
            for(Element temp : results){
                Notification notificationTemp = new Notification();
                //System.out.println(temp.select("td").select("div").select("span").text().replace("【教务公告】",""));
                notificationTemp.setDate(temp.select("td").select("div").select("span").text().replace("【教务公告】","").replace(" ",""));
                //System.out.println(temp.select("td").select("div").select("a").attr("class_title"));
                notificationTemp.setTitle(temp.select("td").select("div").select("a").text().replace("\"","").replace(" ",""));
                notificationTemp.setUrl(baseUrl + temp.select("td").select("div").select("a").attr("href"));
                //System.out.println(baseUrl + temp.select("td").select("div").select("a").attr("href"));
                //System.out.println(notificationTemp.getDate() + notificationTemp.getTitle() + notificationTemp.getUrl());
            }

            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

//    private static void ReadUA() {
//        java.io.File file = new java.io.File("ua/user_agents");
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader(file));
//            String temp = "";
//            while ((temp = reader.readLine()) != null) {
//                ua.add(temp);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private static void setClient() throws Exception{
        Random random = new Random();
        //Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
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
}
