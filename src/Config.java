import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private Properties prop = new Properties();
    String fileName = "app.config";

    public Config() throws IOException {
        FileReader reader=new FileReader("app.config");
        prop.load(reader);
    }

    public void write() throws IOException {
        prop.store(new FileWriter(fileName), "#properties list");
    }

    public Properties getProp(){
        return prop;
    }

}
