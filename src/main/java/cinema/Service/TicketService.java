package cinema.Service;

import cinema.DAL.DAL;
import cinema.Model.Seat;
import cinema.Model.Session;
import cinema.Model.Ticket;
import cinema.Util.FilterUtil;

import java.util.List;

public class TicketService {
    public Ticket getTicket(int id) {
        return DAL.getTicketDAL().getById(id);
    }

    public List<Ticket> getAllTicket() {
        return DAL.getTicketDAL().getAll();
    }

    public List<Ticket> getTicketByFilter(String filter) {
        FilterUtil filterUtil = new FilterUtil();
        String query = filterUtil.generateQuery(filter, "Ticket");
        if (query == null) {
            return null;
        }
        System.out.println(query);
        return DAL.getTicketDAL().getByQuery(query);
    }

    public boolean createTicket(Ticket ticket) {
        return DAL.getTicketDAL().insert(ticket);
    }

    public boolean updateTicket(Ticket ticket) {
        return DAL.getTicketDAL().update(ticket);
    }

    public boolean deleteTicket(int id) {
        return DAL.getTicketDAL().deleteById(id);
    }
}
