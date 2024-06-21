package io.spring.websocket.chat.controller;

import io.spring.websocket.member.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
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


    @Operation(summary = "로그인 화면")
    @GetMapping
    public String getSignInPage() {
        return "signin";
    }


    @Operation(summary = "채팅방 입장")
    @GetMapping("/chat")
    public String getIndexPage() {
        return "index";
    }


    @OnOpen
    public void open(Session newUser, HttpSession session) {
        Member member1 = (Member) session.getAttribute("member");
        System.out.println("member1 = " + member1);
        Member member = (Member) newUser.getUserPrincipal();
        log.info("{}님이 입장했습니다.", member.getNickname());
        sessions.add(newUser);
    }


    @OnMessage
    public void sendMessage(Session recieveSession, String msg) {
        for (int i = 0; i < sessions.size(); i++) {
            if (!recieveSession.getId().equals(sessions.get(i).getId())) {
                try {
                    sessions.get(i).getBasicRemote().sendText("상대 : "+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    sessions.get(i).getBasicRemote().sendText("나 : "+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @OnClose
    public void close(Session user) {
        Member member = (Member) user.getUserPrincipal();
        log.info("{}님이 퇴장했습니다.", member.getNickname());
        sessions.remove(user);
    }

}
