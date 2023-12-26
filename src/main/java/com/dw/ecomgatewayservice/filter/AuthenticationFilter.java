package com.dw.ecomgatewayservice.filter;

import com.dw.ecomgatewayservice.exception.JwtAuthenticationException;
import com.dw.ecomgatewayservice.exception.UnauthorizedException;
import com.dw.ecomgatewayservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Value("${security.service.url}")
    private String SECURITY_SERVICE_URL;

    @Autowired
    private RouteValidator routeValidator;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new UnauthorizedException("Unauthorized, missing authorization header.");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try {
                    if (!jwtUtil.validateToken(authHeader)) {
                        System.out.println("Unauthorized Access, Invalid token");
                        throw new UnauthorizedException("Unauthorized Access, Invalid token");
                    }
                    // Additional authentication logic
                } catch (UnauthorizedException ex) {
                    ex.printStackTrace();
                    throw ex;  // Re-throw custom UnauthorizedException
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new JwtAuthenticationException("Unauthorized Access", ex);
                }
            }
            return chain.filter(exchange);
        };
    }


    public static class Config {
    }
}
