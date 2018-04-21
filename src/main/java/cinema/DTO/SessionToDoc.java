package cinema.DTO;

public class SessionToDoc {
    public String filmName;
    public String date;
    public String time;
    public String cinemaRoomName;
    public int numberOfSoldTickets;
    public double costOfTickets;

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%d|%f", filmName, date, time, cinemaRoomName, numberOfSoldTickets, costOfTickets);
    }
}
