package service.jcf;

import entity.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

//구현체 생성
public class JCFUserService implements UserService {
    private final List<User> data = new ArrayList<>();
    //User만 담을 수 있는 전용 창고느낌,  관리용 list data에 저장하는 시스템 구축
    //ArrayList 데이터를 쌓아두기만 한상태 중복없이 구분하려면 고유id필요

    @Override
    //create(메서드) 저장,만들다
    // 매개변수(이름,이메일,폰번호)=> 사용자 생성을 위한 기초데이터 객체 User /캡슐화
    public User create(String displayName, String email, String phoneNumber) {

        try {
            User user = new User(displayName, email, phoneNumber);
            data.add(user);
            System.out.println("유저 등록 성공");
            return user;

        } catch (Exception e) {
            System.out.println("유저 등록 실패");
            return null;
        }
        // 리스트에 넣는도중 메모리가 찼거나 예상못한 에러가 나타날 수 있기때문에 try catch를 사용함(보호,위험대비)

    }
    // : 오른쪽꺼 하나씩 꺼내기, get가져오다 set설정하다
    // find(Long id)로 할때 받아온 글자를 숫자로 바꾸는 과정을 거쳐야하는데
    //이때 숫자가 아닌 문자가 들어오면 프로그램이 멈출 수 있음 안전하게받고 내부처리
    //
    @Override
    public User find(String id) {
        for(User user : data) {
            if (user.getId().toString().equals(id)){
                return user;
            }
        }
              return null;
    }

   //findAll -> 모든 데이터를 통째로 보여줌
   //return data(원본) new ArrayList<>(data)(복사본) 원본을 보호해야한다는 생각으로
    @Override
    public List<User> findAll() {return new ArrayList<>(data);}

    //바꿀려는 대상먼저before
    @Override
    public void update(String displayName, String email, String phoneNumber) {
         for (User user : data) {
             if (user.getDisplayName().equals(displayName)) {
                 user.update(displayName, email, phoneNumber);
             }
         }
     }

     public boolean delete(String id) {
         return true;

    }
}


