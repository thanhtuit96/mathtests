package tdt.it.mathtests.models;

import java.io.Serializable;

/**
 * Created by Tu Nguyen on 2018-03-25.
 */

public class UserTokenState implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8510796447293061074L;
	private String access_token;
    private Long expires_in;

    public UserTokenState() {
        this.access_token = null;
        this.expires_in = null;
    }

    public UserTokenState(String access_token, long expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }
}