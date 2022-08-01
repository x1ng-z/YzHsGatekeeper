package com.hs.gatekeeper.components.ui;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


@Slf4j
public class ClientUI {
    static {
        System. setProperty("java.awt.headless", "false");
    }

    private static TrayIcon trayIcon = null;
    static JFrame mf ;//= new JFrame();
    static SystemTray tray ;//= SystemTray.getSystemTray();
    private static JFormattedTextField max_load;
    private static JRadioButton manuButton;
    private static JRadioButton historyButton;
    private static JFormattedTextField max_load_count;
    private static JFormattedTextField invalid_load_time;
    private static JFormattedTextField invalid_load_weight;


    public static void show_ClientUI() {

//        mf.setLocation(300, 100);
        //mf.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        mf = new JFrame();
        tray = SystemTray.getSystemTray();
        mf.setSize(500, 300);
        mf.setTitle("进出厂车辆管控系统");
        JPanel pane = new JPanel();
        mf.add(pane);
        pane.setBackground(new Color(6, 119, 203));
        pane.setBounds(0, 0, 500, 300);
        pane.setLayout(null);
        mf.setResizable(false);

        mf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        try {

            Image logoimage = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + "\\conf\\img\\garage.png");
            mf.setIconImage(logoimage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
//            LogMgr.getLogMgr_instance().WirteError(e);
        }


        JLabel label1 = new JLabel("进出厂车辆管控系统", JLabel.CENTER);
        label1.setFont(new Font("宋体", Font.CENTER_BASELINE, 40));//创建标签
        label1.setBounds(0, 0, 500, 300);
        label1.setBackground(new Color(203, 198, 45));
        label1.setVisible(true);
        pane.add(label1);

        mf.setVisible(true);

        mf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "确定要退出系统吗？", "退出系统", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    log.info("End client");
                    System.exit(0);
                }
            }

            @Override
            public void windowIconified(WindowEvent e) { // 窗口最小化事件
                mf.setVisible(false);
                miniTray();
            }

        });

    }

    private static void miniTray() { // 窗口最小化到任务栏托盘

//        String rootpath = System.getProperty("user.dir");
        ClassPathResource resource = new ClassPathResource("static/img/garage.png");
        try {

            ImageIcon trayImg = new ImageIcon(resource.getURL());// 托盘图标

            trayIcon = new TrayIcon(trayImg.getImage(), "GateKeeper", new PopupMenu());
            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    if (e.getClickCount() == 2) {// 单击 1 双击 2

                        tray.remove(trayIcon);
                        mf.setVisible(true);
                        mf.setExtendedState(JFrame.NORMAL);
                        mf.toFront();
                    }

                }

            });

            tray.add(trayIcon);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }


    public static void main1(String[] args) {
        String appdir = System.getProperty("user.dir");
        System.setProperty("OPC_LOG_HOME", appdir + "/log");
//        PropertyConfigurator.configure(ClassLoader.getSystemResource("resource/log4j.properties"));
//        logger = Logger.getLogger(ClientUI.class);

        try {
//            Main_Bulk_Service.service_inite();
        } catch (Exception e) {
//            logger.error(e);
        }
        ClientUI.show_ClientUI();
    }

    public static JRadioButton getManuButton() {
        return manuButton;
    }

    public static JRadioButton getHistoryButton() {
        return historyButton;
    }
}


