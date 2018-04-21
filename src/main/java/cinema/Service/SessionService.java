package cinema.Service;

import cinema.DAL.DAL;
import cinema.DTO.SessionToDoc;
import cinema.Model.*;
import cinema.Util.FilterUtil;

import java.util.ArrayList;
import java.util.List;

public class SessionService {
    public Session getSession(int id) {
        return DAL.getSessionDAL().getById(id);
    }

    public List<Session> getAllSession() {
        return DAL.getSessionDAL().getAll();
    }

    public List<Session> getSessionByFilter(String filter) {
        FilterUtil filterUtil = new FilterUtil();
        String query = filterUtil.generateQuery(filter, "Session");
        if (query == null) {
            return null;
        }
        System.out.println(query);
        return DAL.getSessionDAL().getByQuery(query);
    }

    public boolean createSession(Session session) {
        return DAL.getSessionDAL().insert(session);
    }

    public boolean updateSession(Session session) {
        return DAL.getSessionDAL().update(session);
    }

    public boolean deleteSession(int id) {
        return DAL.getSessionDAL().deleteById(id);
    }

    public Object[] toDocument(List<Session> sessions) {
        List<SessionToDoc> dto = new ArrayList<>();

        for (Session session : sessions) {
            CinemaRoom cinemaRoom = DAL.getCinemaRoomDAL().getById(session.cinemaRoomId);
            Showing showing = DAL.getShowingDAL().getById(session.showingId);
            Movie movie = DAL.getMovieDAL().getById(showing.movieId);
            List<Ticket> tickets = DAL.getTicketDAL().getAll();
            double costOfTickets = 0;
            for (Ticket ticket : tickets) {
                if (ticket.sessionId == session.id) {
                    costOfTickets += ticket.cost;
                }
            }

            SessionToDoc sessionToDoc = new SessionToDoc();
            sessionToDoc.date = session.date;
            sessionToDoc.time = session.time;
            sessionToDoc.numberOfSoldTickets = session.numberOfSoldTickets;
            sessionToDoc.cinemaRoomName = cinemaRoom.name;
            sessionToDoc.filmName = movie.name;
            sessionToDoc.costOfTickets = costOfTickets;
            dto.add(sessionToDoc);
        }
        return dto.toArray();
    }
}
