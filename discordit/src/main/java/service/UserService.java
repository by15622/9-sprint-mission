package service;

import entity.User;
import java.util.List;

public interface UserService {
    // 1. 회원 등록 (성공하면 true, 실패하면 false 반환)
    boolean addUser(User user);

    // 2. 단건 조회 (ID로 회원 찾기)
    User findById(String id);

    // 3. 전체 조회 (모든 회원 목록 가져오기)
    List<User> findAll();

    // 4. 정보 수정
    boolean updateUser(User user);

    // 5. 탈퇴 (삭제)
    boolean withdraw(String id);
}