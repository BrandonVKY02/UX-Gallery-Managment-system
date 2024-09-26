import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ImagePanel extends JPanel {
    private int cornerRadius = 30; // 圆角半径
    private Image image;
    private float alpha = 1f;

    public ImagePanel(String filename) {
        super();
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOpaque(false);
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImagePanel(LayoutManager layout) {
        super(layout);
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
        g2.setComposite(ac);
        g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g2.setColor(new Color(225,225,225,0));
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.dispose();
        super.paintComponent(g);
    }
}
