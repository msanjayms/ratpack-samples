package com.wibmo.mylearning.ratpack.guice;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.example.tutorial.AddressBookProtos.Person;

public class Example {
	public static void main(String... args) throws HttpException, IOException{
		
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod("http://localhost:5050/protobuf");
		method.addRequestHeader("Accept", "application/x-protobuf");
		method.addRequestHeader("Content-Type", "application/x-protobuf");
		int executeMethod = client.executeMethod(method);
		
		// set per default
		client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
		  new DefaultHttpMethodRetryHandler());
		
		byte[] responseBody = method.getResponseBody();
		ByteArrayInputStream stream = new ByteArrayInputStream(responseBody);
		Person person = Person.parseFrom(responseBody);
		System.out.println(person);
		method.releaseConnection();
	}
}