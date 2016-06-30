package de.androidbytes.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import de.androidbytes.auth.di.UserComponentHolder;
import de.androidbytes.auth.model.User;
import de.androidbytes.auth.provider.AuthenticationMethod;
import de.androidbytes.auth.provider.AuthenticationProvider;

import java.util.HashMap;



public class FirebaseAuthenticationHelper extends BaseAuthenticationHelper {

	private final FirebaseAuth firebaseAuth;

	public FirebaseAuthenticationHelper(
			final FirebaseAuth firebaseAuth,
			final UserComponentHolder userComponentHolder,
			final HashMap<AuthenticationMethod, AuthenticationProvider> availableAuthenticationProviders
	) {
		super(userComponentHolder, availableAuthenticationProviders);
		this.firebaseAuth = firebaseAuth;

		this.firebaseAuth.addAuthStateListener(new AuthStateListener() {
			@Override
			public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
				final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
				if (firebaseUser != null) {
					User user = new User(firebaseUser);
					getUserComponentHolder().createUserComponent(user);
					if (getStateListener() != null)
						getStateListener().userSignedIn(user);
				} else {
					getUserComponentHolder().releaseUserComponent();
					if (getStateListener() != null)
						getStateListener().authenticationFailed();
				}
			}
		});
	}

	private void credentialSignIn(final AuthCredential credential) {
		firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if (!task.isSuccessful())
					if (getStateListener() != null)
						getStateListener().authenticationFailed();
			}
		});
	}

	@Override
	public void anonymousSignIn() {
		firebaseAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if (!task.isSuccessful())
					if (getStateListener() != null)
						getStateListener().authenticationFailed();
			}
		});
	}

	@Override
	public void signOut() {
		firebaseAuth.signOut();
	}

	@Override
	public void onAccountCredentialsExtracted(final AuthCredential credential) {
		credentialSignIn(credential);
	}
}
