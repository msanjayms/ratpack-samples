package com.wibmo.mylearning.ratpack.guice;

import java.net.URI;

import ratpack.http.client.ReceivedResponse;
import ratpack.test.ApplicationUnderTest;
import ratpack.test.embed.EmbeddedApp;

public class EmbededAppTest {
	public static void main(String[] args) throws Exception {
		
		EmbeddedApp.fromHandler(ctx -> {
			String message = "hello!";
			ctx.byContent(m -> m
					.json(() -> ctx.render("{\"msg\": \"" + message + "\"}"))
					.html(() -> ctx.render("<p>" + message + "</p>"))
					);
		}).test(httpClient -> {
			ReceivedResponse response = httpClient.requestSpec(s -> s.getHeaders().add("Accept", "application/json"))
					.get();
			System.out.println(response.getBody().getText());
			System.out.println(response.getBody().getContentType().getType());
			// assertEquals("{\"msg\": \"hello!\"}", response.getBody().getText());
			// assertEquals("application/json", type);

			response = httpClient.requestSpec(
					s -> s.getHeaders().add("Accept", "text/plain; q=1.0, text/html; q=0.8, application/json; q=0.7"))
					.get();
			System.out.println(response.getBody().getText());
			System.out.println(response.getBody().getContentType().getType());
/*			assertEquals("<p>hello!</p>", response.getBody().getText());
			assertEquals("text/html", response.getBody().getContentType().getType());
*/		});
	}
}