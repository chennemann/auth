package de.chennemann.libraries.auth.di;

import android.content.Context;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import dagger.Module;
import dagger.Provides;
import de.chennemann.libraries.auth.AuthenticationHelper;
import de.chennemann.libraries.auth.FirebaseAuthenticationHelper;
import de.chennemann.libraries.auth.provider.AuthenticationMethod;
import de.chennemann.libraries.auth.provider.AuthenticationProvider;
import de.chennemann.libraries.auth.provider.google.GoogleAuthenticationProvider;

import javax.inject.Singleton;
import java.util.HashMap;



@Module
public class AuthModule {

	private final Context context;
	private final String googleRequestIdToken;
	private UserComponentHolder userComponentHolder;

	public AuthModule(final Context context, String googleRequestIdToken, UserComponentHolder userComponentHolder) {
		this.context = context;
		this.googleRequestIdToken = googleRequestIdToken;
		this.userComponentHolder = userComponentHolder;
	}

	@Provides
	@Singleton
	GoogleApiClient provideGoogleApiClient() {
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestIdToken(googleRequestIdToken)
				.requestEmail()
				.build();

		return new GoogleApiClient.Builder(context)
				.addApi(Auth.GOOGLE_SIGN_IN_API, gso)
				.build();
	}

	@Provides
	@Singleton
	FirebaseAuth provideFirebaseAuthenticationInstance() {
		return FirebaseAuth.getInstance();
	}

	@Provides
	@Singleton
	UserComponentHolder provideUserComponentHolder() {
		return userComponentHolder;
	}

	@Provides
	@Singleton
	GoogleAuthenticationProvider provideGoogleAuthenticationProvider(GoogleApiClient googleApiClient) {
		return new GoogleAuthenticationProvider(googleApiClient, AuthenticationMethod.Google);
	}

	@Provides
	@Singleton
	AuthenticationHelper provideFirebaseAuthenticationWrapper(FirebaseAuth firebaseAuth, UserComponentHolder userComponentHolder, GoogleAuthenticationProvider googleAuthenticationProvider) {

		HashMap<AuthenticationMethod, AuthenticationProvider> authenticationProviders = new HashMap<>(1);
		authenticationProviders.put(AuthenticationMethod.Google, googleAuthenticationProvider);

		return new FirebaseAuthenticationHelper(firebaseAuth, userComponentHolder, authenticationProviders);
	}

}
