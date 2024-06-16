package io.spring.websocket.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Chat", description = "채팅")
@Slf4j
@Controller
@ServerEndpoint("/websocket")
public class ChatController extends Socket {

    private static final List<Session> sessions = new ArrayList<>();


    @Operation(summary = "채팅방 입장")
    @GetMapping("/chatroom")
    public String getIndexPage() {
        return "index";
    }


    @OnOpen
    public void open(Session newUser) {
        System.out.println("connected");
        sessions.add(newUser);
        System.out.println(newUser.getId());
    }


    @OnMessage
    public void getMsg(Session recieveSession, String msg) {
        for (int i = 0; i < sessions.size(); i++) {
            if (!recieveSession.getId().equals(sessions.get(i).getId())) {
                try {
                    sessions.get(i).getBasicRemote().sendText("상대 : "+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    sessions.get(i).getBasicRemote().sendText("나 : "+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
