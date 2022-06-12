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
import javafx.scene.web.WebEngine;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AddReport {


    @FXML
    HTMLEditor htmlEditor;

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
    String urlToImages;

    public static int editId = 0;



    @FXML
    void addSuggestion() {
        suggestions.add(suggestionTF.getText());
        suggestionTF.setText(null);
    }

    Connect connect;

    @FXML
    private void goHome() throws IOException {
        Main.changeScene("hello-view.fxml");
        reset();
    }

    @FXML
    private void initialize(){


        try {
            connect = new Connect();
            config  = new Config();
            urlToImages = config.getProp().getProperty("imageUrl");
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
                    if(t1 != null){
                        subCatCB.getItems().clear();
                        ResultSet subCats = connect.getSubCats(t1.getCatId());
                        while (subCats.next()){
                            subCatCB.getItems().add(new SubCat(subCats.getInt("id"), subCats.getString("subCatName"), new CategoryClass(t1.getCatName(), t1.getCatId())));
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        suggestionList.setItems(suggestions);


        suggestionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                suggestionTF.setText(t1);
            }
        });



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

        dateTF.setValue(LocalDate.now());

        // check if the page opened to edit report
        if(editId != 0){
            loadReport(editId);
        }else {

        }
    }

    private void loadReport(int editId) {
        try {
            Report report = null;
            ResultSet reportData = connect.getReport(editId);
            while (reportData.next()){
                CategoryClass categoryObject = new CategoryClass(reportData.getString("categoryName"), reportData.getInt("catId"));
                SubCat subCat = new SubCat(reportData.getInt("subCatId"), reportData.getString("subCatName"), categoryObject);
                report = new Report(editId, LocalDate.parse(reportData.getString("reportDate")), categoryObject, subCat
                        , reportData.getString("reportText"), LocalDate.parse(reportData.getString("createdAt")),
                        reportData.getString("title"), reportData.getInt("isRead"));
            }
            ResultSet suggestionSet = connect.getSuggestion(editId);
            List<Suggestion> suggestionArray = new ArrayList<>();
            while (suggestionSet.next()){
                suggestionArray.add(new Suggestion(editId, report, suggestionSet.getString("suggestionText"),
                        LocalDate.parse(suggestionSet.getString("createdAt"))));
                suggestions.add(suggestionSet.getString("suggestionText"));
            }
            Suggestion[] suggestionsArray = new Suggestion[suggestionArray.size()];
            int c = 0;
            for (Suggestion suggestion: suggestionArray){
                suggestionsArray[c] = suggestion;
                c++;
            }
            report.setSuggestions(suggestionsArray);


            ResultSet imagesSet = connect.getImages(report.getId());
            while (imagesSet.next()){
                ImageView imageView = new ImageView();
                imageView.setImage(new Image(urlToImages+imagesSet.getString("imageNewName")));
                uploadedImages.add(new UploadedImages(imageView, imagesSet.getString("imageName"), imagesSet.getString("imageNewName")));
            }
            report.setUploadedImagesList(uploadedImages);

            htmlEditor.setHtmlText(report.getReportText());
            submitBtn.setText("update");
            categoryCB.getItems().add(report.getCategory());
            categoryCB.setValue(report.getCategory());
            subCatCB.getItems().add(report.getSubCat());
            subCatCB.setValue(report.getSubCat());
            dateTF.setValue(report.getReportDate());
            titleTF.setText(report.getTitle());


        } catch (SQLException e) {
            e.printStackTrace();
            Notifications.create().title("unable to load report data").text(e.getMessage()).showError();
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.create().title("unable to load suggestions data").text(e.getMessage()).showError();
        }


    }

    private void reset(){
        editId = 0;
        htmlEditor.setHtmlText("");
        submitBtn.setText("Submit");
        dateTF.setValue(LocalDate.now());
        suggestions.clear();
        titleTF.setText(null);
        categoryCB.getItems().add(null);categoryCB.setValue(null);
        subCatCB.getItems().add(null);subCatCB.setValue(null);
        uploadedImages.clear();
    }

    @FXML
    private void editSuggestion(){
        String text = suggestionTF.getText();
        suggestions.remove(suggestionList.getSelectionModel().getSelectedItem());
        suggestions.add(text);

    }

    @FXML
    private void submit() {
        // check if we are going to add or edit
        if(editId ==0){

            submitBtn.setDisable(true);
            submitBtn.setText("please wait");
            int id = 0;
            try {
                id = connect.addReport(dateTF.getValue(),categoryCB.getValue().getCatId(), subCatCB.getValue().getSubCatId(), htmlEditor.getHtmlText(), titleTF.getText());
            } catch (Exception e) {
                e.printStackTrace();
                Notifications.create().title("Error").text(e.getMessage()).position(Pos.BOTTOM_RIGHT).showError();
            }
            int check = 0;
            for (String object: suggestions){
                try {
                    connect.addSuggestion(id, object);
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.create().title("Error").text(e.getMessage()).position(Pos.BOTTOM_RIGHT).showError();
                }
            }

            for (UploadedImages object : uploadedImages){
                try {
                    check = connect.addImage(id, object.getImageName(), object.getNewName());
                } catch (SQLException e) {
                    e.printStackTrace();
                    Notifications.create().title("Error adding Images").text(e.getMessage()).position(Pos.BOTTOM_RIGHT).showError();
                }
            }

            submitBtn.setDisable(false);

            if(id != 0){
                Notifications.create().title("Success").text("Report details added successfully").position(Pos.BOTTOM_RIGHT).showConfirm();
                submitBtn.setText("Add new");
            }else {
                Notifications.create().title("Error").text("Something went wrong").position(Pos.TOP_LEFT).showError();
                submitBtn.setText("Try Again");
            }

        }else {

            connect.deleteReport(editId);
            connect.deleteProjects(editId);
            connect.deleteSuggestion(editId);
            connect.deleteUploadedImages(editId);

            submitBtn.setDisable(true);
            submitBtn.setText("please wait");
            int id = 0;
            try {
                id = connect.addReport(dateTF.getValue(),categoryCB.getValue().getCatId(), subCatCB.getValue().getSubCatId(), htmlEditor.getHtmlText(), titleTF.getText());
            } catch (Exception e) {
                e.printStackTrace();
                Notifications.create().title("Error").text(e.getMessage()).position(Pos.BOTTOM_RIGHT).showError();
            }
            int check = 0;
            for (String object: suggestions){
                try {
                    connect.addSuggestion(id, object);
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.create().title("Error").text(e.getMessage()).position(Pos.BOTTOM_RIGHT).showError();
                }
            }

            for (UploadedImages object : uploadedImages){
                try {
                    check = connect.addImage(id, object.getImageName(), object.getNewName());
                } catch (SQLException e) {
                    e.printStackTrace();
                    Notifications.create().title("Error adding Images").text(e.getMessage()).position(Pos.BOTTOM_RIGHT).showError();
                }
            }

            submitBtn.setDisable(false);

            if(id != 0){
                Notifications.create().title("Success").text("Report details added successfully").position(Pos.BOTTOM_RIGHT).showConfirm();
                submitBtn.setText("Add new");
            }else {
                Notifications.create().title("Error").text("Something went wrong").position(Pos.TOP_LEFT).showError();
                submitBtn.setText("Try Again");
            }
        }

    }

    @FXML
    private void addNew(){
        reset();
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
                new FileChooser.ExtensionFilter(".jpg .PNG Files Only", "*.png", "*.jpg", "*.pdf")
        );
        List<File> fileArray = fileChooser.showOpenMultipleDialog(Main.stagePointer);

        for(File file : fileArray){
            // add imageView to observable list
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(file.toURI().toURL().toExternalForm()));

            String extension = "png";
            int index = file.getName().lastIndexOf('.');
            if (index > 0) {
                extension = file.getName().substring(index + 1);
            }

            String newFileName = System.currentTimeMillis()+"."+extension;

            uploadedImages.add(new UploadedImages(imageView, file.getName(), newFileName));

            sendFileToServer(file.getAbsolutePath(), newFileName);
        }

    }

    private int sendFileToServer(String url, String newName){
        int isDone = 0;
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(new URL(config.getProp().getProperty("url")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Notifications.create().text(e.getMessage()).show();
        }
        httpPost.setFileNames(new String[]{ url });
        httpPost.setNewName(newName);
        httpPost.post();
        String output = httpPost.getOutput();
        if(output.equals("Done")){
            isDone = 1;
        }

        return  isDone;
    }

    @FXML
    private void deleteImage(){
        uploadedImages.remove(listOfImages.getSelectionModel().getSelectedItem());
    }
}
