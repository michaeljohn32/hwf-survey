package edu.umich.hwf.persistence;

/**
 * Created by mdemerat on 3/4/16.
 */
/**
 * This class holds the Access token generated for an user and  the time stamp the Access
 * token generated. This Object is kept in the Session and passed to the JSP page for accessing
 * in Javascript.
 *
 * With The launch of the LTI tool(POST) Every time  Access token is generated.
 * The Access token life time as google says seems to be 1 hour. But google agrees that they don't
 * always expires the access token in an a hour. So they recommend to watch out for a 401-unauthorised error.
 * More on this https://code.google.com/p/google-oauth-java-client/wiki/OAuth2#FAQ.
 *
 * @author pushyami
 *
 */

public class GoogleAccessToken {

    private String token;
    private long timeTokenCreated;


    public GoogleAccessToken(String token, long currentTimeMillis) {
        this.token=token;
        this.timeTokenCreated=currentTimeMillis;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTimeTokenCreated() {
        return timeTokenCreated;
    }

    public void setTimeTokenCreated(long timeTokenCreated) {
        this.timeTokenCreated = timeTokenCreated;
    }


}