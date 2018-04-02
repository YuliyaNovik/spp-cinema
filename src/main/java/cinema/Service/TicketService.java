package cinema.Service;

import cinema.DAL.DAL;
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

    public boolean createTicket(Ticket Ticket) {
        return DAL.getTicketDAL().insert(Ticket);
    }

    public boolean updateTicket(Ticket Ticket) {
        return DAL.getTicketDAL().update(Ticket);
    }

    public boolean deleteTicket(int id) {
        return DAL.getTicketDAL().deleteById(id);
    }
}
