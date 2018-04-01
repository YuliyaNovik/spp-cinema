package cinema.Model;

import java.sql.Date;

public class ShowingMovie {
    public int id;
    public int cinemaId;
    public int movieId;
    public Date startShowingDate;
    public Date endShowingDate;
    public double estimatedCost;
}
