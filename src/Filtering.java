import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Filtering {

    @FXML
    private ListView<Project> projectsList;

    @FXML
    private ListView<Report> searchListView;

    public static ObservableList<Report> reportsList = FXCollections.observableArrayList();

    @FXML
    private WebView webView;

    @FXML
    private ChoiceBox<CategoryClass> catCB;

    @FXML
    private DatePicker reportDate;

    @FXML
    private Button searchBtn;

    @FXML
    private ListView<Suggestion> suggestionsList;

    @FXML
    private ChoiceBox<SubCat> subCatCB;

    @FXML
    private TextField searchTF;

    @FXML
    private ListView<UploadedImages> listOfImages;

    ObservableList<UploadedImages> uploadedImagesToShow = FXCollections.observableArrayList();

    String urlToImages;

    Connect connect;
    Config config;

    ObservableList<Suggestion> suggestionObservableList = FXCollections.observableArrayList();
    ObservableList<Project> projectObservableList = FXCollections.observableArrayList();

    public static Stage modal;

    @FXML
    void search() {

        Map<String, String> whereClauseArgs = new HashMap<>();

        if(searchTF.getText() != null){
            whereClauseArgs.put("reportText", searchTF.getText());
        }

        if(catCB.getValue() != null){
            whereClauseArgs.put("catId", String.valueOf(catCB.getValue().getCatId()));
        }

        if(subCatCB.getValue() != null){
            whereClauseArgs.put("subCatId", String.valueOf(subCatCB.getValue().getSubCatId()));
        }

        if(reportDate.getValue() != null){
            whereClauseArgs.put("reportDate", reportDate.getValue().toString());
        }
        try {
            loadReports(whereClauseArgs);
        } catch (SQLException e) {
            e.printStackTrace();
            Notifications error = Notifications.create().text(e.getMessage()).title("unable to connect").position(Pos.BASELINE_LEFT).hideAfter(Duration.hours(1));
            error.showError();
        }

    }

    private void loadReports(Map whereClause) throws SQLException {
        reportsList.clear();

        ResultSet reports = connect.getReports(whereClause);
        while (reports.next()){
            CategoryClass categoryClass = new CategoryClass(reports.getString("categoryName"), reports.getInt("catId"));
            Report report = new Report(reports.getInt("id"), LocalDate.parse(reports.getString("reportDate")),
                    categoryClass, new SubCat(reports.getInt("subCatId"), reports.getString("subCatName"),
                    categoryClass), reports.getString("reportText"), LocalDate.parse(reports.getString("createdAt")),
                    reports.getString("title"));
            reportsList.add(report);
            try {
                ResultSet suggestionSet = connect.getSuggestion(report.getId());
                ObservableList<Suggestion> tempSuggest = FXCollections.observableArrayList();

                while (suggestionSet.next()){
                    tempSuggest.add(new Suggestion(suggestionSet.getInt("id"), report,
                            suggestionSet.getString("suggestionText"),
                            LocalDate.parse(suggestionSet.getString("createdAt"))));
                }

                Suggestion[] suggestions = new Suggestion[tempSuggest.size()];
                int c = 0;
                for (Suggestion suggestion: tempSuggest){
                    suggestions[c] = suggestion;
                    c++;
                }

                report.setSuggestions(suggestions);
            } catch (Exception e) {
                e.printStackTrace();
                Notifications error = Notifications.create().text(e.getMessage()).title("Loading suggestions Error").position(Pos.BASELINE_LEFT).hideAfter(Duration.hours(1));
                error.showError();
            }

            try {
                ObservableList<Project> tempProject = FXCollections.observableArrayList();
                ResultSet projectSet = connect.getProjects(report.getId());
                while (projectSet.next()){
                    tempProject.add(new Project(projectSet.getInt("id"), report,
                            projectSet.getString("projectsText"),
                            LocalDate.parse(projectSet.getString("createdAt"))));
                }

                Project[] projects = new Project[tempProject.size()];
                int c = 0;
                for (Project project: tempProject){
                    projects[c] = project;
                    c++;
                }
                report.setProjects(projects);

            } catch (Exception e) {
                e.printStackTrace();
                Notifications error = Notifications.create().text(e.getMessage()).title("Loading Projects Error").position(Pos.BASELINE_LEFT).hideAfter(Duration.hours(1));
                error.showError();
            }

            try {
                ObservableList<UploadedImages> uploadedImages = FXCollections.observableArrayList();
                ResultSet images = connect.getImages(report.getId());
                while (images.next()){
                    // add imageView to observable list
                    ImageView imageView = new ImageView();
                    imageView.setImage(new Image(urlToImages+images.getString("imageNewName")));
                    uploadedImages.add(new UploadedImages(imageView, images.getString("imageName"), images.getString("imageNewName")));

                }
                report.setUploadedImagesList(uploadedImages);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void initialize(){
        try {
            connect = new Connect();
            config  = new Config();
            urlToImages = config.getProp().getProperty("imageUrl");
        } catch (IOException e) {
            e.printStackTrace();
            Notifications error = Notifications.create().text(e.getMessage()).title("unable to connect").position(Pos.BASELINE_LEFT).hideAfter(Duration.hours(1));
            error.showError();
        }

        try {
            ResultSet cats = connect.getCatList();
            while (cats.next()){
                catCB.getItems().add(new CategoryClass(cats.getString("categoryName"), cats.getInt("id")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        catCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CategoryClass>() {
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

        searchListView.setItems(reportsList);
        searchListView.setCellFactory(param -> new ListCell<Report>(){
            @Override
            protected void updateItem(Report report, boolean b) {
                super.updateItem(report, b);
                if (b || report == null || report.getCategory() == null) {
                    setText(null);
                } else {
                    setText(report.getCategory().getCatName()+" :: "+report.getSubCat().getSubCatName()+" :: "+report.getTitle());
                }
            }
        });

        searchListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Report>() {
            @Override
            public void changed(ObservableValue<? extends Report> observableValue, Report report, Report t1) {
                WebEngine webEngine = webView.getEngine();
                webEngine.loadContent(t1.getReportText(), "text/html");
                suggestionObservableList.clear();
                suggestionObservableList.addAll(t1.getSuggestions());
                suggestionsList.setItems(suggestionObservableList);

                projectObservableList.clear();
                projectObservableList.addAll(t1.getProjects());
                projectsList.setItems(projectObservableList);

                uploadedImagesToShow.clear();
                uploadedImagesToShow.addAll(t1.getUploadedImagesList());
                listOfImages.setItems(uploadedImagesToShow);
            }
        });

        searchListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2){
                    AddReport.editId = searchListView.getSelectionModel().getSelectedItems().get(0).getId();
                    try {
                        Main.changeScene("addReport.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(event.getButton().equals(MouseButton.SECONDARY)){
                    Parent root = null;
                    try {
                        AreYouSure.report = searchListView.getSelectionModel().getSelectedItem();
                        root = FXMLLoader.load(getClass().getResource("areYouSure.fxml"));
                        modal = new Stage();
                        modal.setScene(new Scene(root));
                        modal.initModality(Modality.APPLICATION_MODAL);
                        modal.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        suggestionsList.setCellFactory(param -> new ListCell<Suggestion>(){
            @Override
            protected void updateItem(Suggestion suggestion, boolean b){
                super.updateItem(suggestion, b);
                if (b || suggestion == null || suggestion.getSuggestionText() == null) {
                    setText(null);
                } else {
                    setText(suggestion.getSuggestionText());
                }
            }
        });

        projectsList.setCellFactory(param -> new ListCell<Project>(){
            @Override
            protected void updateItem(Project project, boolean b){
                super.updateItem(project, b);
                if (b || project == null || project.getProjectsText() == null) {
                    setText(null);
                } else {
                    setText(project.getProjectsText());
                }
            }
        });

        webView.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if(event.getDeltaY()>0 && event.isControlDown()){
                    webView.setZoom(1.1*webView.getZoom());
                }else if(event.getDeltaY()<0 && event.isControlDown()){
                    webView.setZoom(webView.getZoom()/1.1);
                }

            }
        });

        webView.getChildrenUnmodifiable().addListener(new ListChangeListener<Node>() {
            @Override public void onChanged(Change<? extends Node> change) {
                Set<Node> deadSeaScrolls = webView.lookupAll(".scroll-bar");
                for (Node scroll : deadSeaScrolls) {
                    scroll.setVisible(false);
                }
            }
        });

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
    private void goHome() throws IOException {
        Main.changeScene("hello-view.fxml");
    }

    @FXML
    private void reset(){
        reportDate.setValue(null);
        catCB.setValue(null);
        subCatCB.setValue(null);
        searchTF.setText(null);

    }

    @FXML
    private void preview(){

    }

}
