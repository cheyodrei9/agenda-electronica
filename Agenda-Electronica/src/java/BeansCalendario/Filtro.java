/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author julio.benavidesusam
 */
public class Filtro implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public Filtro() {
    }
    
    @Override
    public void  init(FilterConfig config) throws ServletException{
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        try {
            HttpServletRequest request1 = (HttpServletRequest) request;
            HttpServletResponse response1 = (HttpServletResponse) response;
            HttpSession session = request1.getSession(false);
            String reqURI = request1.getRequestURI();
            if (reqURI.indexOf("/Login.xhtml") >= 0 || (session != null && session.getAttribute("usename") != null)
                    || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resourse")) {
                chain.doFilter(request, response);                
            }else{
                response1.sendRedirect(request1.getContextPath()+"/faces/login.xhtml");
            }
        } catch (IOException | ServletException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

        
}
