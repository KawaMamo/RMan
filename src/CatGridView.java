import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.ResultSet;

public class CatGridView {

    @FXML
    private GridPane gridPane;

    @FXML
    private void initialize(){
        try {
            Connect connect = new Connect();
            ResultSet cats = connect.getCatList();
            int catCount = cats.getInt("counter");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
