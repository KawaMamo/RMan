import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class TaskDashBoard {

    @FXML
    private GridPane calendarGrid;

    @FXML
    private void initialize(){

        for(int i =0; i< 7; i++){
            for (int j = 0; j<6;j++){
                Button button = new Button(i+"-"+j);
                button.setMaxWidth(Double.MAX_VALUE);
                button.setMaxHeight(Double.MAX_VALUE);
                calendarGrid.add(button, i, j);
            }
        }

        ColumnConstraints[] columnConstraintses = new ColumnConstraints[7];
        RowConstraints[] rowConstraintses = new RowConstraints[6];

        for (int s = 0 ; s < 6 ; s++) {
            columnConstraintses[s] = new ColumnConstraints();
            columnConstraintses[s].setPercentWidth(100.0/(7));
            columnConstraintses[s].setHalignment(HPos.CENTER);
            columnConstraintses[s].setFillWidth(true);
            columnConstraintses[s].setHgrow(Priority.SOMETIMES);

            calendarGrid.getColumnConstraints().add(columnConstraintses[s]);
        }

        for (int s = 0 ; s < 5 ; s++) {
            rowConstraintses[s] = new RowConstraints();
            rowConstraintses[s].setPercentHeight(100.0/(6));
            rowConstraintses[s].setValignment(VPos.CENTER);
            rowConstraintses[s].setFillHeight(true);
            rowConstraintses[s].setVgrow(Priority.SOMETIMES);

            calendarGrid.getRowConstraints().add(rowConstraintses[s]);
        }
    }

}
