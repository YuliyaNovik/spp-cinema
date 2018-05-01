package cinema.Service;

import cinema.DAL.DAL;
import cinema.Model.CinemaRoom;
import cinema.Util.FilterUtil;

import java.util.List;

public class CinemaRoomService {
    public CinemaRoom getCinemaRoom(int id) {
        return DAL.getCinemaRoomDAL().getById(id);
    }

    public List<CinemaRoom> getAllCinemaRoom() {
        return DAL.getCinemaRoomDAL().getAll();
    }

    public List<CinemaRoom> getCinemaRoomByFilter(String filter) {
        FilterUtil filterUtil = new FilterUtil();
        String query = filterUtil.generateQuery(filter, "cinema_room");
        if (query == null) {
            return null;
        }
        System.out.println(query);
        return DAL.getCinemaRoomDAL().getByQuery(query);
    }

    public boolean createCinemaRoom(CinemaRoom cinemaRoom) {
        return DAL.getCinemaRoomDAL().insert(cinemaRoom);
    }

    public boolean updateCinemaRoom(CinemaRoom cinemaRoom) {
        return DAL.getCinemaRoomDAL().update(cinemaRoom);
    }

    public boolean deleteCinemaRoom(int id) {
        return DAL.getCinemaRoomDAL().deleteById(id);
    }
}
