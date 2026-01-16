package service;
//interface 뼈대
import entity.User;
import java.util.List;

public interface UserService {

    User find(String id);

    List<User> findAll();

    void update(String displayName, String email, String phoneNumber);

    boolean delete(String id);

    User create(String displayName, String email, String phoneNumber);
}


/* UserService라는  interface안에 메서드 빈상자를 만들고 상자의 이름이랑 입력값만 지정해둔 상태고
이 빈상자를 가지고 JCFUserService로 들고간다
 */