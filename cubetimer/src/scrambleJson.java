import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class scrambleJson {
    // Connect to the URL using java's native library
    public String getScramble() throws IOException{
        // Connect to the URL using java's native library
        URL url = new URL("https://scrambler-api.herokuapp.com/3x3x3");
        URLConnection request = url.openConnection();
        request.connect();
        try {
        JSONParser parser = new JSONParser();
        JSONArray scrambles = (JSONArray) parser.parse(new InputStreamReader((request.getInputStream())));
        //returning scramble
        return scrambles.get(0).toString();
        
        }catch (IOException e) {
            System.out.println("I/O Error");
        } catch (ParseException e) {
            System.out.println("Parse Error");
        }
		return null;
    }

}
