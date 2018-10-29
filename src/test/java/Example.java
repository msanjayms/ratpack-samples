import ratpack.handling.ResponseTimer;
import ratpack.test.embed.EmbeddedApp;
import ratpack.http.client.ReceivedResponse;

public class Example {
  public static void main(String... args) throws Exception {
    EmbeddedApp.of(s -> s
      .registryOf(r -> r
        .add(ResponseTimer.decorator())
      )
      .handler(r ->
        ctx -> ctx.render("ok")
      )
    ).test(httpClient -> {
      ReceivedResponse response = httpClient.get();
      System.out.println(response.getHeaders().get("X-Response-Time"));
//      assertNotNull();
    });
  }
}

