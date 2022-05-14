import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryManager {

    ObservableList<CategoryClass> categoryObservable = FXCollections.observableArrayList();

    @FXML
    private ListView<CategoryClass> catList;

    ObservableList<SubCat> subCatObservableList = FXCollections.observableArrayList();
    @FXML
    private ListView<SubCat> subCatList;

    @FXML
    private TextField subCatTF;

    @FXML
    private TextField categoryTF;

    private Connect connect;

    private int selectedId;

    private int selectedSubId;


    @FXML
    private void initialize() throws Exception {

        try {
            connect = new Connect();
        } catch (IOException e) {
            e.printStackTrace();
            Notifications error = Notifications.create().text(e.getMessage()).title("unable to connect").position(Pos.BASELINE_LEFT).hideAfter(Duration.hours(1));
            error.showError();
        }

        loadCatList();
        loadSubCatList();

        catList.setItems(categoryObservable);
        catList.setCellFactory(param -> new ListCell<CategoryClass>(){
            @Override
            protected void updateItem(CategoryClass categoryClass, boolean b) {
                super.updateItem(categoryClass, b);
                if (b || categoryClass == null || categoryClass.getCatName() == null) {
                    setText(null);
                } else {
                    setText(categoryClass.getCatName());
                }
            }
        });

        catList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        catList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CategoryClass>() {
            @Override
            public void changed(ObservableValue<? extends CategoryClass> observableValue, CategoryClass categoryClass, CategoryClass t1) {
                selectedId = t1.getCatId();
                categoryTF.setText(t1.getCatName());
            }
        });

        subCatList.setItems(subCatObservableList);
        subCatList.setCellFactory(param -> new ListCell<SubCat>(){
            @Override
            protected void updateItem(SubCat subCat, boolean b) {
                super.updateItem(subCat, b);
                if (b || subCat == null || subCat.getSubCatName() == null) {
                    setText(null);
                } else {
                    setText(subCat.getSubCatName()+" :: "+subCat.getCategory().getCatName());
                }
            }
        });

        subCatList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        subCatList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SubCat>() {
            @Override
            public void changed(ObservableValue<? extends SubCat> observableValue, SubCat subCat, SubCat t1) {
                selectedSubId = t1.getSubCatId();
                subCatTF.setText(t1.getSubCatName());
            }
        });
    }

    private void loadCatList() throws Exception {

        ResultSet cats = connect.getCatList();
        while (cats.next()){
            System.out.println("cats.getString(categoryName)"+cats.getString("categoryName"));
            categoryObservable.add(new CategoryClass(cats.getString("categoryName"), cats.getInt("id")));
        }
    }

    private void loadSubCatList() throws Exception {

        ResultSet subCats = connect.getSubCats();
        while (subCats.next()){
            subCatObservableList.add(new SubCat(subCats.getInt("sid"), subCats.getString("subCatName"), new CategoryClass(subCats.getString("categoryName"), subCats.getInt("catId"))));
        }
    }

    @FXML
    void addSub() throws Exception {
        String subCatName = subCatTF.getText();
        if(catList.getSelectionModel().getSelectedItem() != null){
            int subCatId = connect.addSubCat(subCatName, selectedId);
            subCatObservableList.add(new SubCat(subCatId, subCatName, catList.getSelectionModel().getSelectedItem()));
        }else {
            Notifications error = Notifications.create().title("Error").text("Select Category first").hideAfter(Duration.hours(1)).position(Pos.BASELINE_LEFT);
            error.showError();
        }

    }

    @FXML
    void editSub() throws SQLException {
        if(subCatList.getSelectionModel().getSelectedItems().size() > 1){
            Notifications error = Notifications.create().title("Error").text("you can't edit more than row once").hideAfter(Duration.hours(1)).position(Pos.BASELINE_LEFT);
            error.showError();
        }else {
            connect.updateSubCat(subCatTF.getText(), subCatList.getSelectionModel().getSelectedItem().getSubCatId());
            subCatObservableList.set(subCatList.getSelectionModel().getSelectedIndex(), new SubCat(subCatList.getSelectionModel().getSelectedItem().getSubCatId(), subCatTF.getText(), new CategoryClass(subCatList.getSelectionModel().getSelectedItem().getCategory().getCatName(), subCatList.getSelectionModel().getSelectedItem().getCategory().getCatId())));
        }
    }

    @FXML
    void deleteSub() throws SQLException {
        List<SubCat> selectedItems = subCatList.getSelectionModel().getSelectedItems();
        int[] ids = new int[selectedItems.size()];
        int i = 0;
        for (SubCat items: subCatList.getSelectionModel().getSelectedItems()){
            ids[i] = items.getSubCatId();
            i++;

        }
        subCatObservableList.removeAll(selectedItems);
        connect.deleteSubCat(ids);
        try {
            subCatObservableList.clear();
            loadSubCatList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void add() throws Exception {
        String catName = categoryTF.getText();
        int catId = connect.addCat(catName);
        categoryObservable.add(new CategoryClass(catName, catId));
    }

    @FXML
    void edit() throws Exception {

        if(catList.getSelectionModel().getSelectedItems().size() > 1){
            Notifications error = Notifications.create().title("Error").text("you can't edit more than row once").hideAfter(Duration.hours(1)).position(Pos.BASELINE_LEFT);
            error.showError();
        }else {
            connect.updateCat(categoryTF.getText(), selectedId);
            categoryObservable.set(catList.getSelectionModel().getSelectedIndex(), new CategoryClass(categoryTF.getText(), selectedId));
        }
        subCatObservableList.clear();
        loadSubCatList();
    }

    @FXML
    void delete() {

        List<CategoryClass> selectedItems = catList.getSelectionModel().getSelectedItems();
        int[] ids = new int[selectedItems.size()];
        int i = 0;
        for (CategoryClass items: catList.getSelectionModel().getSelectedItems()){
            ids[i] = items.getCatId();
            i++;
        }
        categoryObservable.removeAll(selectedItems);
        try {
            connect.deleteCat(ids);
        }catch (Exception e){
            Notifications.create().title("deletion error").text(e.getMessage()).position(Pos.BOTTOM_RIGHT).showError();
        }


        try {
            subCatObservableList.clear();
            loadSubCatList();
            categoryObservable.clear();
            loadCatList();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    @FXML
    private void goHome() throws IOException {
        Main.changeScene("hello-view.fxml");
    }
}
