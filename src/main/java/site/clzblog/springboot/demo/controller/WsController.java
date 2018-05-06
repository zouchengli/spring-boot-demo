package site.clzblog.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.clzblog.springboot.demo.model.RequestMessage;
import site.clzblog.springboot.demo.model.ResponseMessage;
import site.clzblog.springboot.demo.service.AsyncTaskService;
import site.clzblog.springboot.demo.task.impl.WebSocketTaskImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WsController {
    private List<WebSocketTaskImpl> taskList = new ArrayList<>(5);

    @Autowired
    private AsyncTaskService asyncTaskService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private static int taskCount;

    @MessageMapping("/hello")
    @SendTo("/topic/helloChannel")
    public ResponseMessage sayHello(RequestMessage requestMessage) {
        System.out.println(requestMessage.getName());
        return new ResponseMessage("Hello," + requestMessage.getName() + " !");
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @SubscribeMapping("/initial")
    public List<WebSocketTaskImpl> initial() {
        return taskList;
    }

    @PostMapping("/executeTask")
    @ResponseBody
    public void executeTask() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Start execute task" + (i + 1));
            WebSocketTaskImpl task = new WebSocketTaskImpl("Task-" + taskCount, simpMessagingTemplate);
            taskCount += 1;
            taskList.add(task);
            asyncTaskService.doTask(task);
            System.out.println("End execute task" + (i + 1));
        }
    }
}
