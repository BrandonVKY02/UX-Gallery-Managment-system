import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtworkManager {
    private static final String FILE_NAME = config.artworkFilePath;

    private List<Artwork> artworks;

    public ArtworkManager() {
        this.artworks = new ArrayList<>();
        readArtworksFromFile();
    }

    public void updateArtwork(int artworkId, String artworkName, String artworkPrice,
                              String artist, String status , String detail) {
        for (Artwork artwork : artworks) {
            if (artwork.getArtworkId() == artworkId) {
                artwork.setArtworkName(artworkName);
                artwork.setArtworkPrice(artworkPrice);
                artwork.setArtist(artist);
                artwork.setStatus(status);
                artwork.setDetail(detail);
                break;
            }
        }

        saveArtworksToFile();
    }

    private void readArtworksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            Artwork artwork = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("artworkName:")) {
                    if (artwork != null) {
                        artworks.add(artwork);
                    }
                    artwork = new Artwork();
                    artwork.setArtworkName(line.substring(12));
                } else if (line.startsWith("artworkID:")) {
                    artwork.setArtworkId(Integer.parseInt(line.substring(10)));
                } else if (line.startsWith("artworkPrice:")) {
                    artwork.setArtworkPrice(line.substring(13));
                } else if (line.startsWith("artist:")) {
                    artwork.setArtist(line.substring(7));
                } else if (line.startsWith("status:")) {
                    artwork.setStatus(line.substring(7));
                } else if (line.startsWith("detail:")) {
                    artwork.setDetail(line.substring(7));
                }
            }

            if (artwork != null) {
                artworks.add(artwork);
            }
        } catch (IOException e) {
            System.out.println("Error reading artworks from file.");
            e.printStackTrace();
        }
    }

    private void saveArtworksToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Artwork artwork : artworks) {
                bw.write("artworkName:" + artwork.getArtworkName());
                bw.newLine();
                bw.write("artworkID:" + artwork.getArtworkId());
                bw.newLine();
                bw.write("artworkPrice:" + artwork.getArtworkPrice());
                bw.newLine();
                bw.write("artist:" + artwork.getArtist());
                bw.newLine();
                bw.write("status:" + artwork.getStatus());
                bw.newLine();
                bw.write("detail:" + artwork.getDetail());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing artworks to file.");
            e.printStackTrace();
        }
    }

    public void addArtwork(int artworkId, String artworkName, String artworkPrice,
                           String artist, String status , String detail) {


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write("artworkName:" + artworkName);
            bw.newLine();
            bw.write("artworkID:" + artworkId);
            bw.newLine();
            bw.write("artworkPrice:" + artworkPrice);
            bw.newLine();
            bw.write("artist:" + artist);
            bw.newLine();
            bw.write("status:" + status);
            bw.newLine();
            bw.write("detail:" + detail);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing artwork to file.");
            e.printStackTrace();
        }
    }
    private static class Artwork {
        private String artworkName;
        private int artworkId;
        private String artworkPrice;
        private String artist;
        private String status;
        private String detail;


        public String getArtworkName() {
            return artworkName;
        }

        public void setArtworkName(String artworkName) {
            this.artworkName = artworkName;
        }

        public int getArtworkId() {
            return artworkId;
        }

        public void setArtworkId(int artworkId) {
            this.artworkId = artworkId;
        }

        public String getArtworkPrice() {
            return artworkPrice;
        }

        public void setArtworkPrice(String artworkPrice) {
            this.artworkPrice = artworkPrice;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getDetail() {
            return detail;
        }
        public void setDetail(String detail) {
            this.detail = detail;
        }

    }
}

