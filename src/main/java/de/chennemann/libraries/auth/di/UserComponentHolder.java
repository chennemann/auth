package de.chennemann.libraries.auth.di;

import de.chennemann.libraries.auth.model.User;



public interface UserComponentHolder<C> {

	void createUserComponent(User user);
	void releaseUserComponent();
	C getUserComponent();

}
