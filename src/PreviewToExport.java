import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        String reportHtml[] = report.getReportText().split("</body>");
        htmlPage = reportHtml[0]+"<h1>المشاريع</h1><ol>";
        for (Project project: report.getProjects()){
            htmlPage +="<li>"+project.getProjectsText()+"</li>";
        }
        htmlPage += "</ol><h1>الاقتراحات</h1><ol>";
        for (Suggestion suggestion:report.getSuggestions()){
            htmlPage += "<li>"+suggestion.getSuggestionText()+"</li>";
        }

        htmlPage += "</ol><h1>الصور المرفقة</h1><table width=\"100%\">";
        for (UploadedImages images: report.getUploadedImagesList()){
            htmlPage += "<tr><td><img src='"+config.getProp().getProperty("imageUrl")+images.getNewName()+"' width='100%'/></td></tr>";
        }
        htmlPage += "</table>";

        htmlPage +="<h4>reported at "+report.getReportDate()+" by "+report.getCategory().getCatName()+" :: "+report.getSubCat().getSubCatName()+" titled "+report.getTitle()+"</h4></body></html>";
        webEngine.loadContent(htmlPage, "text/html");
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
