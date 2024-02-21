package springDataIntroLab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springDataIntroLab.models.Account;
import springDataIntroLab.repositories.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public void registerAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Optional<Account> account = accountRepository.findById(id);

        if(account.isPresent()) {
            Account acc = account.get();

            if (acc.getBalance().compareTo(money) >= 0) {
                BigDecimal balanceAfterWithdraw = acc.getBalance().subtract(money);

                acc.setBalance(balanceAfterWithdraw);
                accountRepository.save(acc);
            }
        }
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Optional<Account> account = accountRepository.findById(id);

        if(account.isPresent() && money.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal newBalance = account.get().getBalance().add(money);
            account.get().setBalance(newBalance);
            accountRepository.save(account.get());
        }
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
