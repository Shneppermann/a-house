
package com.epam.auction.controller.filter;

import com.epam.auction.resource.Info;
import com.epam.auction.entity.User;
import com.epam.auction.resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Authorization filter
 */

@WebFilter("/jsp/*")
public class AuthorizedFilter implements Filter {
    private FilterConfig filterConfig;

    private static final int BANNED_ROLE_ID = 3;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * Checks authorization of the user
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean authorized = false;

        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute(Info.ATTRIBUTE_USER);
            if (user != null)
                authorized = banCheck(user, session);
        }

        if (authorized) {
            filterChain.doFilter(request, response);

        } else if (filterConfig != null) {

            String loginPage = ConfigurationManager.getProperty(Info.LOGIN_PAGE);
            response.sendRedirect(loginPage);
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    /**
     * Checks user role. If user is banned, sets message for him
     *
     * @param user    entity of the user
     * @param session user session
     * @return result of check
     */
    private boolean banCheck(User user, HttpSession session) {
        int userRoleId = user.getIdRole();
        boolean isNotBannedUser = false;
        if (userRoleId != BANNED_ROLE_ID) {
            isNotBannedUser = true;
        } else {
            String banMessage = getLocalBanMessage(session);
            session.setAttribute(Info.ATTRIBUTE_BAN, banMessage);
        }
        return isNotBannedUser;
    }

    /**
     * Returns a localized message
     *
     * @param session user session
     * @return message
     */
    private String getLocalBanMessage(HttpSession session) {

        String local = (String) session.getAttribute(Info.LOCAL);

        Locale locale = new Locale(local);
        ResourceBundle bundle = ResourceBundle.getBundle(Info.MESS_BUNDLE, locale);

        return bundle.getString(Info.MESS_BANNED);
    }
}

