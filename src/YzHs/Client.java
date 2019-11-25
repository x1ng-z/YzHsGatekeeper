package YzHs;

import UI.Bulk_ClientUI;
import YzHs.Bean.Vehicle_info;
import YzHs.Dao.VehicleMapper;
import YzHs.StartUp.DealServer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Client.class);

    private volatile static ClassPathXmlApplicationContext ctx=null;

    synchronized static public ClassPathXmlApplicationContext getIOC(){
        if(ctx==null){
            ctx = new ClassPathXmlApplicationContext("application.xml");
        }else {
            return ctx;
        }
        return ctx;
    }

    public static void main(String[] args) {
        ExecutorService service =Executors.newCachedThreadPool();
        ClassPathXmlApplicationContext ctx = getIOC();//new ClassPathXmlApplicationContext("application.xml");
        DealServer server_netty= null;
        try {
            server_netty = (DealServer)ctx.getBean("server_netty");
            service.execute(server_netty);
        } catch (BeansException e) {
            logger.error(e);
        }


        Bulk_ClientUI.show_ClientUI();

    }
}
