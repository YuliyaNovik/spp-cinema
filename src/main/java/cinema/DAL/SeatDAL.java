package cinema.DAL;

import cinema.Helper.SQLQuery;
import cinema.Model.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAL extends BaseDAL {
    private static final int ID_INDEX = 1;
    private static final int CINEMA_ROOM_ID_INDEX = 2;
    private static final int ROW_INDEX = 3;
    private static final int NUMBER_INDEX = 4;
    private static final int COST_COEFFICIENT_INDEX = 5;

    public Seat getById(int id) {
        String query = String.format(SQLQuery.getSeatById, id);
        List<Seat> seats = get(query);
        return seats.size() > 0 ? seats.get(0) : null;
    }

    public List<Seat> getAll() {
        return get(SQLQuery.getAllSeat);
    }

    public List<Seat> getByQuery(String query) {
        return get(query);
    }

    public boolean deleteById(int id) {
        String query = String.format(SQLQuery.deleteSeat, id);
        boolean isDeleted = openUpdateConnection(query);
        closeConnection(null);
        return isDeleted;
    }

    public boolean update(Seat seat) {
        String query = String.format(
                SQLQuery.updateSeat,
                seat.cinemaRoomId,
                seat.row,
                seat.number,
                seat.costCoefficient,
                seat.id
        );
        boolean isUpdated = openUpdateConnection(query);
        closeConnection(null);
        return isUpdated;
    }

    public boolean insert(Seat seat) {
        String query = String.format(
                SQLQuery.insertSeat,
                seat.cinemaRoomId,
                seat.row,
                seat.number,
                seat.costCoefficient
        );

        boolean isCreated = openUpdateConnection(query);
        closeConnection(null);
        return isCreated;
    }

    private List<Seat> get(String query) {
        ResultSet result = openConnection(query);

        List<Seat> seats = new ArrayList<>();
        try {
            while (result != null && result.next()) {
                Seat seat = new Seat();
                seat.id = result.getInt(SeatDAL.ID_INDEX);
                seat.cinemaRoomId = result.getInt(SeatDAL.CINEMA_ROOM_ID_INDEX);
                seat.row = result.getInt(SeatDAL.ROW_INDEX);
                seat.number = result.getInt(SeatDAL.NUMBER_INDEX);
                seat.costCoefficient = result.getFloat(SeatDAL.COST_COEFFICIENT_INDEX);
                seats.add(seat);
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        closeConnection(result);
        return seats;
    }
}
