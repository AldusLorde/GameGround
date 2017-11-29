package aldus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class QueryContext {

    HttpServletRequest request;

    private final HashMap<String,Object> requestAttributes = new HashMap<>();
    private final HashMap<String,String[]> requestParameters = new HashMap<>();
    private final HashMap<String,Object> sessionAttributes = new HashMap<>();
    private final HashMap<String,Object> responseAttriburse  = new HashMap<>();

    public QueryContext(HttpServletRequest request){
        this.request = request;
    }

    public HashMap<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    private void extractValues() throws InterruptedException {
        Thread a = new Thread(()->{
            Enumeration<String> ra = request.getAttributeNames();
            while(ra.hasMoreElements()){
                String name = ra.nextElement();
                requestAttributes.put(name,request.getAttribute(name));
            }
        });

        Thread b =new Thread(()->{
            Enumeration<String> rp = request.getParameterNames();
            while(rp.hasMoreElements()){
                String name = rp.nextElement();
                requestParameters.put(name,request.getParameterValues(name));
            }
        });
        Thread c =  new Thread(()->{
            HttpSession session = request.getSession(false);
            if(session!=null) {
                Enumeration<String> sa = session.getAttributeNames();
                while (sa.hasMoreElements()) {
                    String name = sa.nextElement();
                    sessionAttributes.put(name, session.getAttribute(name));
                }
            }
        });
        a.start();
        b.start();
        c.start();
        a.join();
        b.join();
        c.join();
    }

    public void insertToResponseMap(String n,Object o){
        //request.
    }

    public void insertAttributes(HttpServletRequest request){
        BiConsumer<String,Object> bicr = (a,b)->request.setAttribute(a,b);
        BiConsumer<String,Object> bics = (a,b)->request.getSession().setAttribute(a,b);
        requestAttributes.forEach(bicr);
        HttpSession session = request.getSession(false);
        if(session!=null) sessionAttributes.forEach(bics);
        requestAttributes.clear();
        sessionAttributes.clear();
        requestParameters.clear();
    }
}
