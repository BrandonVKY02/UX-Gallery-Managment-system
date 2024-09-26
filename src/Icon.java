import javax.swing.*;
import java.awt.*;

public class Icon {
    public static final ImageIcon HOME_ICON_NORMAL = icon("./Ico/Home_normal.png");
    public static final ImageIcon HOME_ICON_HOVER = icon("./Ico/Home_hover.png");
    public static final ImageIcon HOME_ICON_PRESSED = icon("./Ico/Home_pressed.png");
    public static final ImageIcon ADD_ICON_NORMAL = icon("./Ico/add_normal.png");
    public static final ImageIcon ADD_ICON_HOVER = icon("./Ico/add_hover.png");
    public static final ImageIcon ADD_ICON_PRESSED = icon("./Ico/add_pressed.png");
    public static final ImageIcon SEARCH_ICON_NORMAL = icon("./Ico/search_normal.png");
    public static final ImageIcon SEARCH_ICON_HOVER = icon("./Ico/search_hover.png");
    public static final ImageIcon SEARCH_ICON_PRESSED = icon("./Ico/search_pressed.png");
    public static final ImageIcon LIST_ICON_ARTWORK_NORMAL = icon("./Ico/list_artwork_normal.png");
    public static final ImageIcon LIST_ICON_ARTWORK_HOVER = icon("./Ico/list_artwork_hover.png");
    public static final ImageIcon LIST_ICON_ARTWORK_PRESSED = icon("./Ico/list_artwork_pressed.png");
    public static final ImageIcon LIST_ICON_ARTIST_NORMAL = icon("./Ico/list_artist_normal.png");
    public static final ImageIcon LIST_ICON_ARTIST_HOVER = icon("./Ico/list_artist_hover.png");
    public static final ImageIcon LIST_ICON_ARTIST_PRESSED = icon("./Ico/list_artist_pressed.png");
    public static final ImageIcon LIST_ICON_CUSTOMER_NORMAL = icon("./Ico/list_customer_normal.png");
    public static final ImageIcon LIST_ICON_CUSTOMER_HOVER = icon("./Ico/list_customer_hover.png");
    public static final ImageIcon LIST_ICON_CUSTOMER_PRESSED = icon("./Ico/list_customer_pressed.png");


    private  static ImageIcon icon(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        Image temp = imageIcon.getImage().getScaledInstance(70, 70, imageIcon.getImage().SCALE_DEFAULT);
        return new ImageIcon(temp);
    }
}
