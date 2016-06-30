package de.androidbytes.auth.model;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.firebase.auth.FirebaseUser;



public class User {

	private final String uid;
	private final boolean anonymous;
	private final String displayName;
	private final Uri photoUrl;
	private final String email;

	public User(FirebaseUser firebaseUser) {
		this(
				firebaseUser.getUid(),
		        firebaseUser.isAnonymous(),
		        firebaseUser.getEmail(),
		        firebaseUser.getDisplayName(),
		        firebaseUser.getPhotoUrl()
		);
	}

	private User(
			@NonNull final String uid,
			final boolean anonymous,
			@Nullable final String email,
			@Nullable final String displayName,
			@Nullable final Uri photoUrl
	) {
		this.uid = uid;
		this.anonymous = anonymous;
		this.email = email;
		this.displayName = displayName;
		this.photoUrl = photoUrl;
	}

	@NonNull
	public String getUid() {
		return uid;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	@Nullable
	public String getEmail() {
		return email;
	}

	@Nullable
	public String getDisplayName() {
		return displayName;
	}

	@Nullable
	public Uri getPhotoUrl() {
		return photoUrl;
	}
}
