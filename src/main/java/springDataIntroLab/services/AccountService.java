package springDataIntroLab.services;

import springDataIntroLab.models.Account;

import java.math.BigDecimal;

public interface AccountService {

    void registerAccount(Account account);

    void withdrawMoney(BigDecimal money, Long id);
    void transferMoney(BigDecimal money, Long id);
}
