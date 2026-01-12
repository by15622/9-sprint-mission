package service.jcf;

import service.UserService;

public class MessageService {
    private final UserService userService;

    public MessageService(UserService userService) {
        this.userService = userService;
    }

    public boolean sendMessage(Message message) {
        if (userService.findById(message.getSenderId()) == null) {
            System.out.println("메시지 전송 실패: ID [" + message.getSenderId() + "] 유저를 찾을 수 없습니다.");
            return false;
        }

        System.out.println("메시지 전송 성공! 보낸 사람 ID: " + message.getSenderId());
        return true;
    }
}