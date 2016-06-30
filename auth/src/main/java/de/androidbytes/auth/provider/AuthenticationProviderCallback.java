package de.androidbytes.auth.provider;

import com.google.firebase.auth.AuthCredential;

public interface AuthenticationProviderCallback {
    void onAuthenticationFailed();
    void onAccountCredentialsExtracted(AuthCredential credential);
}
