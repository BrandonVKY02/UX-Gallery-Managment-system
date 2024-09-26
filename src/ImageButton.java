import javax.swing.*;
import java.awt.event.*;

public class ImageButton extends JButton implements MouseListener {
    private ImageIcon normalIcon;
    private ImageIcon hoverIcon;
    private ImageIcon pressedIcon;

    public ImageButton(ImageIcon normalIcon, ImageIcon hoverIcon, ImageIcon pressedIcon) {
        this.normalIcon = normalIcon;
        this.hoverIcon = hoverIcon;
        this.pressedIcon = pressedIcon;

        setIcon(normalIcon);
        setBorderPainted(false);
        setContentAreaFilled(false);
        addMouseListener(this);
    }

    public void setImageIcon(ImageIcon normalIcon, ImageIcon hoverIcon, ImageIcon pressedIcon) {
        this.normalIcon = normalIcon;
        this.hoverIcon = hoverIcon;
        this.pressedIcon = pressedIcon;
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        setIcon(pressedIcon);
    }

    public void mouseReleased(MouseEvent e) {
        if (contains(e.getPoint())) {
            setIcon(hoverIcon);
        } else {
            setIcon(normalIcon);
        }
    }

    public void mouseEntered(MouseEvent e) {
        setIcon(hoverIcon);
    }

    public void mouseExited(MouseEvent e) {
        setIcon(normalIcon);
    }
}

