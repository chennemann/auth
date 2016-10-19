package de.chennemann.libraries.auth;

import android.content.Intent;



public interface AuthenticationHelper {

	void anonymousSignIn();
	void signOut();
	void registerStateListener(AuthenticationStateListener stateListener);
	void unregisterStateListener();

//	GoogleAuthenticationProvider specific methods
	void googleSignInResult(Intent data);
	Intent getGoogleSignInIntent();
}
