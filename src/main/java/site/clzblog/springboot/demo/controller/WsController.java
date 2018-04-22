package site.clzblog.springboot.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import site.clzblog.springboot.demo.model.RequestMessage;
import site.clzblog.springboot.demo.model.ResponseMessage;

@Controller
public class WsController {
    @MessageMapping("/hello")
    @SendTo("/topic/helloChannel")
    public ResponseMessage sayHello(RequestMessage requestMessage) {
        System.out.println(requestMessage.getName());
        return new ResponseMessage("Hello," + requestMessage.getName() + " !");
    }
}
