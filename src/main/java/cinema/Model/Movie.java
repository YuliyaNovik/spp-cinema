package cinema.Model;

public class Movie {
    public int id;
    public String name;
    public int year;
    public int duration;
    public String startDate;
    public String endDate;
    public String director;
    public String ageLimit;
    public String description;

    @Override
    public String toString() {
        return String.format("%s|%d|%d|%s|%s", name, year, duration, director, ageLimit);
    }
}
