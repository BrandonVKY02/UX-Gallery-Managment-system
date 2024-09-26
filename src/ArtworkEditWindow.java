import javafx.application.Platform;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

public class ArtworkEditWindow extends JFrame {

    private JLayeredPane layeredPane;


    Point pressedPoint;
    static JLabel previewLabel;
    private static String artworkPath;
    private String artworkName;
    private JButton closeButton = new JButton("Exit");//关闭按钮
    private JButton editButton = new JButton("Save Edit");//编辑按钮
    private JButton deleteButton = new JButton("Delete");//删除按钮
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
    private static ArtworkEditWindow instance;
    static boolean ImageChanged = false;
    public ArtworkEditWindow(String artworkID) {


        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ID = Integer.parseInt(artworkID);

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
                config.EditWindowEnabled = false;
            }
        });
        closeButton.setUI((ButtonUI) UIManager.getUI(closeButton));

        editButton.setBounds(710-(100+10), 470-40, 100, 30);
        editButton.setUI((ButtonUI) UIManager.getUI(editButton));

        deleteButton.setBounds(710 - ((100+10)+10+100), 470-40, 100, 30);
        deleteButton.setUI((ButtonUI) UIManager.getUI(deleteButton));


        ButtonGroup StatusGroup = new ButtonGroup();//创建单选按钮组
        JRadioButton Status_onSale = new JRadioButton("onSale");//创建单选按钮
        JRadioButton Status_sellout = new JRadioButton("Sellout");//创建单选按钮

        StatusGroup.add(Status_onSale);
        StatusGroup.add(Status_sellout);





        // 创建背景图片
        backgroundLabel = new JLabel(new ImageIcon(config.ArtworkInfoWindowBackgroundPath));
        backgroundLabel.setSize(710, 480);


        //读取图片
        Filereader fileReader = new Filereader("artwork");
        artworkPath = config.artworkPreviewPath + ID + ".png";
        //创建图片preview窗口
        previewLabel = new JLabel();
        SwingUtilities.invokeLater(() -> {
            ImageIcon icon = new ImageIcon(artworkPath);
            Image image = icon.getImage().getScaledInstance(previewLabel.getWidth(), previewLabel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);
            previewLabel.setIcon(scaledIcon);
        });
        previewLabel.setBounds(68,101,160, 295);
        //读取艺术品名称
        artworkName = fileReader.returnArtworkName(ID);
        //读取艺术品价格
        artworkPrice = fileReader.returnArtworkPrice(ID);
        //读取艺术家
        artworkArtist = fileReader.returnArtist(ID);
        //读取艺术品状态
        artworkStatus = fileReader.returnStatus(ID);
        //读取艺术品详情
        artworkDetail = fileReader.returnDetail(ID);


        if(artworkStatus.equals("onSale")){
            Status_onSale.setSelected(true);}
        else if(artworkStatus.equals("Sellout")){
            Status_sellout.setSelected(true);
        }




        //创建艺术品名称标签
        ArtworkNameLabel = new JLabel("Artwork Name");
        ArtworkNameLabel.setBounds(250, 83, 150, 65);
        ArtworkNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ArtworkNameLabel.setForeground(Color.white);


        ArtworkNameField = new JTextField();
        ArtworkNameField.setText(artworkName);
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
        ArtworkPriceField.setText(artworkPrice);
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
        ArtistField.setText(artworkArtist);
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
        ArtworkDetailField.setText(artworkDetail);
        ArtworkDetailField.setBounds(250, 263, 400, 135);
        ArtworkDetailField.setFont(new Font("Arial", Font.BOLD, 20));
        ArtworkDetailField.setForeground(Color.white);
        ArtworkDetailField.setOpaque(false);
        ArtworkDetailField.setBorder(null);
        ArtworkDetailField.setCaretPosition(0);
        ArtworkDetailField.setLineWrap(true);
        ArtworkDetailField.setWrapStyleWord(true);
        ArtworkDetailField.setCaretPosition(0);

        tips = new JLabel("tips:Double click to edit artwork preview");
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

        editButton.addActionListener(new ActionListener() {
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
                String artworkStatus = selectedRadioButton.getText();//获取艺术品状态
                String artworkDetail = ArtworkDetailField.getText();//获取艺术品详情



                ArtworkManager manager = new ArtworkManager();
                manager.updateArtwork(ID, artworkName, artworkPrice, artworkArtist, artworkStatus, artworkDetail);//更新artwork信息

                if(ImageChanged) {
                    try {
                        ImageCropper.saveImage();//保存图片
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }


                JOptionPane.showMessageDialog(ArtworkDetailField, "Edit Success!");
                adminWindow.refreshCardPanel();
                config.EditWindowEnabled = false;
                dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(ArtworkDetailField, "Are you sure to delete this artwork?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    delete(Integer.parseInt(artworkID));
                    JOptionPane.showMessageDialog(ArtworkDetailField, "Delete Success!");
                    dispose();
                    adminWindow.refreshCardPanel();
                    config.EditWindowEnabled = false;
                }
            }
        });



        //设置窗口属性
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
        layeredPane.add(editButton, 1);
        layeredPane.add(tips, 1);
        layeredPane.add(deleteButton, 1);


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


    public void delete(int ID){
        ArrayList<String> data = new ArrayList<String>(); // 存储文件内容的数组列表

        try {
            File file = new File(config.artworkFilePath);
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);

            // 将文件内容读入数组列表中
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data.add(line);
            }

            // 寻找要删除的艺术品ID并进行删除
            for (int i = 0; i < data.size(); i++) {
                String line = data.get(i);
                if (line.startsWith("artworkID:") && Integer.parseInt(line.split(":")[1]) == ID) {
                    // 删除要删除的行和它后面的数据行
                    data.subList(i-1, i+5).clear();
                    break;
                }
            }

            // 将修改后的内容写回文件中
            FileWriter writer = new FileWriter(file);
            for (String line : data) {
                writer.write(line + "\n");
            }
            writer.close();

            File deleteFile = new File("./img/preview/"+ID+".png");
            deleteFile.delete();



        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


