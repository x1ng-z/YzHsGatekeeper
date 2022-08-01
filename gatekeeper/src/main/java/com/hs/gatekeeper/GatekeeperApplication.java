package com.hs.gatekeeper;

import com.hs.gatekeeper.components.ui.ClientUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class GatekeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatekeeperApplication.class, args);

        ClientUI.show_ClientUI();
    }

}
