package de.androidbytes.auth.provider;

public interface AuthenticationProvider {
	void registerCallback(AuthenticationProviderCallback authenticationProviderCallback);
}
