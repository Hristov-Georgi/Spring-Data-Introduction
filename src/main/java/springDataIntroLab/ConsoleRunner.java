package springDataIntroLab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springDataIntroLab.models.User;
import springDataIntroLab.services.AccountService;
import springDataIntroLab.services.UserService;

@Component
public class ConsoleRunner implements CommandLineRunner {


    private UserService userService;

    public ConsoleRunner(){}

    @Autowired
    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    //private AccountService accountService;


//    public ConsoleRunner (UserService userService, AccountService accountService) {
//        this.userService = userService;
//        this.accountService = accountService;
//    }

    @Override
    public void run(String... args) throws Exception {

        User firstUser = new User("Ivan", 35);
        userService.registerUser(firstUser);

        User secondUser = new User("Ivan", 35);
        userService.registerUser(secondUser);
    }
}
