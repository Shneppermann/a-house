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
 * Filter for users with the role of admin
 */
@WebFilter("/jsp/customer/*")
public class AdminFilter implements Filter {
    private FilterConfig filterConfig;
    private static final int CUSTOMER_ROLE_ID = 2;

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
        User user = (User) session.getAttribute(Info.ATTRIBUTE_USER);
        int userRole = user.getIdRole();
        boolean isCustomer = (userRole == CUSTOMER_ROLE_ID);
        if (isCustomer) {
            filterChain.doFilter(request, response);
        } else if (filterConfig != null) {
            String page = (String) session.getAttribute(Info.PARAM_REDIRECT_PAGE);
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
