package de.chennemann.libraries.auth.di;

import dagger.Module;
import dagger.Provides;
import de.chennemann.libraries.auth.model.User;



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
