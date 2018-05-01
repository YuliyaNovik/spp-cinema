package cinema.DAL;

import cinema.Helper.SQLQuery;
import cinema.Model.CinemaRoom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaRoomDAL extends BaseDAL {
    private static final int ID_INDEX = 1;
    private static final int CINEMA_ID_INDEX = 2;
    private static final int NAME_INDEX = 3;
    private static final int NUMBER_OF_SEATS_INDEX = 4;
    private static final int ROWS_INDEX = 5;
    private static final int NUMBERS_INDEX = 6;

    public CinemaRoom getById(int id) {
        String query = String.format(SQLQuery.getCinemaRoomById, id);
        List<CinemaRoom> cinemaRooms = get(query);
        return cinemaRooms.size() > 0 ? cinemaRooms.get(0) : null;
    }

    public List<CinemaRoom> getAll() {
        return get(SQLQuery.getAllCinemaRoom);
    }

    public List<CinemaRoom> getByQuery(String query) {
        return get(query);
    }

    public boolean deleteById(int id) {
        String query = String.format(SQLQuery.deleteCinemaRoom, id);
        boolean isDeleted = openUpdateConnection(query);
        closeConnection(null);
        return isDeleted;
    }

    public boolean update(CinemaRoom cinemaRoom) {
        String query = String.format(
                SQLQuery.updateCinemaRoom,
                cinemaRoom.cinemaId,
                cinemaRoom.name,
                cinemaRoom.numberOfSeats,
                cinemaRoom.rows,
                cinemaRoom.numbers,
                cinemaRoom.id
        );
        boolean isUpdated = openUpdateConnection(query);
        closeConnection(null);
        return isUpdated;
    }

    public boolean insert(CinemaRoom cinemaRoom) {
        String query = String.format(
                SQLQuery.insertCinemaRoom,
                cinemaRoom.cinemaId,
                cinemaRoom.name,
                cinemaRoom.numberOfSeats,
                cinemaRoom.rows,
                cinemaRoom.numbers
        );

        boolean isCreated = openUpdateConnection(query);
        closeConnection(null);
        return isCreated;
    }

    private List<CinemaRoom> get(String query) {
        ResultSet result = openConnection(query);

        List<CinemaRoom> cinemaRooms = new ArrayList<>();
        try {
            while (result != null && result.next()) {
                CinemaRoom cinemaRoom = new CinemaRoom();
                cinemaRoom.id = result.getInt(CinemaRoomDAL.ID_INDEX);
                cinemaRoom.cinemaId = result.getInt(CinemaRoomDAL.CINEMA_ID_INDEX);
                cinemaRoom.name = result.getString(CinemaRoomDAL.NAME_INDEX);
                cinemaRoom.numberOfSeats = result.getInt(CinemaRoomDAL.NUMBER_OF_SEATS_INDEX);
                cinemaRoom.rows = result.getInt(CinemaRoomDAL.ROWS_INDEX);
                cinemaRoom.numbers = result.getInt(CinemaRoomDAL.NUMBERS_INDEX);

                cinemaRooms.add(cinemaRoom);
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        closeConnection(result);
        return cinemaRooms;
    }
}
