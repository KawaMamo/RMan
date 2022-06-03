import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PreviewToExport {
    @FXML
    private WebView webView;
    Report report;
    String htmlPage;

    @FXML
    private void initialize(){

        report = Filtering.selectedReport;
        WebEngine webEngine = webView.getEngine();
        String reportHtml[] = report.getReportText().split("</body>");
        htmlPage = reportHtml[0]+"<h1>Projects</h1><ol>";
        for (Project project: report.getProjects()){
            htmlPage +="<li>"+project.getProjectsText()+"</li>";
        }
        htmlPage += "</ol><h1>suggestions</h1><ol>";
        for (Suggestion suggestion:report.getSuggestions()){
            htmlPage += "<li>"+suggestion.getSuggestionText()+"</li>";
        }
        htmlPage +="</ol><h4>reported at "+report.getReportDate()+"</h4></body></html>";
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
