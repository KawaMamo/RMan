import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            int j = 0;
            while (cats.next()){
                String categoryName = cats.getString("categoryName");
                int id = cats.getInt("id");
                Button button = new Button(categoryName);
                button.setMaxWidth(Double.MAX_VALUE);
                button.setMaxHeight(Double.MAX_VALUE);
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Notifications.create().text(id+" "+categoryName).showConfirm();
                    }
                });
                gridPane.add(button, j, i);
                j++;
                if(j==numberOfRows){
                    j=0;
                    i++;
                }
            }

            ColumnConstraints[] columnConstraintses = new ColumnConstraints[numberOfRows];
            RowConstraints[] rowConstraintses = new RowConstraints[numberOfRows];

            for (int s = 0 ; s < numberOfRows-1 ; s++) {
                System.out.println(s);
                columnConstraintses[s] = new ColumnConstraints();
                columnConstraintses[s].setPercentWidth(100/(numberOfRows));
                columnConstraintses[s].setHalignment(HPos.CENTER);
                columnConstraintses[s].setFillWidth(true);
                columnConstraintses[s].setHgrow(Priority.SOMETIMES);

                rowConstraintses[s] = new RowConstraints();
                rowConstraintses[s].setPercentHeight(100/(numberOfRows));
                rowConstraintses[s].setValignment(VPos.CENTER);
                rowConstraintses[s].setFillHeight(true);
                rowConstraintses[s].setVgrow(Priority.SOMETIMES);

                gridPane.getRowConstraints().add(rowConstraintses[s]);
                gridPane.getColumnConstraints().add(columnConstraintses[s]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void goHome() throws IOException {
        Main.changeScene("hello-view.fxml");
    }
}
