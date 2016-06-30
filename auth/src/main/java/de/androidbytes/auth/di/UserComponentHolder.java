package de.androidbytes.auth.di;

import de.androidbytes.auth.model.User;



public interface UserComponentHolder<C> {

	void createUserComponent(User user);
	void releaseUserComponent();
	C getUserComponent();

}
