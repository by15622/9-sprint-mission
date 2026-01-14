import entity.Channel;
import entity.User;
import service.ChannelService;
import service.UserService;
import service.jcf.JCFChannelService;
import service.jcf.JCFUserService;
import entity.Message;
import service.MessageService;
import service.jcf.JCFMessageService;

import java.util.List;
import java.util.UUID;


public class JavaApplication {
    static void userCRUDTest(UserService userService) {
        System.out.println("--유저 생성 테스트--");
        // 생성
        User user = userService.create("서현하", "hhh@naver.com", "010-2222-2222");
        User user2 = userService.create("김춘자", "ccc@naver.com", "010-8888-8888");

        // 조회
        User foundUser = userService.find(user.getId().toString());
        System.out.println();
        System.out.println("유저 조회(단건)");
        System.out.println("유저 아이디 : " + foundUser.getId());
        System.out.println("이름 : " + foundUser.getDisplayName());
        System.out.println("이메일 : " + foundUser.getEmail());
        System.out.println("휴대폰 번호 : " + foundUser.getPhoneNumber());
        System.out.println("생성 시간 : " + foundUser.getCreatedAt() + "\n");


        List<User> foundUsers = userService.findAll();
        System.out.println("유저 조회 건수: " + foundUsers.size());
        for(int i = 0; i<foundUsers.size(); i++){
            System.out.println("유저 아이디 : " + foundUsers.get(i).getId());
            System.out.println("이름 : " + foundUsers.get(i).getDisplayName());
            System.out.println("이메일 : " + foundUsers.get(i).getEmail());
            System.out.println("휴대폰 번호 : " + foundUsers.get(i).getPhoneNumber());
            System.out.println("생성 시간 : " + foundUsers.get(i).getCreatedAt() + "\n");

        }// 수정
        String before = "서현하";
        String after = "박춘자";
        userService.update(before, after); // 바꿀유저의 이름이랑 바꿀이름을 넣어줌
        System.out.println();


        // 삭제
        System.out.println(user.getDisplayName()+" 유저를 삭제합니다" +"\n");
        userService.delete(user.getId().toString());
        List<User> foundUsersAfterDelete = userService.findAll();
        System.out.println("유저 조회 건수: " + foundUsersAfterDelete.size());
        for(int i = 0; i<foundUsersAfterDelete.size(); i++){
            System.out.println("유저 아이디 : " + foundUsersAfterDelete.get(i).getId());
            System.out.println("이름 : " + foundUsersAfterDelete.get(i).getDisplayName());
            System.out.println("이메일 : " + foundUsersAfterDelete.get(i).getEmail());
            System.out.println("휴대폰 번호 : " + foundUsersAfterDelete.get(i).getPhoneNumber());
            System.out.println("생성 시간 : " + foundUsersAfterDelete.get(i).getCreatedAt() + "\n");
        }

    }




    static void channelCRUDTest(ChannelService channelService) {
        // 생성
        Channel channel = channelService.create("공지사항 채널");
        System.out.println("채널 생성: " + channel.getId());
        // 조회
        Channel foundChannel = channelService.find(channel.getId());
        System.out.println("채널 조회(단건): " + foundChannel.getId());
        List<Channel> foundChannels = channelService.findAll();
        System.out.println("채널 조회(다건): " + foundChannels.size());
        // 수정
        Channel updatedChannel = channelService.update(channel.getId(), "공지사항");
        System.out.println("채널 수정: " + updatedChannel.getDisplayname());
        // 삭제
        channelService.delete(channel.getId());
        List<Channel> foundChannelsAfterDelete = channelService.findAll();
        System.out.println("채널 삭제: " + foundChannelsAfterDelete.size());
    }

    static void messageCRUDTest(MessageService messageService) {
        // 생성
        UUID channelId = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();
        Message message = messageService.create("안녕하세요.", channelId, authorId);
        System.out.println("메시지 생성: " + message.getId());
        // 조회
        Message foundMessage = messageService.find(message.getId());
        System.out.println("메시지 조회(단건): " + foundMessage.getId());
        List<Message> foundMessages = messageService.findAll();
        System.out.println("메시지 조회(다건): " + foundMessages.size());
        // 수정
        Message updatedMessage = messageService.update(message.getId(), "반갑습니다.");
        System.out.println("메시지 수정: " + updatedMessage.getContent());
        // 삭재
        messageService.delete(message.getId());
        List<Message> foundMessagesAfterDelete = messageService.findAll();
        System.out.println("메시지 삭제: " + foundMessagesAfterDelete.size());
    }

    public static void main(String[] args) {
        // 서비스 초기화
        UserService userService = new JCFUserService();
        ChannelService channelService = new JCFChannelService();
        MessageService messageService = new JCFMessageService();

        // 테스트
        userCRUDTest(userService);
        channelCRUDTest(channelService);
        messageCRUDTest(messageService);
    }
}