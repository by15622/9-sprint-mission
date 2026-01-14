package service.jcf;

import entity.User;
import service.UserService;
import java.util.ArrayList;
import java.util.List;

public class JCFUserService implements UserService {
    private final List<User> data = new ArrayList<>();

    @Override
    public User create(String displayName, String email, String phoneNumber) {
        //try안에 있는 오류를 catch가 잡음
        try {
            User user = new User(displayName, email, phoneNumber);
            data.add(user);
            System.out.println("유저 등록 성공");
            return user;

        } catch (Exception e) {
            System.out.println("유저 등록 실패");
             return null;
        }

    }

    @Override
    public User find(String id) {
        return data.stream()
                .filter(user -> user.getId().toString().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public boolean update(String before, String after) {
        for(int i = 0; i<data.size(); i++){

            if (data.get(i).getDisplayName().equals(before)) {

               System.out.println("수정전 이름 : " +data.get(i).getDisplayName());

                data.get(i).setDisplayName(after);

                System.out.println("수정후 이름 : " +data.get(i).getDisplayName());
            }

        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        return data.removeIf(user -> user.getId().toString().equals(id));
    }

}