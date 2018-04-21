package cinema.DAL;

import cinema.Helper.SQLQuery;
import cinema.Model.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAL extends BaseDAL {
    private static final int ID_INDEX = 1;
    private static final int SESSION_ID_INDEX = 2;
    private static final int SEAT_ID_INDEX = 3;
    private static final int COST_INDEX = 4;

    public Ticket getById(int id) {
        String query = String.format(SQLQuery.getTicketById, id);
        List<Ticket> tickets = get(query);
        return tickets.size() > 0 ? tickets.get(0) : null;
    }

    public List<Ticket> getAll() {
        return get(SQLQuery.getAllTicket);
    }

    public List<Ticket> getByQuery(String query) {
        return get(query);
    }

    public boolean deleteById(int id) {
        String query = String.format(SQLQuery.deleteTicket, id);
        boolean isDeleted = openUpdateConnection(query);
        closeConnection(null);
        return isDeleted;
    }

    public boolean update(Ticket ticket) {
        String query = String.format(
                SQLQuery.updateTicket,
                ticket.sessionId,
                ticket.seatId,
                ticket.cost,
                ticket.id
        );
        boolean isUpdated = openUpdateConnection(query);
        closeConnection(null);
        return isUpdated;
    }

    public boolean insert(Ticket ticket) {
        String query = String.format(
                SQLQuery.insertTicket,
                ticket.sessionId,
                ticket.seatId
        );

        boolean isCreated = openUpdateConnection(query);
        closeConnection(null);
        return isCreated;
    }

    private List<Ticket> get(String query) {
        ResultSet result = openConnection(query);

        List<Ticket> tickets = new ArrayList<>();
        try {
            while (result != null && result.next()) {
                Ticket ticket = new Ticket();
                ticket.id = result.getInt(TicketDAL.ID_INDEX);
                ticket.sessionId = result.getInt(TicketDAL.SESSION_ID_INDEX);
                ticket.seatId = result.getInt(TicketDAL.SEAT_ID_INDEX);
                ticket.cost = result.getFloat(TicketDAL.COST_INDEX);

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(result);
        return tickets;
    }
}
