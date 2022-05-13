import java.time.LocalDate;

public class Report {

    private int id;
    private LocalDate reportDate;
    private CategoryClass category;
    private SubCat subCat;
    private String reportText;
    private LocalDate createdAt;
    private Suggestion[] suggestions;
    private Project[] projects;

    public Report(int id, LocalDate reportDate, CategoryClass category, SubCat subCat, String reportText, LocalDate createdAt) {
        this.id = id;
        this.reportDate = reportDate;
        this.category = category;
        this.subCat = subCat;
        this.reportText = reportText;
        this.createdAt = createdAt;

    }

    public Suggestion[] getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Suggestion[] suggestions) {
        this.suggestions = suggestions;
    }

    public Project[] getProjects() {
        return projects;
    }

    public void setProjects(Project[] projects) {
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public CategoryClass getCategory() {
        return category;
    }

    public void setCategory(CategoryClass category) {
        this.category = category;
    }

    public SubCat getSubCat() {
        return subCat;
    }

    public void setSubCat(SubCat subCat) {
        this.subCat = subCat;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
