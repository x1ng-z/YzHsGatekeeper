package UI;

import YzHs.Client;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;


public class Bulk_ClientUI {
    private static Logger logger = Logger.getLogger(Bulk_ClientUI.class);


    private static TrayIcon trayIcon = null;
    static JFrame mf = new JFrame();
    static SystemTray tray = SystemTray.getSystemTray();
    private static JComboBox cmb = new JComboBox();
    private static JFormattedTextField max_load;
    private static JRadioButton manuButton;
    private static JRadioButton historyButton;
    private static JFormattedTextField max_load_count;
    private static  JFormattedTextField invalid_load_time;
    private static JFormattedTextField invalid_load_weight;


    public static void show_ClientUI() {
        logger = Logger.getLogger(Bulk_ClientUI.class);

//        mf.setLocation(300, 100);

        mf.setSize(500, 300);
        mf.setTitle("永州进出厂车辆管控系统");
        JPanel pane = new JPanel();
        mf.add(pane);
        pane.setBackground(new Color(6, 119, 203));
        pane.setBounds(0, 0, 500, 320);
        pane.setLayout(null);
        mf.setResizable(false);

        mf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        try {

            Image logoimage = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + "\\conf\\img\\garage.png");
            mf.setIconImage(logoimage);
        } catch (Exception e) {
            logger.error(e);
//            LogMgr.getLogMgr_instance().WirteError(e);
        }




        JLabel label1 = new JLabel("");
        label1.setFont(new Font("",Font.CENTER_BASELINE,40));//创建标签
        label1.setBounds(0, 0, 500, 300);
        label1.setBackground(new Color(203, 198, 45));
        label1.setVisible(true);
        pane.add(label1);
//        cmb = new JComboBox();    //创建JComboBox
//        cmb.addItem("--请选择--");    //向下拉列表中添加一项
//
//        cmb.setBounds(120,0,100,30);
//        cmb.setBackground(new Color(6, 119, 203));
//
//
//        JButton jButton = new JButton("确定");
//        jButton.setBounds(220,0,100,30);
//        jButton.setBackground(new Color(6, 119, 203));
//
//
//        jButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                String s = (String) cmb.getSelectedItem();
//                if(s==null){
//                    return;
//                }
//
//            }
//        });
//
//        pane.add(label1);
//        pane.add(cmb);
//        pane.add(jButton);
//
//
//
//
//        JPanel panel_history = new JPanel();
//        panel_history.setBounds(25,50,200,150);
//        Border history_bord = BorderFactory.createTitledBorder("最大装车重量限制设置");
//        ((TitledBorder) history_bord).setTitleColor(Color.BLACK);
////        ((TitledBorder) borderpanelmzdj2).setTitleFont(panelfont);
//        panel_history.setBorder(history_bord);
//        panel_history.setBackground(new Color(255, 184, 18));
////        panelmzdj2.setVisible(false);
//        pane.add(panel_history);
//
//
//
//
//        String strict = "按历史最大装车重量";
//        String limit = "手动,按";
//
//        historyButton = new JRadioButton(strict);
//        historyButton.setBounds(10,20,150,20);
//        historyButton.setActionCommand(strict);
//        historyButton.setBackground(new Color(255, 184, 18));
//
//        historyButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                max_load.setEditable(false);
//            }
//        });
//
//
//        manuButton = new JRadioButton(limit);
//        manuButton.setActionCommand(limit);
//        manuButton.setBounds(10,60,70,20);
//        manuButton.setBackground(new Color(255, 184, 18));
//
//        manuButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                max_load.setEditable(true);
//            }
//        });
//
//
//
//
//
//
//        ButtonGroup group = new ButtonGroup();
//        group.add(historyButton);
//        group.add(manuButton);
//
//
//
//
//
//
//        max_load = new JFormattedTextField(new java.text.DecimalFormat("#.#"));
//        max_load.setBounds(80,60,40,20);
//        max_load.setBackground(new Color(255, 184, 18));
//
//        max_load.setEditable(false);
//
//        if(manuButton.isSelected()){
//            max_load.setEditable(true);
//        }
//
//        JLabel fix_dun=new JLabel();
//        fix_dun.setBounds(120,60,20,20);
//        fix_dun.setBackground(new Color(255, 184, 18));
//        fix_dun.setText("吨");
//
//        JButton Yes = new JButton("√");
//        Yes.setBounds(140,60,50,20);
//        Yes.setBackground(new Color(255, 184, 18));
//
//        Yes.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(manuButton.isSelected()){
//
//                }
//            }
//        });
//
//
//
//
//
//
//
//
//        panel_history.add(historyButton);
//        panel_history.add(manuButton);
//        panel_history.add(max_load);
//        panel_history.add(fix_dun);
//        panel_history.add(Yes);
//
//        panel_history.setLayout(null);
//
//
//
//
//
//
//        JPanel panel_count = new JPanel();
//        panel_count.setBounds(270,50,200,150);
//        Border count_bord = BorderFactory.createTitledBorder("装车次数限制及无效装车设置");
//        ((TitledBorder) count_bord).setTitleColor(Color.BLACK);
////        ((TitledBorder) borderpanelmzdj2).setTitleFont(panelfont);
//        panel_count.setBorder(count_bord);
//        panel_count.setBackground(new Color(255, 184, 18));
////        panelmzdj2.setVisible(false);
//        pane.add(panel_count);
//
//
//        JLabel fix = new JLabel();
//        fix.setBounds(20,20,100,20);
//        fix.setBackground(new Color(255, 184, 18));
//        fix.setText("1.最大装车次数:");
//        panel_count.add(fix);
//
//
//
//
//        max_load_count = new JFormattedTextField(new java.text.DecimalFormat("#0"));
//        max_load_count.setBounds(110,20,30,20);
//        max_load_count.setBackground(new Color(255, 184, 18));
//        max_load_count.setEditable(true);
//        panel_count.add(max_load_count);
//
//
//        JLabel fix_ci=new JLabel();
//        fix_ci.setBounds(140,20,20,20);
//        fix_ci.setBackground(new Color(255, 184, 18));
//        fix_ci.setText("次");
//        panel_count.add(fix_ci);
//
//
//
//
//
//
//        JLabel fix_regular = new JLabel();
//        fix_regular.setBounds(20,40,130,20);
//        fix_regular.setBackground(new Color(255, 184, 18));
//        fix_regular.setText("2.无效装车判断规则：");
//        panel_count.add(fix_regular);
//
//
//        JLabel regular_time = new JLabel();
//        regular_time.setBounds(20,60,90,20);
//        regular_time.setBackground(new Color(255, 184, 18));
//        regular_time.setText("装车时间小于");
//        panel_count.add(regular_time);
//
//
//        invalid_load_time = new JFormattedTextField(new java.text.DecimalFormat("#0"));
//        invalid_load_time.setBounds(110,60,30,20);
//        invalid_load_time.setBackground(new Color(255, 184, 18));
//        invalid_load_time.setEditable(true);
//        panel_count.add(invalid_load_time);
//
//        JLabel regular_min = new JLabel();
//        regular_min.setBounds(140,60,70,20);
//        regular_min.setBackground(new Color(255, 184, 18));
//        regular_min.setText("分钟,并且");
//        panel_count.add(regular_min);
//
//
//
//
//
//        JLabel regular_dun = new JLabel();
//        regular_dun.setBounds(20,80,90,20);
//        regular_dun.setBackground(new Color(255, 184, 18));
//        regular_dun.setText("装车重量小于");
//        panel_count.add(regular_dun);
//
//
//        invalid_load_weight = new JFormattedTextField(new java.text.DecimalFormat("#.#"));
//        invalid_load_weight.setBounds(110,80,30,20);
//        invalid_load_weight.setBackground(new Color(255, 184, 18));
//        invalid_load_weight.setEditable(true);
//        panel_count.add(invalid_load_weight);
//
//        JLabel fix_re_dun = new JLabel();
//        fix_re_dun.setBounds(140,80,70,20);
//        fix_re_dun.setBackground(new Color(255, 184, 18));
//        fix_re_dun.setText("吨");
//        panel_count.add(fix_re_dun);
//
//
//
//        JButton yes_up = new JButton("提交√");
//        yes_up.setBounds(70,100,80,20);
//        yes_up.setBackground(new Color(255, 184, 18));
//
//        yes_up.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//
//
//            }
//        });
//        panel_count.add(yes_up);
//
//
//
//
//
//        panel_count.setLayout(null);



//







