package cinema.Service;

import cinema.DAL.DAL;
import cinema.DTO.ShowingToDoc;
import cinema.DTO.TicketToDoc;
import cinema.Model.*;
import cinema.Util.FilterUtil;

import java.util.ArrayList;
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

    public Object[] toDocument(List<Ticket> tickets) {
        List<TicketToDoc> dto = new ArrayList<>();

        for (Ticket ticket: tickets) {
            Seat seat = DAL.getSeatDAL().getById(ticket.seatId);
            Session session = DAL.getSessionDAL().getById(ticket.sessionId);
            Showing showing = DAL.getShowingDAL().getById(session.showingId);
            Movie movie = DAL.getMovieDAL().getById(showing.movieId);

            TicketToDoc ticketToDoc = new TicketToDoc();
            ticketToDoc.cost = ticket.cost;
            ticketToDoc.row = seat.row;
            ticketToDoc.number = seat.number;
            ticketToDoc.date = session.date;
            ticketToDoc.time = session.time;
            ticketToDoc.filmName = movie.name;
            dto.add(ticketToDoc);
        }
        return dto.toArray();
    }
}
