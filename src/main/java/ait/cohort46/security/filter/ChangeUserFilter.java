package ait.cohort46.security.filter;

import ait.cohort46.accounting.dao.UserRepository;
import ait.cohort46.accounting.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Order(15)
public class ChangeUserFilter implements Filter {
    private final UserRepository repository;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (checkEndpoint(request.getMethod(), request.getServletPath())) {
            User user = repository.findById(request.getUserPrincipal().getName()).get();
            String login = request.getServletPath().split("/")[3];

            if (!login.equals(user.getLogin())) {
                if (HttpMethod.PATCH.matches(request.getMethod())) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                } else {
                    if (!user.getRoles().contains("ADMINISTRATOR")) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN);
                        return;
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }

    private boolean checkEndpoint(String method, String path) {
        return path.matches("/account/user/\\w+") && (HttpMethod.PATCH.matches(method) || HttpMethod.DELETE.matches(method));
    }
}
