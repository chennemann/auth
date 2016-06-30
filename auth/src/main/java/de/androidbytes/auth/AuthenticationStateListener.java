package de.androidbytes.auth;

import de.androidbytes.auth.model.User;



public interface AuthenticationStateListener {
	void userSignedIn(User user);
	void userSignedOut();
	void authenticationFailed();
}
