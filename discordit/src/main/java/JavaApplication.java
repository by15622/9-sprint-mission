import entity.User;
import service.UserService;
import service.jcf.JCFUserService;
import service.jcf.Message;
import service.jcf.MessageService;
import java.util.Scanner;

public class JavaApplication {
    public static void main(String[] args) {
        UserService userService = new JCFUserService();
        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("==메뉴를 입력하세요==");
            System.out.println("1. 유저 등록");
            System.out.println("2. 유저 조회");
            System.out.println("3. 전체 조회");
            System.out.println("4. 정보 수정");
            System.out.println("5. 수정 조회");
            System.out.println("6. 유저 삭제");
            System.out.println("7. 삭제 결과확인");
            System.out.println("8. 메세지 전송 테스트");

            System.out.print("메뉴 선택 : ");
            int number = scanner.nextInt();



            if (number == 1) {

                System.out.print("이름을 입력하세요 : ");
                String name = scanner.next();

                System.out.print("이메일을 입력하세요 : ");
                String email = scanner.next();

                System.out.print("폰번호를 입력하세요: ");
                String phoneNumber = scanner.next();

                User user = new User(name, email, phoneNumber);
                User user2 = new User("서현하", "hh15963@naver.com", "010-2222-2222");
                boolean addResult = userService.addUser(user);
                System.out.println("1. 등록 결과 : " + addResult);

            }else if (number == 0) break;

        }

        User findUser = userService.findById(user.getId().toString());
        System.out.println("2. 단건 조회 : " + findUser);


        System.out.println("3. 다건 조회");
        userService.findAll().forEach(System.out::println);


        user.update("서현하_수정", "010-7777-7777");
        boolean updateResult = userService.updateUser(user);
        System.out.println("4. 수정 결과 : " + updateResult);


        User updatedUser = userService.findById(user.getId().toString());
        System.out.println("5. 수정된 데이터 조회 : " + updatedUser);


        boolean deleteResult = userService.withdraw(user.getId().toString());
        System.out.println("6. 삭제 결과 : " + deleteResult);


        User deletedUser = userService.findById(user.getId().toString());
        System.out.println("7. 삭제 확인 : " + deletedUser);

        MessageService messageService = new MessageService(userService);
        Message successMsg = new Message("안녕하세요?", user.getId().toString());
        Message failMsg = new Message("실패 메시지", "fake-id-123");
        System.out.println("8. 메시지 전송 테스트(성공) : " + messageService.sendMessage(successMsg));
        System.out.println("9. 메시지 전송 테스트(실패) : " + messageService.sendMessage(failMsg));
    }
}