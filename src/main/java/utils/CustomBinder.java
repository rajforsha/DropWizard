package utils;

import com.google.inject.AbstractModule;

import service.UserService;
import serviceImpl.UserServiceImpl;

public class CustomBinder extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserService.class).to(UserServiceImpl.class);
	}

}
