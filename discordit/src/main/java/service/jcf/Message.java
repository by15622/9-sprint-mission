package service.jcf;

public class Message {
    private String content;   // 메시지 내용
    private String senderId;  // 보낸 사람의 ID

    public Message(String content, String senderId) {
        this.content = content;
        this.senderId = senderId;
    }

    public String getSenderId() { return senderId; }
}

