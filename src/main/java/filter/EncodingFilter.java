package filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

	private static final String ENCODING = "encoding";
	String encoding;
	public EncodingFilter(){}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter(ENCODING);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(encoding != null) {
			if(!encoding.equalsIgnoreCase(request.getCharacterEncoding())){
				request.setCharacterEncoding(encoding);
			}

			if(!encoding.equalsIgnoreCase(response.getCharacterEncoding())){
				response.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}
