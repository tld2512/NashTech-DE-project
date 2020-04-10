package com.longdt.finalproject.filter;

import com.longdt.finalproject.dao.DBConnection;
import com.longdt.finalproject.log.MyLogger;
import com.longdt.finalproject.service.ConnectionService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

@WebFilter(filterName = "jdbcFilter", urlPatterns = {"/*"})
public class JDBCFilter implements Filter {
    private static final Logger logger = MyLogger.getLogger();

    public JDBCFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (this.needJDBC(req)) {
//            System.out.println("Open Connection for: " + req.getServletPath());
            logger.info("Open JDBC connection for " + req.getServletPath());
            Connection conn = null;
            try {
                conn = DBConnection.getConnection();
                conn.setAutoCommit(false);
                ConnectionService.storeConnection(request, conn);
                filterChain.doFilter(request, response);
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                ConnectionService.rollbackQuietly(conn);
                logger.severe(e.getMessage());
                throw new ServletException();
            } finally {
                ConnectionService.closeQuietly(conn);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    private boolean needJDBC(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String urlPattern = servletPath;

        if (pathInfo != null) {
            urlPattern = servletPath + "/*";
        }
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext().getServletRegistrations();

        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;

    }
}
