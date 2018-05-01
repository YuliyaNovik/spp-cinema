package cinema.DAL;

import cinema.Helper.SQLQuery;
import cinema.Model.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionDAL extends BaseDAL {
    private static final int ID_INDEX = 1;
    private static final int SHOWING_ID_INDEX = 2;
    private static final int CINEMA_ROOM_ID_INDEX = 3;
    private static final int COEFFICIENT_INDEX = 4;
    private static final int NUMBER_OF_SOLD_TICKETS_INDEX = 5;
    private static final int DATE_INDEX = 6;
    private static final int TIME_INDEX = 7;

    public Session getById(int id) {
        String query = String.format(SQLQuery.getSessionById, id);
        List<Session> sessions = get(query);
        return sessions.size() > 0 ? sessions.get(0) : null;
    }

    public List<Session> getAll() {
        return get(SQLQuery.getAllSession);
    }

    public List<Session> getByQuery(String query) {
        return get(query);
    }

    public boolean deleteById(int id) {
        String query = String.format(SQLQuery.deleteSession, id);
        boolean isDeleted = openUpdateConnection(query);
        closeConnection(null);
        return isDeleted;
    }

    public boolean update(Session session) {
        String query = String.format(
                SQLQuery.updateSession,
                session.showingId,
                session.cinemaRoomId,
                session.date,
                session.time,
                session.coefficient,
                session.numberOfSoldTickets,
                session.id
        );
        boolean isUpdated = openUpdateConnection(query);
        closeConnection(null);
        return isUpdated;
    }

    public boolean insert(Session session) {
        String query = String.format(
                SQLQuery.insertSession,
                session.showingId,
                session.cinemaRoomId,
                session.date,
                session.time,
                session.coefficient,
                session.numberOfSoldTickets
        );

        boolean isCreated = openUpdateConnection(query);
        closeConnection(null);
        return isCreated;
    }

    private List<Session> get(String query) {
        ResultSet result = openConnection(query);

        List<Session> sessions = new ArrayList<>();
        try {
            while (result != null && result.next()) {
                Session session = new Session();
                session.id = result.getInt(SessionDAL.ID_INDEX);
                session.showingId = result.getInt(SessionDAL.SHOWING_ID_INDEX);
                session.cinemaRoomId = result.getInt(SessionDAL.CINEMA_ROOM_ID_INDEX);
                session.date = result.getString(SessionDAL.DATE_INDEX);
                session.time = result.getString(SessionDAL.TIME_INDEX);
                session.coefficient = result.getFloat(SessionDAL.COEFFICIENT_INDEX);
                session.numberOfSoldTickets = result.getInt(SessionDAL.NUMBER_OF_SOLD_TICKETS_INDEX);
                sessions.add(session);
            }
        } catch (NullPointerException | SQLException e) {
            closeConnection(result);
            return sessions;
        }
        closeConnection(result);
        return sessions;
    }
}
