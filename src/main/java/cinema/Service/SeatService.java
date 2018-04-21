package cinema.Service;

import cinema.DAL.DAL;
import cinema.Model.Seat;
import cinema.Util.FilterUtil;

import java.util.List;

public class SeatService {
    public Seat getSeat(int id) {
        return DAL.getSeatDAL().getById(id);
    }

    public List<Seat> getAllSeat() {
        return DAL.getSeatDAL().getAll();
    }

    public List<Seat> getSeatByFilter(String filter) {
        FilterUtil filterUtil = new FilterUtil();
        String query = filterUtil.generateQuery(filter, "seat");
        if (query == null) {
            return null;
        }
        System.out.println(query);
        return DAL.getSeatDAL().getByQuery(query);
    }

    public boolean createSeat(Seat seat) {
        return DAL.getSeatDAL().insert(seat);
    }

    public boolean updateSeat(Seat seat) {
        return DAL.getSeatDAL().update(seat);
    }

    public boolean deleteSeat(int id) {
        return DAL.getSeatDAL().deleteById(id);
    }
}
