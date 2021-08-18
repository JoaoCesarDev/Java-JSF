/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj1_maven_jsf.filters;

import com.pj1_maven_jsf.model.users;
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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jcrfm
 */
@WebFilter(filterName = "autenticacaoFilter", urlPatterns = {"/sistema/*"})
public class autenticacaoFilter implements Filter {
   
    public autenticacaoFilter() {
    }    
   
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
       
        
        HttpSession httpSession =((HttpServletRequest)request).getSession();
        
        HttpServletRequest httpservletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
 
		if(httpservletRequest.getRequestURI().indexOf("index.xhtml") <= -1){
 
			users usuarioModel =(users) httpSession.getAttribute("usuarioAutenticado");
 
			if(usuarioModel == null){
 
				httpServletResponse.sendRedirect(httpservletRequest.getContextPath()+ "/index.xhtml");
 
			}
			else{
 
				chain.doFilter(request, response);
			}
		}		
		else{
 
			chain.doFilter(request, response);
		}
        
    }
    

    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
      
    }

    /**
     * Return a String representation of this object.
     */
   
}
