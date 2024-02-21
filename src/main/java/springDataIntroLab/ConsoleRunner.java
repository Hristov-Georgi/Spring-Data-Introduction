package springDataIntroLab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springDataIntroLab.models.Account;
import springDataIntroLab.models.User;
import springDataIntroLab.services.AccountService;
import springDataIntroLab.services.UserService;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;

    private AccountService accountService;

    public ConsoleRunner(){}

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

        User firstUser = new User("Pesho", 26);

        Account account = new Account(new BigDecimal(25000));

        account.setUser(firstUser);
        firstUser.setAccounts(account);

        userService.registerUser(firstUser);
        accountService.registerAccount(account);

        accountService.withdrawMoney(new BigDecimal(20000), 1L);
        accountService.transferMoney(new BigDecimal(50), 1L);
    }
}
