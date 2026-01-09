package service;

import entity.User;

import java.util.List;

public interface UserService {

    //생성
    User addUser();

    //조회
    User getUser(String displayName);

    //전체 조회
    List<User> getAllUser();

    //수정
    User updateUser(String name, String email, String phoneNumber);
     //ex) "" 전체 목록에서 검색 ->user 객체 가져오기 -> user 안에 필드 id를
     //들고와서 삭제(?)
    //삭제
    boolean deleteUser(String displayName);
}
