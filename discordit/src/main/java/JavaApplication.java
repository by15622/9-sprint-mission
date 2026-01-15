import entity.Channel;
import entity.User;
import service.ChannelService;
import service.UserService;
import service.jcf.JCFChannelService;
import service.jcf.JCFUserService2;
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

        // 2.User객체 user에다가 userService.create 메서드의 반환값으로 받은 유저객체를 담는다


        // 조회
        User foundUser = userService.find(user.getId().toString());
        System.out.println();
        System.out.println("유저 조회(단건)");
        System.out.println("유저 아이디 : " + foundUser.getId());
        System.out.println("이름 : " + foundUser.getDisplayName());
        System.out.println("이메일 : " + foundUser.getEmail());
        System.out.println("휴대폰 번호 : " + foundUser.getPhoneNumber());
        System.out.println("생성 시간 : " + foundUser.getCreatedAt() + "\n");

        /*3.userService.find 메서드에 위에서 가져온 user객체의 id를 매개변수로 입력한다

        5. 반환값으로 가져온 유저객체를 foundUser라는 변수명인 유저변수에 담는다
        그리고 foundUser가 가지고있는 id와 이름,이메일,전화번호,생성시간을 출력한다
        */
        List<User> foundUsers = userService.findAll();
        System.out.println("유저 조회 건수: " + foundUsers.size());
        for(int i = 0; i<foundUsers.size(); i++){
            System.out.println("유저 아이디 : " + foundUsers.get(i).getId());
            System.out.println("이름 : " + foundUsers.get(i).getDisplayName());
            System.out.println("이메일 : " + foundUsers.get(i).getEmail());
            System.out.println("휴대폰 번호 : " + foundUsers.get(i).getPhoneNumber());
            System.out.println("생성 시간 : " + foundUsers.get(i).getCreatedAt() + "\n");

            /* 7. data list를 foundUsers라는 유저객체를 담을 수 있는 list에 담는다
            foundUsers list의 크기를 출력한다(유저 조회건수)
            for문으로 foundUsers의 size만큼 반복문을 수행한다
            foundUsers의 i번째에 들어있는 user객체의 값을 출력한다
             */
        }// 수정
        String before = "서현하";   //8수정전이름
        String after = "박춘자";    //수정후이름
        userService.update(before, after); // 수정할 유저의 이름이랑 새이름 값을 넣어줌
        System.out.println();


        /*13 userService.delete메소드에 위에서 생성한 user 변수의 id값을 매개변수로 입력한다
         */
        // 삭제
        System.out.println(user.getDisplayName()+" 유저를 삭제합니다" +"\n");
        userService.delete(user.getId().toString());

        /*15 user service에 findall메소드를 통해서 유저삭제이후 list를 새로 반환받는다
        for문 안에서 유저삭제이후 list를 돌리면서 해당 list의 유저가 가지고있는 값을 출력한다
        */
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
        /* 입력값으로 채널을 생성하고 새 채널을 만들어서 channel객체를 담는 리스트에
        넣어서 리턴값으로 채널을 반환해 다시 변수에 넣는다
        */
        System.out.println("-- 채널 생성 테스트 --");
        Channel channel = channelService.create("공지사항 채널");
        Channel channel2 = channelService.create("자유 게시판");
        System.out.println("채널 생성: " + channel.getId());
        System.out.println("채널 생성: " + channel2.getId());
        System.out.println();

        // 조회
        //생성한 채널중 첫번째로 생성한 채널의 객체를 가져와서 출력했다<foundChannel.getId()>
        Channel foundChannel = channelService.find(channel.getId());
        System.out.println("채널 조회(단건): " + foundChannel.getId());
        System.out.println();

        // list<channel> 개체를 담아서 변수이름 foundChannels를 선언하고 findAll메소드를 이용하여
        List<Channel> foundChannels = channelService.findAll();
        System.out.println("채널 조회(다건): " + foundChannels.size());
        // int i를 0으로 선언하고 i가 파운드채널스 리스트의 크기보다 작으면 for문을 이용해서
        // foundChannels의 list값을get(i)
        for (int i = 0; i < foundChannels.size(); i++) {
            System.out.println("채널 아이디 : " + foundChannels.get(i).getId());
            System.out.println("이름 : " + foundChannels.get(i).getDisplayname());
            System.out.println();
        }


        // 수정
        System.out.println("수정 전 이름 : " + channel.getDisplayname());
        channelService.update(channel.getId(), "공지");
        System.out.println("수정 후 이름 : " + channel.getDisplayname());
        System.out.println("수정 시간 : " + channel.getUpdatedAt());

        // 삭제
        //삭제한 후에 남아있는 채널이 담긴 리스트에 삭제하고 싶은 리스트를 없앤 다음에 리스트 가져옴
        channelService.delete(channel.getId());
        List<Channel> foundChannelsAfterDelete = channelService.findAll();
        System.out.println("\n채널 삭제 후 남은 목록");
        System.out.println("채널 삭제: " + foundChannelsAfterDelete.size());


        for (int i = 0; i < foundChannelsAfterDelete.size(); i++) {
            System.out.println("남은 채널 아이디 : " + foundChannelsAfterDelete.get(i).getId());
            System.out.println("남은 이름 : " + foundChannelsAfterDelete.get(i).getDisplayname());
            System.out.println("---------------------------");
        }

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
        // 삭제
        messageService.delete(message.getId());
        List<Message> foundMessagesAfterDelete = messageService.findAll();
        System.out.println("메시지 삭제: " + foundMessagesAfterDelete.size());
    }

    public static void main(String[] args) {
        // 서비스 초기화
        UserService userService = new JCFUserService2();
        ChannelService channelService = new JCFChannelService();
        MessageService messageService = new JCFMessageService();

        // 테스트
        userCRUDTest(userService);
        channelCRUDTest(channelService);
        messageCRUDTest(messageService);
    }
}