package de.chennemann.libraries.auth.provider;

public interface AuthenticationProvider {
	void registerCallback(AuthenticationProviderCallback authenticationProviderCallback);
}
