import javafx.embed.swing.JFXPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // 初始化JavaFX环境
            new LoginWindow();// 创建并显示Swing应用程序窗口
        });

    }
}