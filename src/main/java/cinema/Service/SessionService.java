package cinema.Service;

import cinema.DAL.DAL;
import cinema.Model.Session;
import cinema.Util.FilterUtil;

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
}
