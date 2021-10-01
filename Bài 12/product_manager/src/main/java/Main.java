import com.codegym.model.User;
import com.codegym.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserService us = new UserService();
        User user = us.findById(5);
        System.out.println(user.getName());
    }
}
