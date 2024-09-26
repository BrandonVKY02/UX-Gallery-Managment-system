import javafx.application.Platform;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Enumeration;

public class ArtworkAddWindow extends JFrame {

    private JLayeredPane layeredPane;


    Point pressedPoint;
    static JLabel previewLabel;
    private static String artworkPath;
    private String artworkName;
    private JButton closeButton = new JButton("Exit");//关闭按钮
    private JButton SaveButton = new JButton("Save");//编辑按钮
    private static int ID;
    private String artworkPrice;
    private String artworkStatus;
    private String artworkArtist;
    private String artworkDetail;

    private JLabel ArtworkNameLabel;
    private JLabel ArtworkPriceLabel;
    private JLabel ArtistLabel;
    private JLabel ArtworkStatusLabel;
    private JLabel backgroundLabel;
    private JLabel tips;

    private JTextField ArtworkNameField;
    private priceTextField ArtworkPriceField;
    private JTextField ArtistField;
    private JTextArea ArtworkDetailField;
    private static ArtworkAddWindow instance;
    public ArtworkAddWindow() {


        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(710,470);
        setBackground(Color.GRAY);
        setLayout(null);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");//格式化设置
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);//四舍五入


        layeredPane = getLayeredPane();

        closeButton.setBounds(640, 10, 60, 30);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                adminWindow.refreshCardPanel();
                config.AddWindowEnabled = false;
            }
        });
        closeButton.setUI((ButtonUI) UIManager.getUI(closeButton));

        SaveButton.setBounds(710-(100+10), 470-40, 100, 30);
        SaveButton.setUI((ButtonUI) UIManager.getUI(SaveButton));


        ButtonGroup StatusGroup = new ButtonGroup();//创建单选按钮组
        JRadioButton Status_onSale = new JRadioButton("onSale");//创建单选按钮
        JRadioButton Status_sellout = new JRadioButton("Sellout");//创建单选按钮

        StatusGroup.add(Status_onSale);
        StatusGroup.add(Status_sellout);
        Status_onSale.setSelected(true);

        // 创建背景图片
        backgroundLabel = new JLabel(new ImageIcon(config.ArtworkInfoWindowBackgroundPath));
        backgroundLabel.setSize(710, 480);


        //读取图片
        Filereader fileReader = new Filereader("artwork");
        artworkPath = config.artworkPreviewPath + "Add.png";
        //创建图片preview窗口
        previewLabel = new JLabel();
        SwingUtilities.invokeLater(() -> {
            ImageIcon icon = new ImageIcon(artworkPath);
            Image image = icon.getImage().getScaledInstance(previewLabel.getWidth(), previewLabel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);
            previewLabel.setIcon(scaledIcon);
        });
        previewLabel.setBounds(68,101,160, 295);
        ID = fileReader.returnCount();
        String artworkID = String.valueOf(ID);



        //创建艺术品名称标签
        ArtworkNameLabel = new JLabel("Artwork Name");
        ArtworkNameLabel.setBounds(250, 83, 150, 65);
        ArtworkNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ArtworkNameLabel.setForeground(Color.white);


        ArtworkNameField = new JTextField();
        ArtworkNameField.setText("");
        ArtworkNameField.setBounds(400, 83, 240, 65);
        ArtworkNameField.setFont(new Font("Arial", Font.BOLD, 20));
        ArtworkNameField.setForeground(Color.white);
        ArtworkNameField.setOpaque(false);
        ArtworkNameField.setBorder(null);

        //创建艺术品价格标签
        ArtworkPriceLabel = new JLabel("Price (RM)");
        ArtworkPriceLabel.setBounds(250, 123, 150, 65);
        ArtworkPriceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ArtworkPriceLabel.setForeground(Color.white);


        ArtworkPriceField = new priceTextField();
        ArtworkPriceField.setText("");
        ArtworkPriceField.setColumns(10);
        ArtworkPriceField.setBounds(400, 123, 240, 65);
        ArtworkPriceField.setFont(new Font("Arial", Font.BOLD, 20));
        ArtworkPriceField.setForeground(Color.white);
        ArtworkPriceField.setOpaque(false);
        ArtworkPriceField.setBorder(null);

        //创建艺术家标签
        ArtistLabel = new JLabel("Artist");
        ArtistLabel.setBounds(250, 163, 150, 65);
        ArtistLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ArtistLabel.setForeground(Color.white);

        ArtistField = new JTextField();
        ArtistField.setText("");
        ArtistField.setBounds(400, 163, 240, 65);
        ArtistField.setFont(new Font("Arial", Font.BOLD, 20));
        ArtistField.setForeground(Color.white);
        ArtistField.setOpaque(false);
        ArtistField.setBorder(null);

        //创建艺术品状态标签
        ArtworkStatusLabel = new JLabel("Status");
        ArtworkStatusLabel.setBounds(250, 203, 150, 65);
        ArtworkStatusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ArtworkStatusLabel.setForeground(Color.white);

        Status_onSale.setBounds(400, 203, 100, 65);
        Status_onSale.setFont(new Font("Arial", Font.BOLD, 20));
        Status_onSale.setForeground(Color.white);
        Status_onSale.setOpaque(false);
        Status_onSale.setBorder(null);

        Status_sellout.setBounds(500, 203, 100, 65);
        Status_sellout.setFont(new Font("Arial", Font.BOLD, 20));
        Status_sellout.setForeground(Color.white);
        Status_sellout.setOpaque(false);
        Status_sellout.setBorder(null);

        //创建艺术品详情标签
        ArtworkDetailField = new JTextArea();
        ArtworkDetailField.setText("");
        ArtworkDetailField.setBounds(250, 263, 400, 135);
        ArtworkDetailField.setFont(new Font("Arial", Font.BOLD, 20));
        ArtworkDetailField.setForeground(Color.white);
        ArtworkDetailField.setOpaque(false);
        ArtworkDetailField.setBorder(null);
        ArtworkDetailField.setCaretPosition(0);
        ArtworkDetailField.setLineWrap(true);
        ArtworkDetailField.setWrapStyleWord(true);
        ArtworkDetailField.setCaretPosition(0);

        tips = new JLabel("tips:Double click to Add artwork preview");
        tips.setBounds(68, 0, 400, 30);
        tips.setFont(new Font("Arial", Font.BOLD, 20));
        tips.setForeground(Color.white);


        previewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    Platform.runLater(() -> {
                        new ImageCropper(artworkID);
                    });
                }
            }
        });




        ArtworkDetailField.addKeyListener(new KeyAdapter() {//禁用回车键
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume(); // 消耗回车键事件
                }
            }
        });

        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String artworkName = ArtworkNameField.getText();


                String strPrice = ArtworkPriceField.getText();//获取价格
                double dblPrice = Double.parseDouble(strPrice);//转换为double
                String artworkPrice = String.format("%.2f",dblPrice);//保留两位小数


                String artworkArtist = ArtistField.getText();//获取艺术家


                JRadioButton selectedRadioButton = null;//获取艺术品状态
                Enumeration<AbstractButton> buttons = StatusGroup.getElements();
                while (buttons.hasMoreElements()) {
                    JRadioButton radioButton = (JRadioButton) buttons.nextElement();
                    if (radioButton.isSelected()) {
                        selectedRadioButton = radioButton;
                        break;
                    }
                }
                String artworkStatus = selectedRadioButton.getText();
                String artworkDetail = ArtworkDetailField.getText();//获取艺术品详情



                ArtworkManager manager = new ArtworkManager();
                manager.addArtwork(ID, artworkName, artworkPrice, artworkArtist, artworkStatus, artworkDetail);

                try {
                    ImageCropper.saveImage();//保存图片
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                JOptionPane.showMessageDialog(ArtworkDetailField, "Add Success!");
                adminWindow.refreshCardPanel();
                config.AddWindowEnabled = false;
                dispose();
            }
        });


        //设置窗口置顶，无边框
        setUndecorated(true);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);


        //添加组件
        layeredPane.add(ArtworkDetailField, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(ArtworkNameLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(ArtworkPriceLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(ArtistLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(ArtworkStatusLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(ArtworkNameField,JLayeredPane.DEFAULT_LAYER );
        layeredPane.add(ArtworkPriceField, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(ArtistField, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(Status_onSale, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(Status_sellout, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(previewLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(backgroundLabel, -1);
        layeredPane.add(closeButton, 1);
        layeredPane.add(SaveButton, 1);
        layeredPane.add(tips, 1);


        //窗口位移
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




        setLocationRelativeTo(null);
        setVisible(true);
        instance = this;
    }

    public static void dialog(){
        JOptionPane.showMessageDialog(instance, "Please close the Add window first!");
    }

}
