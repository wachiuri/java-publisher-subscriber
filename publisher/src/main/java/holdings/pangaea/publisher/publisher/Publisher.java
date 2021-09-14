/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holdings.pangaea.publisher.publisher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import okhttp3.*;
import javax.json.*;

/**
 *
 * @author timot
 */
@RestController
public class Publisher {

    private final HashSet<Subscriber> subscribers = new HashSet();

    /**
     *
     * @param topic
     * @param requestBody
     * @return
     */
    @PostMapping("/subscribe/{topic}")
    @ResponseBody
    public Subscriber subscribe(@PathVariable("topic") String topic, @RequestBody SubscribeBody requestBody) {
        Subscriber subscriber = new Subscriber(requestBody.getUrl(), topic);

        subscribers.add(subscriber);

        System.out.println("received subscriber ".concat(subscriber.toString()));
        System.out.println("all subscribers ".concat(subscribers.toString()));
        System.out.println("count of subscribers ".concat("" + subscribers.size()));
        return subscriber;
    }

    @PostMapping("/publish/{topic}")
    @ResponseBody
    public RestResponse publish(@PathVariable("topic") String topic, @RequestBody String requestBody) {

        System.out.println("recevied publish request for topic ".concat(topic).concat(" body ").concat(requestBody));
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        OkHttpClient httpClient = new OkHttpClient();

        for (Subscriber subscriber : subscribers) {
            if (subscriber.getTopic().equals(topic)) {
                System.out.println("publishing to subscriber ".concat(subscriber.toString()));

                JsonObject json = Json.createObjectBuilder()
                        .add("topic", topic)
                        .add("data", requestBody)
                        .build();

                okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json.toString());
                Request request = new Request.Builder()
                        .url(subscriber.getUrl())
                        .post(body)
                        .build();
                try {
                    Response response = httpClient.newCall(request).execute();

                    System.out.println("response ".concat(response.body().toString()));
                } catch (IOException ioe) {
                    System.err.println("IOException");
                    System.err.println(ioe);
                } catch (NullPointerException npe) {
                    System.err.println("NullPointerException");
                    System.err.println(npe);
                }
            }
        }

        return new RestResponse("OK", 0);

    }

}
