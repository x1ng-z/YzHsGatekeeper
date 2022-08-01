package gateway;

import gateway.ui.Bulk_ClientUI;
import gateway.netty.DealServer;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {


    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        ClassPathXmlApplicationContext ctx = getIOC();//new ClassPathXmlApplicationContext("application.xml");
        ctx.start();
        DealServer serverNetty = null;
        try {
            serverNetty = (DealServer) ctx.getBean("server_netty");
            service.execute(serverNetty);
        } catch (BeansException e) {
            logger.error(e);
        }
        Bulk_ClientUI.show_ClientUI();

    }

    synchronized static public ClassPathXmlApplicationContext getIOC() {
        if (ctx == null) {
            ctx = new ClassPathXmlApplicationContext("application.xml");
        } else {
            return ctx;
        }
        return ctx;
    }
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Client.class);

    private volatile static ClassPathXmlApplicationContext ctx = null;
}
