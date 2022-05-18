import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button dataEntryBtn;

    @FXML
    private ImageView managerBtn;

    @FXML
    void dataEntry() throws IOException {

        Main.changeScene("altAddReport.fxml");
    }

    @FXML
    void manager(ActionEvent event) {

    }

    @FXML
    private void setting() throws IOException {
        Main.changeScene("categoryManager.fxml");
    }

    @FXML
    private void browser() throws IOException {
        Main.changeScene("filtering.fxml");
    }

    @FXML
    private void browsing() throws IOException {
        Main.changeScene("catGridView.fxml");
    }

    @FXML
    private void taskManager() throws IOException {
        Main.changeScene("taskDashBoard.fxml");
    }

}