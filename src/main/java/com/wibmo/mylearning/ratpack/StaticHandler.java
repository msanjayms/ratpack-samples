package com.wibmo.mylearning.ratpack;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.example.tutorial.AddressBookProtos.Person;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MediaType;

@Singleton
public class StaticHandler implements Handler {

	static int counter = 0;
	@Inject
	EmployeeMgr employeeMgr;

	@Override
	public void handle(Context ctx) throws Exception {
		counter++;
		System.out.println(counter);
		String message = "Not sure exactly how long it takes, but I'll get there";
		ctx.render("ALMOST THERE");
	}

	private Person addPerson() {
		Person.Builder person = Person.newBuilder();

		person.setId(123);
		person.setName("Sanjay M S");

		String email = "sanjayms@abc.com";
		if (email.length() > 0) {
			person.setEmail(email);
		}

		String number = "8898855555";

		Person.PhoneNumber.Builder phoneNumber = Person.PhoneNumber.newBuilder().setNumber(number);
		phoneNumber.setType(Person.PhoneType.MOBILE);

		person.addPhones(phoneNumber);

		return person.build();
	}
}