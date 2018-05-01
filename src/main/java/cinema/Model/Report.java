package cinema.Model;

public class Report {
    public String filmName;
    public String date;
    public String time;
    public int numberOfSoldTickets;
    public double costOfTickets;

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%d|%.2f", filmName, date, time, numberOfSoldTickets, costOfTickets);
    }
}
