package cinema.Model;

import java.sql.Date;
import java.sql.Time;

public class Session {
    public int id;
    public int showingId;
    public int cinemaRoomId;
    public Date date;
    public Time time;
    public double coefficient;
    public int numberOfSoldTickets;
}
