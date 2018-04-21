package cinema.DTO;

public class TicketToDoc {
    public String filmName;
    public String date;
    public String time;
    public int row;
    public int number;
    public double cost;

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%d|%d|%f", filmName, date, time, row, number, cost);
    }
}
