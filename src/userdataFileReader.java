import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;
public class userdataFileReader {
    public String[] UID;

    public userdataFileReader(String filePath){


        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            ArrayList<String> linesList = new ArrayList<>();
            String line = null;
            Stream<String> lines = reader.lines();
            long count = lines.count();
            BufferedReader reader2 = new BufferedReader(new FileReader(filePath));
            int x = (int)count/4;
            UID = new String[x];
            int n = 0;
            while ((line = reader2.readLine()) != null) {
                if(line.startsWith("Uid:")){
                    UID[n] = line.substring(4).trim();
                    n++;
                }

            }

            reader2.close();


        }catch(IOException e){
            e.printStackTrace();
        }


    }
    public String returnUID(){
        return UID[UID.length-1];
    }



}
