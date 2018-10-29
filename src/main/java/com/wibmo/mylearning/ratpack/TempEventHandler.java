package com.wibmo.mylearning.ratpack;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.example.tutorial.AddressBookProtos.Person;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MediaType;

@Singleton
public class TempEventHandler implements Handler {

	@Inject
	EmployeeMgr employeeMgr;

	@Override
	public void handle(Context ctx) throws Exception {
		String message = "Not sure exactly how long it takes, but I'll get there";
/*		ctx.byContent(m -> m.json(() -> ctx.render("{\"msg\": \"" + message + "\"}"))
				.html(() -> ctx.render("<p>" + message + "</p>")));
*/
		MediaType contentType = ctx.getRequest().getContentType();
		if (contentType.getType().equals("application/x-protobuf")) {
			Person person = addPerson();
			System.out.println(person.toByteArray());
			ctx.getResponse().send("application/x-protobuf", person.toByteArray());
//			ctx.render(person);
		}else if(contentType.getType().equals(MediaType.APPLICATION_JSON)) {
			String msg = "name: \"Sanjay M S\"\n" + 
					"id: 123\n" + 
					"email: \"sanjayms@abc.com\"\n" + 
					"phones {\n" + 
					"  number: \"8898855555\"\n" + 
					"  type: MOBILE\n" + 
					"}\n";
			ctx.render(msg);
		}
		// Promise<Message> parse = ctx.parse(Message.class);
		// parse.then(form -> ctx.render(message));
		// ctx.render("ALMOST THERE");
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