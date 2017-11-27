package aldus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class
SessionRequestContent {
    private HashMap<String,Object> requestAttributes = new HashMap<>();
    private HashMap<String,String[]> requestParameters = new HashMap<>();
    private HashMap<String,Object> sessionAttributes = new HashMap<>();
    public HashMap<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void extractValues(HttpServletRequest request){
        new Thread(()->{
            Enumeration<String> ra = request.getAttributeNames();
            while(ra.hasMoreElements()){
                String name = ra.nextElement();
                requestAttributes.put(name,request.getAttribute(name));
            }
        }).start();
        new Thread(()->{
            Enumeration<String> rp = request.getParameterNames();
            while(rp.hasMoreElements()){
                String name = rp.nextElement();
                requestParameters.put(name,request.getParameterValues(name));
            }
        }).start();
        new Thread(()->{
            HttpSession session = request.getSession(false);
            if(session!=null) {
                Enumeration<String> sa = session.getAttributeNames();
                while (sa.hasMoreElements()) {
                    String name = sa.nextElement();
                    sessionAttributes.put(name, session.getAttribute(name));
                }
            }
        }).start();
    }

    public void insertAttributes(HttpServletRequest request){
        BiConsumer<String,Object> bicr = (a,b)->request.setAttribute(a,b);
        BiConsumer<String,Object> bics = (a,b)->request.getSession().setAttribute(a,b);
        requestAttributes.forEach(bicr);
        HttpSession session = request.getSession(false);
        if(session!=null) sessionAttributes.forEach(bics);
    }
}
