import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class LoginWindow extends JFrame {
    private JLabel User;//用户名标签
    private JLabel pw;//密码标签
    private JPanel topPanel = new JPanel(new FlowLayout());//顶部面板
    private JLabel titleLabel = new JLabel("Login Window");//标题标签
    private JButton closeButton = new JButton("X");//关闭按钮
    private  JTextField usernameTextfield;//用户名文本框
    private  JPasswordField passwordTextfield;//密码文本框
    private JButton login = new JButton("Login");//登录按钮
    Point pressedPoint;
    public LoginWindow(){

        topPanel.setBackground(Color.darkGray);
        topPanel.setBounds(0, 0, 354, 20);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(titleLabel,BorderLayout.CENTER);
        topPanel.add(closeButton,BorderLayout.EAST);

        titleLabel.setForeground(Color.white);
        titleLabel.setLocation(10, 10);
        closeButton.setLocation(200, 10);

        JLabel testUser = new JLabel("Test User: Admin, password: admin");
        testUser.setFont(new Font("Arial",Font.PLAIN,12));
        testUser.setBounds(30, 120, 200, 20);


        setLayout (null);
        User = new JLabel("Username :");
        User.setFont(new Font("Arial",Font.PLAIN,12));
        User.setBounds(30, 40, 80, 20);
        pw = new JLabel("Password :");
        pw.setFont(new Font("Arial",Font.PLAIN,12));
        pw.setBounds(30, 80, 80, 20);

        usernameTextfield = new JTextField(20);
        usernameTextfield.setBounds(110,40,150,20);
        passwordTextfield = new JPasswordField(20);
        passwordTextfield.setBounds(110,80,150,20);
        getContentPane().setBackground(new Color(195, 184, 162));
        setUndecorated(true);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);


        closeButton.addActionListener(new ActionListener() {// 设置按钮关闭动作事件处理
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        passwordTextfield.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    String username = usernameTextfield.getText();
                    String password = new String(passwordTextfield.getPassword());
                    String uid = null;
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(config.userFilePath));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            if (line.startsWith("Username:")) {
                                String storedUsername = line.substring(9).trim();
                                if (storedUsername.equals(username)) {
                                    line = reader.readLine();
                                    if (line.startsWith("Password:")) {
                                        String storedPassword = line.substring(9).trim();
                                        if (storedPassword.equals(password)) {
                                            line = reader.readLine();
                                            if (line.startsWith("Uid:")) {
                                                uid = line.substring(4).trim();
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        reader.close();
                    }catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if (uid != null ) {
                        System.out.println("Login successful.");
                            new adminWindow();
                        dispose();  // Close login window
                    } else {
                        System.out.println("Incorrect username or password.");
                        JOptionPane.showMessageDialog(LoginWindow.this, "Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextfield.getText();
                String password = new String(passwordTextfield.getPassword());
                String uid = null;
                String usertype = null;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(config.userFilePath));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("Username:")) {
                            String storedUsername = line.substring(9).trim();
                            if (storedUsername.equals(username)) {
                                line = reader.readLine();
                                if (line.startsWith("Password:")) {
                                    String storedPassword = line.substring(9).trim();
                                    if (storedPassword.equals(password)) {
                                        line = reader.readLine();
                                        if (line.startsWith("Uid:")) {
                                            uid = line.substring(4).trim();
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    reader.close();
                }catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (uid != null && usertype != null) {
                    System.out.println("Login successful.");
                        new adminWindow();

                    dispose();  // Close login window
                } else {
                    System.out.println("Incorrect username or password.");
                    JOptionPane.showMessageDialog(LoginWindow.this, "Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        login.setBounds(140, 165,70,30);
        getContentPane().add(topPanel);
        getContentPane().add(usernameTextfield);
        getContentPane().add(passwordTextfield);
        getContentPane().add(User); //添加User组件到容器中
        getContentPane().add(pw);//添加pw组件到容器中
        getContentPane().add(login);
        getContentPane().add(testUser);//将测试用户显示到窗口中
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { //鼠标按下事件
                pressedPoint = e.getPoint(); //记录鼠标坐标
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) { // 鼠标拖拽事件
                Point point = e.getPoint();// 获取当前坐标
                Point locationPoint = getLocation();// 获取窗体坐标
                int x = locationPoint.x + point.x - pressedPoint.x;// 计算移动后的新坐标
                int y = locationPoint.y + point.y - pressedPoint.y;
                setLocation(x, y);// 改变窗体位置
            }
        });
        setBounds(0,0,354,206);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

