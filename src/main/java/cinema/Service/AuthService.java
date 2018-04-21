package cinema.Service;

import cinema.DAL.DAL;
import cinema.Enum.Role;
import cinema.Helper.PathHelper;
import cinema.Helper.RoleHelper;
import cinema.Model.Auth;
import cinema.Model.Path;
import cinema.Model.User;
import cinema.Util.TokenUtil;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class AuthService {
    private List<Path> paths = PathHelper.initPaths();

    public boolean hasAccess(Path path, String token) {
        if (path.role == Role.ANON) {
            return true;
        }
        Auth auth = DAL.getAuthDAL().getUser(token);
        if (auth == null) {
            return false;
        }
        return RoleHelper.getLevel(auth.user.role) >= RoleHelper.getLevel(path.role);
    }

    public void setUser(Auth auth) {
        DAL.getAuthDAL().addUser(auth);
    }

    public Auth createAuthForUser(User user) {
        Auth auth = new Auth();
        auth.token = TokenUtil.generateToken();
        auth.user = user;
        return auth;
    }

    public Path getPath(String path, RequestMethod type) {
        for (Path item: paths) {
            if (item.path.equals(path) && item.type == type) {
                return item;
            }
        }
        return null;
    }
}
