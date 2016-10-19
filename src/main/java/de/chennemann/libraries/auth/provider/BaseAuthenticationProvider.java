package de.chennemann.libraries.auth.provider;

import de.chennemann.libraries.auth.provider.exception.CallbackNotRegisteredException;

public abstract class BaseAuthenticationProvider implements AuthenticationProvider {

    private AuthenticationProviderCallback providerCallback;

    protected AuthenticationProviderCallback getCallback() {
        assertCallbackRegistered();
        return providerCallback;
    }

    @Override
    public void registerCallback(AuthenticationProviderCallback providerCallback) {
        this.providerCallback = providerCallback;
    }

    private void assertCallbackRegistered() {
        if (providerCallback == null)
            throw new CallbackNotRegisteredException();
    }

}
