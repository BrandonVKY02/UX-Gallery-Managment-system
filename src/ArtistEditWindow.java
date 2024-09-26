import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

public class ArtistEditWindow extends JFrame {
    private JLayeredPane layeredPane;
    private JButton closeButton = new JButton("Exit");//关闭按钮
    private JButton editButton = new JButton("Save Edit");//编辑按钮
    private JButton deleteButton = new JButton("Delete");//删除按钮

    private JLabel backgroundLabel;

    private JTextField artistNameField;
    private JTextField artistSpecialtyField;
    private priceTextField artistRangeField1;
    private priceTextField artistRangeField2;
    private JTextField artistCountryField;

    private String artistName;
    private String artistSpecialty;
    private String artistAlive;
    private String artistRange;
    private String artistCountry;

    int ID;
    private Point pressedPoint = null; // 鼠标按下时的坐标
    public ArtistEditWindow(String artistID) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        layeredPane = getLayeredPane();

        setSize(710,470);

        ID = Integer.parseInt(artistID);

        closeButton.setBounds(640, 10, 60, 30);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                adminWindow.refreshCardPanel();
                config.EditWindowEnabled = false;
            }
        });

        ButtonGroup AliveGroup = new ButtonGroup();
        JRadioButton aliveButton_YES = new JRadioButton("Yes");
        JRadioButton aliveButton_NO = new JRadioButton("No");
        AliveGroup.add(aliveButton_YES);
        AliveGroup.add(aliveButton_NO);


        editButton.setBounds(710-(100+10), 470-40, 100, 30);
        editButton.setUI((ButtonUI) UIManager.getUI(editButton));

        deleteButton.setBounds(710 - ((100+10)+10+100), 470-40, 100, 30);
        deleteButton.setUI((ButtonUI) UIManager.getUI(deleteButton));

        backgroundLabel = new JLabel(new ImageIcon(config.ArtistInfoWindowBackgroundPath));
        backgroundLabel.setSize(710, 480);

        Filereader fileReader = new Filereader("artist");
        artistName = fileReader.returnArtistName(ID);
        artistSpecialty = fileReader.returnArtistSpecialty(ID);
        artistAlive = fileReader.returnArtistAlive(ID);
        artistRange = fileReader.returnArtistRange(ID);
        artistCountry = fileReader.returnArtistCountry(ID);

        if(artistAlive.equals("Yes")){
            aliveButton_YES.setSelected(true);
        }else{
            aliveButton_NO.setSelected(true);
        }

        artistNameField = new JTextField();
        artistNameField.setText(artistName);
        artistNameField.setFont(new Font("Arial", Font.PLAIN, 30));
        artistNameField.setBounds(250, 100, 400, 58);
        artistNameField.setForeground(Color.WHITE);
        artistNameField.setOpaque(false);
        artistNameField.setBorder(null);

        artistSpecialtyField = new JTextField();
        artistSpecialtyField.setText(artistSpecialty);
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


        String[] artistRangeParts = artistRange.split("-");
        artistRangeField1 = new priceTextField();
        artistRangeField1.setText(artistRangeParts[0]);
        artistRangeField1.setFont(new Font("Arial", Font.PLAIN, 30));
        artistRangeField1.setBounds(250, 280, 150, 58);
        artistRangeField1.setForeground(Color.WHITE);
        artistRangeField1.setOpaque(false);
        artistRangeField1.setBorder(null);

        artistRangeField2 = new priceTextField();
        artistRangeField2.setText(artistRangeParts[1]);
        artistRangeField2.setFont(new Font("Arial", Font.PLAIN, 30));
        artistRangeField2.setBounds(500, 280, 150, 58);
        artistRangeField2.setForeground(Color.WHITE);
        artistRangeField2.setOpaque(false);
        artistRangeField2.setBorder(null);

        artistCountryField = new JTextField();
        artistCountryField.setText(artistCountry);
        artistCountryField.setFont(new Font("Arial", Font.PLAIN, 30));
        artistCountryField.setBounds(250, 340, 400, 58);
        artistCountryField.setForeground(Color.WHITE);
        artistCountryField.setOpaque(false);
        artistCountryField.setBorder(null);


        editButton.addActionListener(new ActionListener() {
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
                manager.editArtist(ID, artistName, artistSpecialty, artistAlive, artistRange, artistCountry);
                JOptionPane.showMessageDialog(null, "Edit Success!");
                dispose();
                adminWindow.refreshCardPanel();
                config.EditWindowEnabled = false;
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int n = JOptionPane.showConfirmDialog(null, "Are you sure to delete this artwork?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    delete(ID);
                    JOptionPane.showMessageDialog(null, "Delete Success!");
                    dispose();
                    adminWindow.refreshCardPanel();
                    config.EditWindowEnabled = false;
                }
            }
        });












        layeredPane.add(closeButton, layeredPane.DEFAULT_LAYER);
        layeredPane.add(editButton, layeredPane.DEFAULT_LAYER);
        layeredPane.add(deleteButton, layeredPane.DEFAULT_LAYER);
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


    public void delete(int ID){
        ArrayList<String> data = new ArrayList<String>(); // 存储文件内容的数组列表

        try {
            File file = new File(config.artistDataPath);
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
                if (line.startsWith("ID:") && Integer.parseInt(line.split(":")[1]) == ID) {
                    // 删除要删除的行和它后面的数据行
                    data.subList(i, i+6).clear();
                    break;
                }
            }

            // 将修改后的内容写回文件中
            FileWriter writer = new FileWriter(file);
            for (String line : data) {
                writer.write(line + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

