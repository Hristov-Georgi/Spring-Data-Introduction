package springDataIntroLab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springDataIntroLab.repositories.AccountRepository;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {

    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {

    }

    public AccountServiceImpl(){}

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
