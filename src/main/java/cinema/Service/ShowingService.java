package cinema.Service;

import cinema.DAL.DAL;
import cinema.DTO.SessionToDoc;
import cinema.DTO.ShowingToDoc;
import cinema.Model.Movie;
import cinema.Model.Showing;
import cinema.Util.FilterUtil;

import java.util.ArrayList;
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
        String query = filterUtil.generateQuery(filter, "showing_movie");
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

    public Object[] toDocument(List<Showing> showings) {
        List<ShowingToDoc> dto = new ArrayList<>();

        for (Showing showing: showings) {
            Movie movie = DAL.getMovieDAL().getById(showing.movieId);
            ShowingToDoc showingToDoc = new ShowingToDoc();
            showingToDoc.endShowingDate = showing.endShowingDate;
            showingToDoc.startShowingDate = showing.startShowingDate;
            showingToDoc.estimatedCost = showing.estimatedCost;
            showingToDoc.filmName = movie.name;
            dto.add(showingToDoc);
        }
        return dto.toArray();
    }
}
