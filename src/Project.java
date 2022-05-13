import java.time.LocalDate;

public class Project {

    private int id;
    private Report report;
    private String projectsText;
    private LocalDate createdAt;

    public Project(int id, Report report, String projectsText, LocalDate createdAt) {
        this.id = id;
        this.report = report;
        this.projectsText = projectsText;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getProjectsText() {
        return projectsText;
    }

    public void setProjectsText(String projectsText) {
        this.projectsText = projectsText;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
