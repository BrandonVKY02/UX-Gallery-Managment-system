import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class ArtistAddWindow extends JFrame {
    private JLayeredPane layeredPane;
    private JButton closeButton = new JButton("Exit");//关闭按钮
    private JButton saveButton = new JButton("Save");//编辑按钮

    private JLabel backgroundLabel;

    private JTextField artistNameField;
    private JTextField artistSpecialtyField;
    private priceTextField artistRangeField1;
    private priceTextField artistRangeField2;
    private JTextField artistCountryField;



    int ID;
    private Point pressedPoint = null; // 鼠标按下时的坐标

    public ArtistAddWindow() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        layeredPane = getLayeredPane();

        setSize(710, 470);



        closeButton.setBounds(640, 10, 60, 30);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                adminWindow.refreshCardPanel();
                config.AddWindowEnabled = false;
            }
        });

        saveButton.setBounds(710 - (100 + 10), 470 - 40, 100, 30);
        saveButton.setUI((ButtonUI) UIManager.getUI(saveButton));


        ButtonGroup AliveGroup = new ButtonGroup();
        JRadioButton aliveButton_YES = new JRadioButton("Yes");
        JRadioButton aliveButton_NO = new JRadioButton("No");
        AliveGroup.add(aliveButton_YES);
        AliveGroup.add(aliveButton_NO);
        aliveButton_YES.setSelected(true);


        backgroundLabel = new JLabel(new ImageIcon(config.ArtistInfoWindowBackgroundPath));
        backgroundLabel.setSize(710, 480);

        Filereader fileReader = new Filereader("artist");
        ID = fileReader.returnCount();



        artistNameField = new JTextField();
        artistNameField.setText("");
        artistNameField.setFont(new Font("Arial", Font.PLAIN, 30));
        artistNameField.setBounds(250, 100, 400, 58);
        artistNameField.setForeground(Color.WHITE);
        artistNameField.setOpaque(false);
        artistNameField.setBorder(null);

        artistSpecialtyField = new JTextField();
        artistSpecialtyField.setText("");
        artistSpecialtyField.setFont(new Font("Arial", Font.PLAIN, 30));
        artistSpecialtyField.setBounds(250, 160, 400, 58);
        artistSpecialtyField.setForeground(Color.WHITE);
        artistSpecialtyField.setOpaque(false);
        artistSpecialtyField.setBorder(null);

        aliveButton_YES.setBounds(250, 220, 100, 58);
        aliveButton_YES.setFont(new Font("Arial", Font.PLAIN, 30));
        aliveButton_YES.setForeground(Color.WHITE);
        aliveButton_YES.setOpaque(false);
        aliveButton_YES.setBorder(null);

        aliveButton_NO.setBounds(350, 220, 100, 58);
        aliveButton_NO.setFont(new Font("Arial", Font.PLAIN, 30));
        aliveButton_NO.setForeground(Color.WHITE);
        aliveButton_NO.setOpaque(false);
        aliveButton_NO.setBorder(null);



        artistRangeField1 = new priceTextField();
        artistRangeField1.setText("");
        artistRangeField1.setFont(new Font("Arial", Font.PLAIN, 30));
        artistRangeField1.setBounds(250, 280, 150, 58);
        artistRangeField1.setForeground(Color.WHITE);
        artistRangeField1.setOpaque(false);
        artistRangeField1.setBorder(null);

        artistRangeField2 = new priceTextField();
        artistRangeField2.setText("");
        artistRangeField2.setFont(new Font("Arial", Font.PLAIN, 30));
        artistRangeField2.setBounds(500, 280, 150, 58);
        artistRangeField2.setForeground(Color.WHITE);
        artistRangeField2.setOpaque(false);
        artistRangeField2.setBorder(null);

        artistCountryField = new JTextField();
        artistCountryField.setText("");
        artistCountryField.setFont(new Font("Arial", Font.PLAIN, 30));
        artistCountryField.setBounds(250, 340, 400, 58);
        artistCountryField.setForeground(Color.WHITE);
        artistCountryField.setOpaque(false);
        artistCountryField.setBorder(null);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String artistName = artistNameField.getText();
                String artistSpecialty = artistSpecialtyField.getText();
                JRadioButton selectedRadioButton = null;
                Enumeration<AbstractButton> buttons = AliveGroup.getElements();
                while (buttons.hasMoreElements()) {
                    JRadioButton radioButton = (JRadioButton) buttons.nextElement();
                    if (radioButton.isSelected()) {
                        selectedRadioButton = radioButton;
                        break;
                    }
                }
                String artistAlive = selectedRadioButton.getText();
                String artistRange = artistRangeField1.getText() + "-" + artistRangeField2.getText();
                String artistCountry = artistCountryField.getText();

                ArtistManager manager = new ArtistManager();
                manager.addArtist(ID, artistName, artistSpecialty, artistAlive, artistRange, artistCountry);
                JOptionPane.showMessageDialog(null, "Add Success!");
                dispose();
                adminWindow.refreshCardPanel();
                config.AddWindowEnabled = false;
            }
        });


        layeredPane.add(closeButton, layeredPane.DEFAULT_LAYER);
        layeredPane.add(saveButton, layeredPane.DEFAULT_LAYER);
        layeredPane.add(artistNameField, layeredPane.DEFAULT_LAYER);
        layeredPane.add(artistSpecialtyField, layeredPane.DEFAULT_LAYER);
        layeredPane.add(aliveButton_YES, layeredPane.DEFAULT_LAYER);
        layeredPane.add(aliveButton_NO, layeredPane.DEFAULT_LAYER);
        layeredPane.add(artistRangeField1, layeredPane.DEFAULT_LAYER);
        layeredPane.add(artistCountryField, layeredPane.DEFAULT_LAYER);
        layeredPane.add(artistRangeField2, layeredPane.DEFAULT_LAYER);
        layeredPane.add(backgroundLabel, -1);


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
        setUndecorated(true);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }
}