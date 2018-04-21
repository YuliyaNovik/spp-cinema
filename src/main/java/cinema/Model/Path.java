package cinema.Model;

import cinema.Enum.Role;
import org.springframework.web.bind.annotation.RequestMethod;

public class Path {
    public Role role;
    public RequestMethod type;
    public String path;

    public Path(Role role, String path, RequestMethod type) {
        this.path = path;
        this.role = role;
        this.type = type;
    }
}
