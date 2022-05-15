import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
            int catCount = connect.getCatCount();
            int numberOfRows = (int)Math.ceil(Math.sqrt(catCount));
            ResultSet cats = connect.getCatList();
            int i = 0;

            while (cats.next()){
                if(i<numberOfRows){
                    for (int j = 0; j<numberOfRows; j++){
                        gridPane.add(new Label(cats.getString("categoryName")), i, j);
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
