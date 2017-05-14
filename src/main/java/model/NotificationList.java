package model;

import java.util.ArrayList;

/**
 * Created by sdlds on 2017/5/14.
 */
public class NotificationList {
    private int code;
    private String message;
    private ArrayList<Notification> body;

    public NotificationList(){
        body = new ArrayList<Notification>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Notification> getBody() {
        return body;
    }

    public void setBody(ArrayList<Notification> body) {
        this.body = body;
    }
}
