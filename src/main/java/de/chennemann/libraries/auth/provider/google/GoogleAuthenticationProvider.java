package de.chennemann.libraries.auth.provider.google;

import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.GoogleAuthProvider;
import de.chennemann.libraries.auth.provider.AuthenticationMethod;
import de.chennemann.libraries.auth.provider.BaseAuthenticationProvider;



public class GoogleAuthenticationProvider extends BaseAuthenticationProvider {

	private final GoogleApiClient googleApiClient;

	private final AuthenticationMethod authenticationMethod;

	public GoogleAuthenticationProvider(final GoogleApiClient googleApiClient, AuthenticationMethod authenticationMethod) {
		this.googleApiClient = googleApiClient;
		this.authenticationMethod = authenticationMethod;
	}

	public AuthenticationMethod getAuthenticationMethod() {
		return authenticationMethod;
	}

	public Intent getSignInIntent() {
		return Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
	}

	public void extractAccountCredentials(Intent data) {
		GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
		if (result.isSuccess()) {
			final GoogleSignInAccount account = result.getSignInAccount();
			if (account != null)
				getCallback().onAccountCredentialsExtracted(GoogleAuthProvider.getCredential(account.getIdToken(), null));
		} else
			getCallback().onAuthenticationFailed();
	}
}
