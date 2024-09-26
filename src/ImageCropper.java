import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import net.coobird.thumbnailator.Thumbnails;
import javax.swing.filechooser.FileSystemView;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageCropper {
    private static BufferedImage croppedImage;
    private static Rectangle cropRectangle;
    private static String fileName;
    public ImageCropper(String fileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        this.fileName = fileName;
        // 获取文件系统视图
        FileSystemView view = FileSystemView.getFileSystemView();

        // 获取用户默认的图片文件夹路径
        File defaultPicturesFolder = view.getDefaultDirectory();
        fileChooser.setInitialDirectory(defaultPicturesFolder);
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                BufferedImage originalImage = ImageIO.read(selectedFile);

                // get the width and height of the original image
                int width = originalImage.getWidth();
                int height = originalImage.getHeight();

                // compute the aspect ratio of the original image
                double aspectRatio = (double) width / (double) height;

                // set the crop rectangle based on the aspect ratio
                int cropWidth = (int) Math.round(height * 8.0 / 15.0);
                int cropX = (width - cropWidth) / 2;
                cropRectangle = new Rectangle(cropX, 0, cropWidth, height);

                croppedImage = Thumbnails.of(originalImage)
                        .sourceRegion(cropRectangle.x, cropRectangle.y, cropRectangle.width, cropRectangle.height)
                        .size(400, 400)
                        .asBufferedImage();


                if(config.EditWindowEnabled){
                ImageIcon icon = new ImageIcon(croppedImage);
                Image image = icon.getImage().getScaledInstance(ArtworkEditWindow.previewLabel.getWidth(), ArtworkEditWindow.previewLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(image);
                    ArtworkEditWindow.previewLabel.setIcon(scaledIcon);
                    ArtworkEditWindow.ImageChanged = true;
                }
                if(config.AddWindowEnabled){
                    ImageIcon icon = new ImageIcon(croppedImage);
                    Image image = icon.getImage().getScaledInstance(ArtworkAddWindow.previewLabel.getWidth(), ArtworkAddWindow.previewLabel.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(image);
                    ArtworkAddWindow.previewLabel.setIcon(scaledIcon);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    public static void saveImage() throws IOException {
        String extension = "png";
        Path filePath = Paths.get("./img/preview/" + fileName + "." + extension);
        Files.createDirectories(filePath.getParent());
        Thumbnails.of(croppedImage)
                .size(cropRectangle.width, cropRectangle.height)
                .outputFormat(extension)
                .toFile(filePath.toFile());
    }


}
