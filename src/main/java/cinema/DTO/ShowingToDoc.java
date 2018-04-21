package cinema.DTO;

public class ShowingToDoc {
    public String filmName;
    public String startShowingDate;
    public String endShowingDate;
    public double estimatedCost;

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%f", filmName, startShowingDate, endShowingDate, estimatedCost);
    }
}
