package cinema.Service;

import cinema.DAL.DAL;
import cinema.Model.*;

import java.util.ArrayList;
import java.util.List;

public class SalesReportService {
    public Report getReport(int id) {
        Session session = DAL.getSessionDAL().getById(id);
        Showing showing = DAL.getShowingDAL().getById(session.showingId);
        Movie movie = DAL.getMovieDAL().getById(showing.movieId);
        List<Ticket> tickets = DAL.getTicketDAL().getAll();
        double costOfTickets = 0;
        for (Ticket ticket : tickets) {
            if (ticket.sessionId == session.id) {
                costOfTickets += ticket.cost;
            }
        }

        Report report = new Report();
        report.filmName = movie.name;
        report.date = session.date;
        report.time = session.time;
        report.numberOfSoldTickets = session.numberOfSoldTickets;
        report.costOfTickets = costOfTickets;
        return report;
    }

    public Object[] toDocument() {
        List<Report> dto = new ArrayList<>();
        List<Session> sessions = DAL.getSessionDAL().getAll();
        for (Session session: sessions) {
            Report report = this.getReport(session.id);
            dto.add(report);
        }

        return dto.toArray();
    }
}
