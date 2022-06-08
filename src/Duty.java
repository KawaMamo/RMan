import java.time.LocalDate;
import java.time.LocalDateTime;

public class Duty {

    private String description;
    private int id;
    private LocalDate dutyTime;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Duty(String description, int id, LocalDate dutyTime) {
        this.description = description;
        this.id = id;
        this.dutyTime = dutyTime;
    }

    public LocalDate getDutyTime() {
        return dutyTime;
    }

    public void setDutyTime(LocalDate dutyTime) {
        this.dutyTime = dutyTime;
    }

    @Override
    public String toString(){
        return description;
    }
}
