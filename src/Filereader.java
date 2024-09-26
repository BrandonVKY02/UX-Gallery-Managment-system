import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Filereader {

    private String[] artworkName;
    private String[] artworkID;
    private String[] artworkPrice;
    private String[] artist;
    private String[] status;
    private String[] detail;
    private String[] artistID;
    private String[] artistName;
    private String[] artistSpecialty;
    private String[] artistAlive;
    private String[] artistRange;
    private String[] artistCountry;
    private String[] customerID;
    private String[] customerName;
    private String[] customerPhone;
    private String[] customerPurchases;
    private String[] customerPreferences;
    private int x;
    private long count;
    public Filereader(String readtype) {

        if(readtype.equals("artwork") ){
            try {
                String filePath = config.artworkFilePath;
                BufferedReader reader = new BufferedReader(new FileReader(filePath));

                ArrayList<String> linesList = new ArrayList<>();
                String line = null;
                Stream<String> lines = reader.lines();
                count = lines.count();
                BufferedReader reader2 = new BufferedReader(new FileReader(filePath));
                x = (int) count / 6;
                artworkName = new String[x];
                artworkID = new String[x];
                artworkPrice = new String[x];
                artist = new String[x];
                status = new String[x];
                detail = new String[x];
                int n = 0;
                int j = 0;
                int r = 0;
                int a = 0;
                int s = 0;
                int d = 0;
                while ((line = reader2.readLine()) != null) {
                    if (line.startsWith("artworkName:")) {
                        artworkName[n] = line.substring(12).trim();
                        n++;
                    }
                    if (line.startsWith("artworkID:")) {
                        artworkID[j] = line.substring(10).trim();
                        j++;
                    }
                    if (line.startsWith("artworkPrice:")) {
                        artworkPrice[r] = line.substring(13).trim();
                        r++;
                    }
                    if (line.startsWith("artist:")) {
                        artist[a] = line.substring(7).trim();
                        a++;
                    }
                    if (line.startsWith("status:")) {
                        status[s] = line.substring(7).trim();
                        s++;
                    }
                    if (line.startsWith("detail:")) {
                        detail[d] = line.substring(7).trim();
                        d++;
                    }

                }

                reader2.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(readtype.equals("artist")){
            try {
                String filePath = config.artistDataPath;
                BufferedReader reader = new BufferedReader(new FileReader(filePath));

                ArrayList<String> linesList = new ArrayList<>();
                String line = null;
                Stream<String> lines = reader.lines();
                count = lines.count();
                BufferedReader reader2 = new BufferedReader(new FileReader(filePath));
                x = (int) count / 6;
                artistID = new String[x];
                artistName = new String[x];
                artistSpecialty = new String[x];
                artistAlive = new String[x];
                artistRange = new String[x];
                artistCountry = new String[x];
                int n = 0;
                int j = 0;
                int r = 0;
                int a = 0;
                int s = 0;
                int d = 0;
                while ((line = reader2.readLine()) != null) {
                    if (line.startsWith("ID:")) {
                        artistID[n] = line.substring(3).trim();
                        n++;
                    }
                    if (line.startsWith("Name:")) {
                        artistName[j] = line.substring(5).trim();
                        j++;
                    }
                    if (line.startsWith("Specialty:")) {
                        artistSpecialty[r] = line.substring(10).trim();
                        r++;
                    }
                    if (line.startsWith("Alive:")) {
                        artistAlive[a] = line.substring(6).trim();
                        a++;
                    }
                    if (line.startsWith("Price Range:")) {
                        artistRange[s] = line.substring(12).trim();
                        s++;
                    }
                    if (line.startsWith("Country:")) {
                        artistCountry[d] = line.substring(8).trim();
                        d++;
                    }

                }

                reader2.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(readtype.equals("customer")){
            try{
                String filePath = config.customerDataPath;
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                ArrayList<String> linesList = new ArrayList<>();
                String line = null;
                Stream<String> lines = reader.lines();
                count = lines.count();
                BufferedReader reader2 = new BufferedReader(new FileReader(filePath));
                x = (int) count / 5;
                customerID = new String[x];
                customerName = new String[x];
                customerPhone = new String[x];
                customerPurchases = new String[x];
                customerPreferences = new String[x];
                int n = 0;
                int j = 0;
                int r = 0;
                int a = 0;
                int s = 0;
                while ((line = reader2.readLine()) != null) {
                    if (line.startsWith("ID:")) {
                        customerID[n] = line.substring(3).trim();
                        n++;
                    }
                    if (line.startsWith("Name:")) {
                        customerName[j] = line.substring(5).trim();
                        j++;
                    }
                    if (line.startsWith("Phone Number:")) {
                        customerPhone[r] = line.substring(13).trim();
                        r++;
                    }
                    if (line.startsWith("Artwork Purchases:")) {
                        customerPurchases[a] = line.substring(18).trim();
                        a++;
                    }
                    if (line.startsWith("Artist Preferences:")) {
                        customerPreferences[s] = line.substring(19).trim();
                        s++;
                    }

                }

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public String returnArtworkName(int i){
        return artworkName[i];
    }
    public String returnArtworkID(int i){
        return artworkID[i];
    }
    public String returnArtworkPrice(int i){
        return artworkPrice[i];
    }
    public String returnArtist(int i){
        return artist[i];
    }
    public String returnStatus(int i){
        return status[i];
    }
    public String returnDetail(int i){
        return detail[i];
    }
    public String returnArtistID(int i){
        return artistID[i];
    }
    public String returnArtistName(int i){
        return artistName[i];
    }
    public String returnArtistSpecialty(int i){
        return artistSpecialty[i];
    }
    public String returnArtistAlive(int i){
        return artistAlive[i];
    }
    public String returnArtistRange(int i){
        return artistRange[i];
    }
    public String returnArtistCountry(int i){
        return artistCountry[i];
    }
    public String returnCustomerID(int i){
        return customerID[i];
    }
    public String returnCustomerName(int i){
        return customerName[i];
    }
    public String returnCustomerPhone(int i){
        return customerPhone[i];
    }
    public String returnCustomerPurchases(int i){
        return customerPurchases[i];
    }
    public String returnCustomerPreferences(int i){
        return customerPreferences[i];
    }
    public int returnCount(){
        return x;
    }

}

