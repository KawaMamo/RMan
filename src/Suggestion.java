import java.time.LocalDate;

public class Suggestion {

    private int id;
    private Report report;
    private String suggestionText;
    private LocalDate createdAt;

    public Suggestion(int id, Report report, String suggestionText, LocalDate createdAt) {
        this.id = id;
        this.report = report;
        this.suggestionText = suggestionText;
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

    public String getSuggestionText() {
        return suggestionText;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
