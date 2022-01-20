package pl.coderslab.model;

public class DayName {

    private int id;
    private String dayName;
    private int displayOrder;

    public DayName() {

    }

    public DayName (int id, String dayName, int displayOrder) {

        this.id = id;
        this.dayName = dayName;
        this.displayOrder = displayOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }



    @Override
    public String toString() {
        return "dayName{" +
                "id=" + id +
                ", dayName'" + dayName + '\'' +
                ", displayOrder='" + displayOrder +
                '}';
    }

}
