package service;

import entity.User;
import java.util.List;

public interface UserService {

    User find(String id);

    List<User> findAll();

    boolean update(String before, String after);

    boolean delete(String id);

    User create(String displayName, String email, String phoneNumber);
}


/* UserService에는 addUser(User user) 사용자추가 fimdById(String id) 아이디로 사용자를 찾는기능
findALL()등록된 모든 사용자 목록을 보여주는 메뉴 updateUser(User User) 기존 정보를 수정하는기능
 */