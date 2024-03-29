package services;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class FilterService implements ContainerResponseFilter,ContainerRequestFilter {
		@Override
		public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
			if (request.getHeaderString("Origin") == null) {
	            return;
	        }

	        // If it is a preflight request, then we add all
	        // the CORS headers here.
	        if (isPreflightRequest(request)) {
	            response.getHeaders().add("Access-Control-Allow-Credentials", "true");
	            response.getHeaders().add("Access-Control-Allow-Methods",
	                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	            response.getHeaders().add("Access-Control-Allow-Headers",
	                // Whatever other non-standard/safe headers (see list above) 
	                // you want the client to be able to send to the server,
	                // put it in this list. And remove the ones you don't want.
	                "X-Requested-With, Authorization, " +
	                "Accept-Version, Content-MD5, CSRF-Token, Content-Type");
	        }

	        // Cross origin requests can be either simple requests
	        // or preflight request. We need to add this header
	        // to both type of requests. Only preflight requests
	        // need the previously added headers.
	        response.getHeaders().add("Access-Control-Allow-Origin", "*");
	    }
			
		@Override
		public void filter(ContainerRequestContext request) throws IOException {
			// TODO Auto-generated method stub
			if (isPreflightRequest(request)) {
	            request.abortWith(Response.ok().build());
	            return;
	        }
		}
		private static boolean isPreflightRequest(ContainerRequestContext request) {
	        return request.getHeaderString("Origin") != null
	                && request.getMethod().equalsIgnoreCase("OPTIONS");
	    }

}
