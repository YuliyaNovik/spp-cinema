package cinema.Service;

import cinema.DAL.DAL;
import cinema.Model.Showing;
import cinema.Util.FilterUtil;

import java.util.List;

public class ShowingService {
    public Showing getShowing(int id) {
        return DAL.getShowingDAL().getById(id);
    }

    public List<Showing> getAllShowing() {
        return DAL.getShowingDAL().getAll();
    }

    public List<Showing> getShowingByFilter(String filter) {
        FilterUtil filterUtil = new FilterUtil();
        String query = filterUtil.generateQuery(filter, "Showing");
        if (query == null) {
            return null;
        }
        System.out.println(query);
        return DAL.getShowingDAL().getByQuery(query);
    }

    public boolean createShowing(Showing showing) {
        return DAL.getShowingDAL().insert(showing);
    }

    public boolean updateShowing(Showing showing) {
        return DAL.getShowingDAL().update(showing);
    }

    public boolean deleteShowing(int id) {
        return DAL.getShowingDAL().deleteById(id);
    }
}
