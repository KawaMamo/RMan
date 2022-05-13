import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AddReport {


    @FXML
    HTMLEditor htmlEditor;

    @FXML
    private ListView<String> projectsList;

    ObservableList<String> projects = FXCollections.observableArrayList();

    @FXML
    private DatePicker dateTF;

    @FXML
    private ChoiceBox<SubCat> subCatCB;

    @FXML
    private TextField suggestionTF;

    @FXML
    private TextField projectsTF;

    @FXML
    private Button submitBtn;

    @FXML
    private ChoiceBox<CategoryClass> categoryCB;

    ObservableList<String> suggestions = FXCollections.observableArrayList();
    @FXML
    private ListView<String> suggestionList;

    @FXML
    private ListView<UploadedImages> listOfImages;

    @FXML
    private TextField titleTF;

    ObservableList<UploadedImages> uploadedImages = FXCollections.observableArrayList();

    Config config;


    @FXML
    void addSuggestion() {
        suggestions.add(suggestionTF.getText());
        suggestionTF.setText(null);
    }

    @FXML
    void addProjects() {
        projects.add(projectsTF.getText());
        projectsTF.setText(null);
    }

    Connect connect;

    @FXML
    private void goHome() throws IOException {
        Main.changeScene("hello-view.fxml");
    }

    @FXML
    private void initialize(){

        try {
            connect = new Connect();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("connection error");
        }

        try {
            ResultSet cats = connect.getCatList();
            while (cats.next()){
                categoryCB.getItems().add(new CategoryClass(cats.getString("categoryName"), cats.getInt("id")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        categoryCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CategoryClass>() {
            @Override
            public void changed(ObservableValue<? extends CategoryClass> observableValue, CategoryClass categoryClass, CategoryClass t1) {
                try {
                    subCatCB.getItems().clear();
                    ResultSet subCats = connect.getSubCats(t1.getCatId());
                    while (subCats.next()){
                        subCatCB.getItems().add(new SubCat(subCats.getInt("id"), subCats.getString("subCatName"), new CategoryClass(t1.getCatName(), t1.getCatId())));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        projectsList.setItems(projects);
        suggestionList.setItems(suggestions);

        projectsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                projectsTF.setText(t1);
            }
        });

        suggestionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                suggestionTF.setText(t1);
            }
        });

        try {
            config = new Config();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listOfImages.setItems(uploadedImages);

        listOfImages.setCellFactory(param -> new ListCell<UploadedImages>(){
            @Override
            public void updateItem(UploadedImages uploadedImages, boolean empty) {
                super.updateItem(uploadedImages, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(uploadedImages.getImageName());
                    setGraphic(uploadedImages.getImageView());
                }
            }
        });

    }

    @FXML
    private void editSuggestion(){
        String text = suggestionTF.getText();
        suggestions.remove(suggestionList.getSelectionModel().getSelectedItem());
        suggestions.add(text);

    }

    @FXML
    private void editProjects(){
        String text = projectsTF.getText();
        projects.remove(projectsList.getSelectionModel().getSelectedItem());
        projects.add(text);
    }

    @FXML
    private void deleteProjects(){
        projects.remove(projectsList.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void submit() {

        submitBtn.setDisable(true);
        submitBtn.setText("please wait");
        int id = 0;
        try {
            id = connect.addReport(dateTF.getValue(),categoryCB.getValue().getCatId(), subCatCB.getValue().getSubCatId(), htmlEditor.getHtmlText(), titleTF.getText());
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.create().title("Error").text(e.getMessage()).hideAfter(Duration.hours(1)).position(Pos.BOTTOM_RIGHT).showError();
        }
        int check = 0;
        for (String object: suggestions){
            try {
                connect.addSuggestion(id, object);
            } catch (Exception e) {
                e.printStackTrace();
                Notifications.create().title("Error").text(e.getMessage()).hideAfter(Duration.hours(1)).position(Pos.BOTTOM_RIGHT).showError();
            }
        }

        for (String object: projects){
            try {
                check = connect.addProject(id, object);
            } catch (Exception e) {
                e.printStackTrace();
                Notifications.create().title("Error").text(e.getMessage()).hideAfter(Duration.hours(1)).position(Pos.BOTTOM_RIGHT).showError();
            }
        }

        for (UploadedImages object : uploadedImages){
            try {
                check = connect.addImage(id, object.getImageName(), object.getNewName());
            } catch (SQLException e) {
                e.printStackTrace();
                Notifications.create().title("Error adding Images").text(e.getMessage()).hideAfter(Duration.minutes(15)).position(Pos.BOTTOM_RIGHT).showError();
            }
        }

        submitBtn.setDisable(false);

        if(id != 0){
            Notifications.create().title("Success").text("Report details added successfully").hideAfter(Duration.hours(1)).position(Pos.BOTTOM_RIGHT).showConfirm();
            submitBtn.setText("Add new");
        }else {
            Notifications.create().title("Error").text("Something went wrong").hideAfter(Duration.hours(1)).position(Pos.TOP_LEFT).showError();
            submitBtn.setText("Try Again");
        }

    }

    @FXML
    private void deleteSuggestion(){
        suggestions.remove(suggestionList.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void chooseImage() throws MalformedURLException {
        // file chooser with specific extensions
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".jpg .PNG Files Only", "*.png", "*.jpg")
        );
        List<File> fileArray = fileChooser.showOpenMultipleDialog(Main.stagePointer);

        for(File file : fileArray){
            // add imageView to observable list
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(file.toURI().toURL().toExternalForm()));

            String newFileName = System.currentTimeMillis()+".png";

            uploadedImages.add(new UploadedImages(imageView, file.getName(), newFileName));

            sendFileToServer(file.getAbsolutePath(), newFileName);
        }

    }

    private int sendFileToServer(String url, String newName){
        int isDone = 0;
        try {

            HttpPost httpPost = new HttpPost(new URL(config.getProp().getProperty("url")));
            httpPost.setFileNames(new String[]{ url });
            httpPost.setNewName(newName);
            httpPost.post();
            String output = httpPost.getOutput();
            if(output.equals("Done")){
                isDone = 1;
            }
        }catch (Exception e){

        }

        return  isDone;
    }
}