import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtistManager {
    private static final String FILE_NAME = config.artistDataPath;

    private List<Artwork> artist;

    public ArtistManager() {
        this.artist = new ArrayList<>();
        readArtistFromFile();
    }

    public void editArtist(int artistID, String artistName, String artistSpecialty, String artistAlive, String artistRange, String artistCountry) {
        for (Artwork artwork : this.artist) {
            if (artwork.getArtistID() == artistID) {
                artwork.setArtistID(artistID);
                artwork.setArtistName(artistName);
                artwork.setArtistSpecialty(artistSpecialty);
                artwork.setArtistAlive(artistAlive);
                artwork.setArtistRange(artistRange);
                artwork.setArtistCountry(artistCountry);
                break;
            }
        }

        saveArtistToFile();
    }

    private void readArtistFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            Artwork artist = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("ID:")) {
                    if (artist != null) {
                        this.artist.add(artist);
                    }
                    artist = new Artwork();
                    artist.setArtistID(Integer.parseInt(line.substring(3)));
                } else if (line.startsWith("Name:")) {
                    artist.setArtistName(line.substring(5));
                } else if (line.startsWith("Specialty:")) {
                    artist.setArtistSpecialty(line.substring(10));
                } else if (line.startsWith("Alive:")) {
                    artist.setArtistAlive(line.substring(6));
                } else if (line.startsWith("Price Range:")) {
                    artist.setArtistRange(line.substring(12));
                } else if (line.startsWith("Country:")) {
                    artist.setArtistCountry(line.substring(8));
                }
            }

            if (artist != null) {
                this.artist.add(artist);
            }
        } catch (IOException e) {
            System.out.println("Error reading artworks from file.");
            e.printStackTrace();
        }
    }

    private void saveArtistToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Artwork artwork : artist) {
                bw.write("ID:" + artwork.getArtistID());
                bw.newLine();
                bw.write("Name:" + artwork.getArtistName());
                bw.newLine();
                bw.write("Specialty:" + artwork.getArtistSpecialty());
                bw.newLine();
                bw.write("Alive:" + artwork.getArtistAlive());
                bw.newLine();
                bw.write("Price Range:" + artwork.getArtistRange());
                bw.newLine();
                bw.write("Country:" + artwork.getArtistCountry());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing artworks to file.");
            e.printStackTrace();
        }
    }

    public void addArtist(int artistID, String artistName, String artistSpecialty, String artistAlive, String artistRange, String artistCountry){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write("ID:" + artistID);
            bw.newLine();
            bw.write("Name:" + artistName);
            bw.newLine();
            bw.write("Specialty:" + artistSpecialty);
            bw.newLine();
            bw.write("Alive:" + artistAlive);
            bw.newLine();
            bw.write("Price Range:" + artistRange);
            bw.newLine();
            bw.write("Country:" + artistCountry);
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error writing artwork to file.");
            e.printStackTrace();
        }

    }


    private static class Artwork {
        private int artistID;
        private String artistName;
        private String artistSpecialty;
        private String ArtistAlive;
        private String ArtistRange;
        private String ArtistCountry;


        public int getArtistID() {
            return artistID;
        }

        public void setArtistID(int artistID) {
            this.artistID = artistID;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public String getArtistSpecialty() {
            return artistSpecialty;
        }

        public void setArtistSpecialty(String artistSpecialty) {
            this.artistSpecialty = artistSpecialty;
        }

        public String getArtistAlive() {
            return ArtistAlive;
        }

        public void setArtistAlive(String ArtistAlive) {
            this.ArtistAlive = ArtistAlive;
        }

        public String getArtistRange() {
            return ArtistRange;
        }

        public void setArtistRange(String ArtistRange) {
            this.ArtistRange = ArtistRange;
        }
        public String getArtistCountry() {
            return ArtistCountry;
        }
        public void setArtistCountry(String ArtistCountry) {
            this.ArtistCountry = ArtistCountry;
        }

    }
}

