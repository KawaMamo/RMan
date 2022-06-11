import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import org.controlsfx.control.Notifications;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PreviewToExport {
    @FXML
    private WebView webView;
    Report report;
    String htmlPage;
    public static String caller = null;
    Config config;

    @FXML
    private void initialize(){
        try {
            config = new Config();
        } catch (IOException e) {
            e.printStackTrace();
            Notifications.create().title("error loading config").text(e.getMessage()).showError();
        }

        if(caller == "Filtering"){
            report = Filtering.selectedReport;
        }else if (caller == "ReportsDetails"){
            report = ReportsDetails.selectedReport;
        }

        WebEngine webEngine = webView.getEngine();
        String htmlPartOne[] = report.getReportText().split("<head>");

        String reportHtml[] = htmlPartOne[1].split("</body>");
        htmlPage = "<html dir=\"ltr\"><head><meta charset=\"UTF-8\">"+reportHtml[0]+"<h1>المشاريع</h1><ol>";
        for (Project project: report.getProjects()){
            htmlPage +="<li>"+project.getProjectsText()+"</li>";
        }
        htmlPage += "</ol><h1>الاقتراحات</h1><ol>";
        for (Suggestion suggestion:report.getSuggestions()){
            htmlPage += "<li>"+suggestion.getSuggestionText()+"</li>";
        }

        htmlPage += "</ol><h1>الصور المرفقة</h1><table width=\"100%\">";
        for (UploadedImages images: report.getUploadedImagesList()){
            String[] extension = images.getNewName().split("\\.");
            if(extension[1].equals("pdf")){
                htmlPage += "<tr><td><a href='"+config.getProp().getProperty("imageUrl")+images.getNewName()+"' width='100%'>"+images.getImageName()+"</a></td></tr>";
            }else {
                htmlPage += "<tr><td><img src='"+config.getProp().getProperty("imageUrl")+images.getNewName()+"' width='100%'/></td></tr>";
            }

        }
        htmlPage += "</table>";

        htmlPage +="<h4>reported at "+report.getReportDate()+" by "+report.getCategory().getCatName()+" :: "+report.getSubCat().getSubCatName()+" titled "+report.getTitle()+"</h4></body></html>";
        webEngine.loadContent(htmlPage, "text/html");

        //WebEngine webEngine = webView.getEngine();
        webEngine.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Open URL in Browser:
                String[] extension = newValue.split("\\.");
                if(extension.length>1){
                    if(extension[1].equals("pdf")){
                        try {
                            Desktop.getDesktop().browse(new URI(newValue));
                            //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+newValue);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    webEngine.loadContent(htmlPage);
                }
            }
        });
    }

    @FXML
    private void exportTo(){

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(Main.stagePointer);
        System.out.println(selectedDirectory.getAbsolutePath());

        try {
            FileWriter html = new FileWriter(selectedDirectory+"\\"+report.getId()+".html");
            html.write(htmlPage);
            html.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