        mf.setVisible(true);

        mf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "确定要退出系统吗？", "退出系统", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    logger.info("End OneCar");
                    try {

                        Client.getIOC().close();
                    } catch (Exception e1) {
                        logger.error(e1);
                    }
                    System.exit(0);
                }
            }

            ;

            public void windowIconified(WindowEvent e) { // 窗口最小化事件

                mf.setVisible(false);
                miniTray();

            }

        });

    }

    private static void miniTray() { // 窗口最小化到任务栏托盘

        String rootpath = System.getProperty("user.dir");

        String filepath = rootpath + "\\conf\\img\\garage.png";
        ImageIcon trayImg = new ImageIcon(filepath);// 托盘图标

        trayIcon = new TrayIcon(trayImg.getImage(), "GateKeeper", new PopupMenu());
        trayIcon.setImageAutoSize(true);

        trayIcon.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {// 单击 1 双击 2

                    tray.remove(trayIcon);
                    mf.setVisible(true);
                    mf.setExtendedState(JFrame.NORMAL);
                    mf.toFront();
                }

            }

        });

        try {

            tray.add(trayIcon);

        } catch (AWTException e1) {
//            e1.printStackTrace();
            logger.error(e1);
        }
    }


    public static void main(String[] args) {
        String appdir = System.getProperty("user.dir");
        System.setProperty("OPC_LOG_HOME", appdir + "/log");
        PropertyConfigurator.configure(ClassLoader.getSystemResource("resource/log4j.properties"));
        logger = Logger.getLogger(Bulk_ClientUI.class);

        try {
//            Main_Bulk_Service.service_inite();
        } catch (Exception e) {
            logger.error(e);
        }



        Bulk_ClientUI.show_ClientUI();







    }

    public static JRadioButton getManuButton() {
        return manuButton;
    }

    public static JRadioButton getHistoryButton() {
        return historyButton;
    }
}


