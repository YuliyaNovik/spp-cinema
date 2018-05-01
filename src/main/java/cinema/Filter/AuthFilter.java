package cinema.Filter;

import cinema.Model.Path;
import cinema.Service.Service;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
@Configuration
public class AuthFilter implements Filter {
    private static final String TOKEN_HEADER = "Token";

    @Override
    public void destroy() {
    }

    private void filterOptions(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Token, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        RequestMethod requestType = RequestMethod.GET;
        if (request.getMethod().equalsIgnoreCase("POST")) {
            requestType = RequestMethod.POST;
        } else if (request.getMethod().equalsIgnoreCase("PUT")) {
            requestType = RequestMethod.PUT;
        } else if (request.getMethod().equalsIgnoreCase("DELETE")) {
            requestType = RequestMethod.DELETE;
        } else if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterOptions(response);
            return;
        }

        Path path = Service.getAuthService().getPath(request.getRequestURI(), requestType);
        if (path == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not Found");
            return;
        }

        boolean hasAccess = Service.getAuthService().hasAccess(path, request.getHeader(TOKEN_HEADER));
        if (hasAccess) {
            chain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    @Override
    public void init(FilterConfig arg0) {
    }
}
