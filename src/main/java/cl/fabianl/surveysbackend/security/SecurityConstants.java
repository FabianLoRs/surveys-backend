package cl.fabianl.surveysbackend.security;

import cl.fabianl.surveysbackend.SpringApplicationContext;
import cl.fabianl.surveysbackend.services.AppProperties;

public class SecurityConstants {
    public static final long EXPIRATION_DATE = 2592000000L; // 30 dias
    public static final String LOGIN_URL = "/users/login";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");

        return appProperties.getTokenSecret();
    }
}
