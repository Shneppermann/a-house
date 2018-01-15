package com.epam.auction.controller.filter;

import com.epam.auction.resource.Info;
import com.epam.auction.entity.User;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for users with the role of customer
 */

@WebFilter("/jsp/admin/*")
public class CustomerFilter implements Filter {

    private FilterConfig filterConfig;
    private static final String USER_ATTRIBUTE = "user";
    private static final int ADMIN_ROLE_ID = 1;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        int userRole = user.getIdRole();
        if (userRole == ADMIN_ROLE_ID) {
            filterChain.doFilter(request, response);
        } else if (filterConfig != null) {
            String redirectPage = (String) session.getAttribute(Info.PARAM_REDIRECT_PAGE);
            response.sendRedirect(request.getContextPath() + redirectPage);
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
