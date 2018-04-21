package cinema.DAL;

import cinema.Helper.SQLQuery;
import cinema.Model.Showing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowingDAL extends BaseDAL {
    private static final int ID_INDEX = 1;
    private static final int MOVIE_ID_INDEX = 1;
    private static final int CINEMA_ID_INDEX = 3;
    private static final int START_SHOWING_DATE_INDEX = 4;
    private static final int END_SHOWING_DATE_INDEX = 5;
    private static final int ESTIMATED_COST_INDEX = 6;

    public Showing getById(int id) {
        String query = String.format(SQLQuery.getShowingById, id);
        List<Showing> showings = get(query);
        return showings.size() > 0 ? showings.get(0) : null;
    }

    public List<Showing> getAll() {
        return get(SQLQuery.getAllShowing);
    }

    public List<Showing> getByQuery(String query) {
        return get(query);
    }

    public boolean deleteById(int id) {
        String query = String.format(SQLQuery.deleteShowing, id);
        boolean isDeleted = openUpdateConnection(query);
        closeConnection(null);
        return isDeleted;
    }

    public boolean update(Showing showing) {
        String query = String.format(
                SQLQuery.updateShowing,
                showing.cinemaId,
                showing.movieId,
                showing.startShowingDate,
                showing.endShowingDate,
                showing.estimatedCost,
                showing.id
        );
        boolean isUpdated = openUpdateConnection(query);
        closeConnection(null);
        return isUpdated;
    }

    public boolean insert(Showing showing) {
        String query = String.format(
                SQLQuery.insertShowing,
                showing.cinemaId,
                showing.movieId,
                showing.startShowingDate,
                showing.endShowingDate,
                showing.estimatedCost
        );

        boolean isCreated = openUpdateConnection(query);
        closeConnection(null);
        return isCreated;
    }

    private List<Showing> get(String query) {
        ResultSet result = openConnection(query);

        List<Showing> showings = new ArrayList<>();
        try {
            while (result != null && result.next()) {
                Showing showing = new Showing();
                showing.id = result.getInt(ShowingDAL.ID_INDEX);
                showing.cinemaId = result.getInt(ShowingDAL.CINEMA_ID_INDEX);
                showing.movieId = result.getInt(ShowingDAL.MOVIE_ID_INDEX);
                showing.startShowingDate = result.getString(ShowingDAL.START_SHOWING_DATE_INDEX);
                showing.endShowingDate = result.getString(ShowingDAL.END_SHOWING_DATE_INDEX);
                showing.estimatedCost = result.getInt(ShowingDAL.ESTIMATED_COST_INDEX);

                showings.add(showing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(result);
        return showings;
    }
}
