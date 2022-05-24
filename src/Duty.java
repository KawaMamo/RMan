import java.time.LocalDate;

public class Duty {

    private String description;
    private int id;

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

    public Duty(String description, int id) {
        this.description = description;
        this.id = id;
    }

    @Override
    public String toString(){
        return description;
    }
}
