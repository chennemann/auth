package de.androidbytes.auth.di;

import dagger.Module;
import dagger.Provides;
import de.androidbytes.auth.model.User;



@Module
public class UserModule {

	private User user;

	public UserModule(final User user) {
		this.user = user;
	}

	@UserScope
	@Provides
	User provideLoggedInUser() {
		return user;
	}
}
