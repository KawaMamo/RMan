import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;


public class TaskDashBoard {

    @FXML
    private GridPane calendarGrid;

    @FXML
    private TextField monthTF;

    @FXML
    private TextField yearTF;

    private int dayOfMonth;

    public static int year;
    public static int month;

    public static LocalDate dutyTime;

    static DecimalFormat formatter = new DecimalFormat("00");

    static Connect connect;

    @FXML
    private ListView<Duty> listOfDuties;

    static ObservableList<Duty> observableList = FXCollections.observableArrayList();

    @FXML
    private void initialize(){

        try {
            connect = new Connect();
        } catch (IOException e) {
            e.printStackTrace();
            Notifications.create().title("unable to connect db").text(e.getMessage()).showError();
        }

        year = LocalDate.now().getYear();
        month = LocalDate.now().getMonthValue();

        String formattedYear = formatter.format(year);
        String formattedMonth = formatter.format(month);
        yearTF.setText(formattedYear);
        monthTF.setText(formattedMonth);

        setCalender(year, month);
        setTableConstraint();

        yearTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                year = Integer.valueOf(newValue);
                setCalender(year, month);
            }
        });

        monthTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                month = Integer.valueOf(newValue);
                setCalender(year, month);
            }
        });
        listOfDuties.setItems(observableList);

        listOfDuties.setCellFactory(param -> new ListCell<Duty>(){
            @Override
            protected void updateItem(Duty report, boolean b) {
                super.updateItem(report, b);
                if (b || report == null || report.getDescription() == null) {
                    setText(null);
                } else {
                    setText(report.getDescription());
                    setMaxWidth(param.getWidth());
                    setPrefWidth(param.getWidth());
                    setMinWidth(param.getWidth());
                    setWrapText(true);
                    setStyle("-fx-font-size: 20;-fx-font-weight: bold;");
                }
            }
        });




    }

    @FXML
    private void goHome() throws IOException {
        Main.changeScene("hello-view.fxml");
    }

    public static void setList(int day){
        observableList.clear();
        try {
            ResultSet duties = connect.getDuties(LocalDate.parse(year+"-"+formatter.format(month)+"-"+formatter.format(day)));
            while (duties.next()){
                observableList.add(new Duty(duties.getString("description"), 1, LocalDate.parse(duties.getString("createdAt"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setCalender(int year, int month){

        calendarGrid.getChildren().clear();
        String formattedYear = formatter.format(year);
        String formattedMonth = formatter.format(month);

        LocalDate firstDayOfThisMonth = LocalDate.parse(formattedYear+"-"+formattedMonth+"-"+"01");
        Month thisMonth = firstDayOfThisMonth.getMonth();
        // mon 1(3) tue 2(4) wed 3(5) thur 4(6) fri 5(7) sat 6(1) sun 7(2)
        int startDay = (firstDayOfThisMonth.getDayOfWeek().getValue()+2)%7;
        startDay = (startDay == 0 ? 7 : startDay);
        int lastDay = thisMonth.maxLength();
        dayOfMonth = 1;
        for(int i =0; i< 6; i++){
            int t;
            // in the first row start from start day
            if(i==0){
                t = startDay-1;
            }else {
                t = 0;
            }
            for (int j = t; j<7;j++){
                if(dayOfMonth<=lastDay){
                    DayButton button = new DayButton(String.valueOf(dayOfMonth));
                    button.setMaxWidth(Double.MAX_VALUE);
                    button.setMaxHeight(Double.MAX_VALUE);
                    calendarGrid.add(button, j, i);
                    dayOfMonth++;
                }
            }
        }

    }

    private void setTableConstraint(){
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

    @FXML
    private void plusMonth(){
        month = (month <12 ? ++month : month);
        monthTF.setText(formatter.format(month));
        setCalender(year, month);
    }

    @FXML
    private void minusMonth(){
        month = (month >1 ? --month : month);
        monthTF.setText(formatter.format(month));
        setCalender(year, month);
    }

    @FXML
    private void plusYear(){
        year++;
        yearTF.setText(String.valueOf(year));
        setCalender(year, month);
    }

    @FXML
    private void minusYear(){
        year = (year >1000 ? --year : year);
        yearTF.setText(String.valueOf(year));
        setCalender(year, month);
    }

    public static void setDutyTime(int day){
        dutyTime = LocalDate.parse(year+"-"+formatter.format(month)+"-"+formatter.format(day));
        System.out.println(dutyTime.toString());
    }

}
