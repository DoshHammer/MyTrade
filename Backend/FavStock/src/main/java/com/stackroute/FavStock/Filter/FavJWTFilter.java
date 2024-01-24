package com.stackroute.FavStock.Filter;


import io.jsonwebtoken.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpMethod;
import java.util.*;
import java.io.IOException;

public class FavJWTFilter extends GenericFilter {
    /**
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                 to for further processing
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpreq = (HttpServletRequest) request;
        HttpServletResponse httpres = (HttpServletResponse) response;

        httpres.setHeader("Access-Control-Allow-Origin", "*");
        httpres.setHeader("Access-Control-Allow-Headers", "*");
        httpres.setHeader("Access-Control-Allow-Credentials", "true");
        httpres.setHeader("Access-Control-Allow-Methods","GET, POST, DELETE, OPTIONS");


//        if (httpreq.equals(HttpMethod.OPTIONS.name()))
//        {
//            chain.doFilter((ServletRequest) httpreq, httpres);
//        }
        if (httpreq.getMethod().equals(HttpMethod.OPTIONS.name()))
        {
            chain.doFilter(httpreq, httpres);
        }
        else
        {

            String headerinfo=httpreq.getHeader("Authorization");

            System.out.println(headerinfo);

            if( (headerinfo==null) || (!headerinfo.startsWith("Bearer")))
            {
                throw new ServletException("JWT Token is missing. Pls use JWT Token.");
            }

            String mytoken=headerinfo.substring(7);


            try
            {
                JwtParser jwtparser= Jwts.parser().setSigningKey("secret");

                Jwt jwtobj=	jwtparser.parse(mytoken);
                Claims claimobj=(Claims) jwtobj.getBody();

                System.out.println("User logged in " + claimobj.getSubject());

                HttpSession sess=httpreq.getSession();
                sess.setAttribute("email", claimobj.getSubject());
            }
            catch(SignatureException e)
            {
                throw new ServletException("Invalid siganature / token ");
            }
            catch(MalformedJwtException  e)
            {
                throw new ServletException("Someone illegally modified token");

            }

            chain.doFilter((ServletRequest) httpreq, httpres);
        }
    }

    /**
     *
     */
    @Override
    public void destroy() {
        super.destroy();
    }
}
