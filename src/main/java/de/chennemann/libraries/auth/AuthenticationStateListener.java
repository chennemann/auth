package de.chennemann.libraries.auth;

import de.chennemann.libraries.auth.model.User;



public interface AuthenticationStateListener {
	void userSignedIn(User user);
	void userSignedOut();
	void authenticationFailed();
}
