package cinema.Helper;

import cinema.Enum.Role;
import cinema.Model.Path;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class PathHelper {
    public static List<Path> initPaths() {
        List<Path> paths = new ArrayList<>();
        paths.add(new Path(Role.ANON, "/api/login", RequestMethod.GET));
        paths.add(new Path(Role.ANON, "/api/registration", RequestMethod.POST));

        paths.add(new Path(Role.ACCOUNTANT, "/api/export/pdf", RequestMethod.GET));
        paths.add(new Path(Role.ADMIN, "/api/export/excel", RequestMethod.GET));
        paths.add(new Path(Role.ADMIN, "/api/export/csv", RequestMethod.GET));

        paths.add(new Path(Role.DEFAULT, "/api/order", RequestMethod.POST));

        paths.add(new Path(Role.ACCOUNTANT, "/api/sales", RequestMethod.GET));

        paths.add(new Path(Role.DEFAULT, "/api/cinemaRoom", RequestMethod.GET));
        paths.add(new Path(Role.DEFAULT, "/api/cinemaRoom/all", RequestMethod.GET));
        paths.add(new Path(Role.DEFAULT, "/api/cinemaRoom/filter", RequestMethod.GET));
        paths.add(new Path(Role.ADMIN, "/api/cinemaRoom", RequestMethod.POST));
        paths.add(new Path(Role.ADMIN,  "/api/cinemaRoom", RequestMethod.PUT));
        paths.add(new Path(Role.ADMIN,  "/api/cinemaRoom", RequestMethod.DELETE));

        paths.add(new Path(Role.DEFAULT, "/api/seat", RequestMethod.GET));
        paths.add(new Path(Role.DEFAULT, "/api/seat/all", RequestMethod.GET));
        paths.add(new Path(Role.DEFAULT, "/api/seat/filter", RequestMethod.GET));
        paths.add(new Path(Role.ADMIN, "/api/seat", RequestMethod.POST));
        paths.add(new Path(Role.ADMIN,  "/api/seat", RequestMethod.PUT));
        paths.add(new Path(Role.ADMIN,  "/api/seat", RequestMethod.DELETE));

        paths.add(new Path(Role.ANON, "/api/movie", RequestMethod.GET));
        paths.add(new Path(Role.ANON, "/api/movie/all", RequestMethod.GET));
        paths.add(new Path(Role.ANON, "/api/movie/filter", RequestMethod.GET));
        paths.add(new Path(Role.MOVIE_ADMIN, "/api/movie", RequestMethod.POST));
        paths.add(new Path(Role.MOVIE_ADMIN,  "/api/movie", RequestMethod.PUT));
        paths.add(new Path(Role.MOVIE_ADMIN,  "/api/movie", RequestMethod.DELETE));

        paths.add(new Path(Role.DEFAULT, "/api/session", RequestMethod.GET));
        paths.add(new Path(Role.DEFAULT, "/api/session/all", RequestMethod.GET));
        paths.add(new Path(Role.DEFAULT, "/api/session/filter", RequestMethod.GET));
        paths.add(new Path(Role.MOVIE_ADMIN, "/api/session", RequestMethod.POST));
        paths.add(new Path(Role.MOVIE_ADMIN,  "/api/session", RequestMethod.PUT));
        paths.add(new Path(Role.MOVIE_ADMIN,  "/api/session", RequestMethod.DELETE));

        paths.add(new Path(Role.DEFAULT, "/api/showing", RequestMethod.GET));
        paths.add(new Path(Role.DEFAULT, "/api/showing/all", RequestMethod.GET));
        paths.add(new Path(Role.DEFAULT, "/api/showing/filter", RequestMethod.GET));
        paths.add(new Path(Role.MOVIE_ADMIN, "/api/showing", RequestMethod.POST));
        paths.add(new Path(Role.MOVIE_ADMIN,  "/api/showing", RequestMethod.PUT));
        paths.add(new Path(Role.MOVIE_ADMIN,  "/api/showing", RequestMethod.DELETE));

        paths.add(new Path(Role.ACCOUNTANT, "/api/ticket", RequestMethod.GET));
        paths.add(new Path(Role.ACCOUNTANT, "/api/ticket/all", RequestMethod.GET));
        paths.add(new Path(Role.ACCOUNTANT, "/api/ticket/filter", RequestMethod.GET));
        paths.add(new Path(Role.ACCOUNTANT,  "/api/ticket", RequestMethod.PUT));
        paths.add(new Path(Role.ACCOUNTANT,  "/api/ticket", RequestMethod.DELETE));

        paths.add(new Path(Role.ADMIN, "/api/user", RequestMethod.GET));
        paths.add(new Path(Role.ADMIN, "/api/user/all", RequestMethod.GET));
        paths.add(new Path(Role.ADMIN, "/api/user/filter", RequestMethod.GET));
        paths.add(new Path(Role.ADMIN,  "/api/user", RequestMethod.PUT));
        paths.add(new Path(Role.ADMIN,  "/api/user", RequestMethod.DELETE));
        return paths;
    }
}
