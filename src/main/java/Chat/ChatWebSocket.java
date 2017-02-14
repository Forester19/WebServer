package Chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

/**
 * Created by Владислав on 13.02.2017.
 */
@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
    private ChatService chatService;
    private Session session;

    public ChatWebSocket(ChatService chatService) {
        this.chatService = chatService;
    }
    @OnWebSocketConnect
    public void onOpen(Session session){
        chatService.add(this);
        this.session =session;
    }
    @OnWebSocketMessage
    public void onMessage(String data){
        chatService.sendMessage(data);
    }
    @OnWebSocketClose
    public void onClose(int statusCode,String reason){
        chatService.remove(this);
    }

    public void sendString(String data){

            try {
                session.getRemote().sendString(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
