package de.chennemann.libraries.auth;

import android.content.Intent;
import de.chennemann.libraries.auth.di.UserComponentHolder;
import de.chennemann.libraries.auth.provider.AuthenticationMethod;
import de.chennemann.libraries.auth.provider.AuthenticationProvider;
import de.chennemann.libraries.auth.provider.AuthenticationProviderCallback;
import de.chennemann.libraries.auth.provider.google.GoogleAuthenticationProvider;

import java.util.HashMap;



abstract class BaseAuthenticationHelper implements AuthenticationProviderCallback, AuthenticationHelper {

	private final UserComponentHolder userComponentHolder;
	private final HashMap<AuthenticationMethod, AuthenticationProvider> availableAuthenticationProviders;

	private AuthenticationStateListener stateListener;

	BaseAuthenticationHelper(
			final UserComponentHolder userComponentHolder,
			final HashMap<AuthenticationMethod, AuthenticationProvider> availableAuthenticationProviders
	) {
		this.userComponentHolder = userComponentHolder;
		this.availableAuthenticationProviders = availableAuthenticationProviders;

		for (AuthenticationProvider authenticationProvider : availableAuthenticationProviders.values()) {
			authenticationProvider.registerCallback(this);
		}
	}

	@Override
	public final void registerStateListener(final AuthenticationStateListener stateListener) {
		this.stateListener = stateListener;
	}

	@Override
	public final void unregisterStateListener() {
		this.stateListener = null;
	}

	AuthenticationStateListener getStateListener() {
		return stateListener;
	}

	private AuthenticationProvider getAuthenticationProvider(final AuthenticationMethod authenticationMethod) {
		return availableAuthenticationProviders.get(authenticationMethod);
	}

	final UserComponentHolder getUserComponentHolder() {
		return userComponentHolder;
	}

	@Override
	public void googleSignInResult(final Intent data) {
		final GoogleAuthenticationProvider googleAuthenticationProvider = getGoogleAuthenticationProvider();
		googleAuthenticationProvider.extractAccountCredentials(data);
	}

	@Override
	public Intent getGoogleSignInIntent() {
		return getGoogleAuthenticationProvider().getSignInIntent();
	}

	private GoogleAuthenticationProvider getGoogleAuthenticationProvider() {
		final AuthenticationProvider authenticationProvider = getAuthenticationProvider(AuthenticationMethod.Google);
		return ((GoogleAuthenticationProvider) authenticationProvider);
	}

	@Override
	public void onAuthenticationFailed() {
		stateListener.authenticationFailed();
	}
}
