package com.wibmo.mylearning.ratpack;

import org.apache.log4j.Logger;

import com.wibmo.ratpack.protobuf.ProtobufModule;

import ratpack.guice.Guice;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

public class MyApp {

	final static Logger logger = Logger.getLogger(MyApp.class);
	
	public static void main(String[] args) throws Exception {

		logger.error("Starting the process");
		/*
		 * RatpackServer.start(server -> server.handlers(chain -> chain.get(ctx ->
		 * ctx.render("Hello World!")).get(":name", ctx -> ctx.render("Hello " +
		 * ctx.getPathTokens().get("name") + "!"))));
		 */
		RatpackServer.of(server -> server.registry(Guice.registry(b -> {
			b.bind(EmployeeMgr.class);
			b.bind(TempEventHandler.class);
			b.bind(StaticHandler.class);

			ServerConfig configData = b.getServerConfig();
			b.moduleConfig(ProtobufModule.class, 
					configData.get("/protobuf", ProtobufModule.Config.class));
		})).handlers(chain -> {
			
			chain.get("protobuf", TempEventHandler.class);
			chain.get("static", StaticHandler.class);
			// chain.all(TempEventHandler.class);
		})).start();

		/*
		 * RatpackServer.of(s -> s.registry(Guice.registry(b ->
		 * b.module(ServiceModule.class))).handlers(chain -> { chain.get("welcome",
		 * TempEventHandler.class); }));
		 */
	}
}
