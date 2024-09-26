import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArtworkContentPanel extends JPanel {

    private final String[] artworkID;
    private final String[] artworkName;
    private final String[] artworkArtist;
    private final String[] artworkPrice;
    private final String[] artworkStatus;

    public static JPanel Instance;
    public static String[] artworkIDStatic;
    public static String[] artworkNameStatic;
    public static String[] artworkArtistStatic;
    public static String[] artworkPriceStatic;
    public static String[] artworkStatusStatic;

    public ArtworkContentPanel(String[] artworkID, String[] artworkName, String[] artworkArtist, String[] artworkPrice, String[] artworkStatus) {
        super(new GridBagLayout());

        this.artworkID = artworkID;
        this.artworkName = artworkName;
        this.artworkArtist = artworkArtist;
        this.artworkPrice = artworkPrice;
        this.artworkStatus = artworkStatus;

        GridBagConstraints gbc = new GridBagConstraints(); // 创建网格包约束
        gbc.fill = GridBagConstraints.BOTH; // 设置组件在水平和垂直方向上都铺满父容器
        gbc.insets = new Insets(5, 10, 5, 10); // 添加内边距
        gbc.weightx = 1.0; // 充满水平空间
        listPanel listHead = new listPanel("ID","Artwork Name","Artist","Price","Status");
        listHead.setBackground(new Color(0,0,0,100));
        listHead.setPreferredSize(new Dimension(0, 50)); // set the size of the list head
        listHead.setLayout(new BoxLayout(listHead, BoxLayout.X_AXIS));
        gbc.gridx = 0; // 每行只有一个card
        gbc.gridy = 0;
        gbc.ipady = 1; // 让每个card的高度随窗口改变
        add(listHead,gbc);
        for (int i = 0; i < this.artworkID.length; i++) {
            listPanel cardPage = new listPanel(this.artworkID[i],this.artworkName[i],this.artworkArtist[i],this.artworkPrice[i],this.artworkStatus[i]);
            cardPage.setBackground(new Color(0,0,0,100));
            cardPage.setPreferredSize(new Dimension(0, 50)); // 设置小窗口的大小
            cardPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            int finalI = i;
            cardPage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!config.EditWindowEnabled) {
                        super.mouseClicked(e);
                        new ArtworkEditWindow(artworkID[finalI]);
                        config.EditWindowEnabled = true;
                    }else {
                        JOptionPane.showMessageDialog(null, "Please close the edit window first!");
                    }
                }
            });
            gbc.gridx = 0; // 每行只有一个card
            gbc.gridy = i+1;
            gbc.ipady = 1; // 让每个card的高度随窗口改变
            add(cardPage,gbc);



        }
        Instance = this;
        artworkIDStatic = this.artworkID;
        artworkNameStatic = this.artworkName;
        artworkArtistStatic = this.artworkArtist;
        artworkPriceStatic = this.artworkPrice;
        artworkStatusStatic = this.artworkStatus;

}





public static void Panelupdate(String[] artworkID, String[] artworkName, String[] artworkArtist, String[] artworkPrice, String[] artworkStatus){

        Instance.removeAll();

        artworkIDStatic = artworkID;
        artworkNameStatic = artworkName;
        artworkArtistStatic = artworkArtist;
        artworkPriceStatic = artworkPrice;
        artworkStatusStatic = artworkStatus;

        GridBagConstraints gbc = new GridBagConstraints(); // 创建网格包约束
        gbc.fill = GridBagConstraints.BOTH; // 设置组件在水平和垂直方向上都铺满父容器
        gbc.insets = new Insets(5, 10, 5, 10); // 添加内边距
        gbc.weightx = 1.0; // 充满水平空间
        listPanel listHead = new listPanel("ID","Artwork Name","Artist","Price","Status");
        listHead.setBackground(new Color(0,0,0,100));
        listHead.setPreferredSize(new Dimension(0, 50)); // set the size of the list head
        listHead.setLayout(new BoxLayout(listHead, BoxLayout.X_AXIS));
        gbc.gridx = 0; // 每行只有一个card
        gbc.gridy = 0;
        gbc.ipady = 1; // 让每个card的高度随窗口改变
        Instance.add(listHead,gbc);
        for (int i = 0; i < artworkID.length; i++) {
            listPanel cardPage = new listPanel(artworkID[i],artworkName[i],artworkArtist[i],artworkPrice[i],artworkStatus[i]);
            cardPage.setBackground(new Color(0,0,0,100));
            cardPage.setPreferredSize(new Dimension(0, 50)); // 设置小窗口的大小
            cardPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            int finalI = i;
            cardPage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!config.EditWindowEnabled) {
                        super.mouseClicked(e);
                        new ArtworkEditWindow(artworkID[finalI]);
                        config.EditWindowEnabled = true;
                    }else {
                        JOptionPane.showMessageDialog(null, "Please close the edit window first!");
                    }
                }
            });
            gbc.gridx = 0; // 每行只有一个card
            gbc.gridy = i+1;
            gbc.ipady = 1; // 让每个card的高度随窗口改变
            Instance.add(cardPage,gbc);



        }
        Instance.revalidate();
        Instance.repaint();
    }



}
