/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holdings.pangaea.subscriber.subscriber;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.json.*;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author timot
 */
@RestController
public class Subscriber {

    /**
     *
     * @param data
     * @return
     */
    @PostMapping("/test1")
    @ResponseBody
    public RestResponse publish(@RequestBody String data) {
        String message = "Received ".concat(" data ").concat(data).concat(" in test1 ");
        System.out.println(message);

        return new RestResponse(message, 0);
    }
    
    /**
     *
     * @param data
     * @return
     */
    @PostMapping("/test2")
    @ResponseBody
    public RestResponse publish2(@RequestBody String data) {
        String message = "Received ".concat(" data ").concat(data).concat(" in test2 ");
        System.out.println(message);

        return new RestResponse(message, 0);
    }

}
