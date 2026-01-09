import entity.User;
import service.UserService;
import service.jcf.JCFUserService;

public class JavaApplication {
    public static void main(String[] args) {
        UserService userService = new JCFUserService();


        User user = new User("서현하", "hyeonha1103@gmail.com", "010-2222-2222");
        boolean addResult = userService.addUser(user);
        System.out.println("1. 등록 결과 : " + addResult);

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
        System.out.println("7. 삭제 확인 (null이면 성공) : " + deletedUser);
    }
}