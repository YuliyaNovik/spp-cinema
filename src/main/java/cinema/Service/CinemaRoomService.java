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
        String query = filterUtil.generateQuery(filter, "CinemaRoom");
        if (query == null) {
            return null;
        }
        System.out.println(query);
        return DAL.getCinemaRoomDAL().getByQuery(query);
    }

    public boolean createCinemaRoom(CinemaRoom CinemaRoom) {
        return DAL.getCinemaRoomDAL().insert(CinemaRoom);
    }

    public boolean updateCinemaRoom(CinemaRoom CinemaRoom) {
        return DAL.getCinemaRoomDAL().update(CinemaRoom);
    }

    public boolean deleteCinemaRoom(int id) {
        return DAL.getCinemaRoomDAL().deleteById(id);
    }
}
