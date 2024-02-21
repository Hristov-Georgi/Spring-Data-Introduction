package springDataIntroLab.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_names", unique = true, nullable = false)
    private String username;

    private int age;

    @OneToMany(targetEntity = Account.class, mappedBy = "user")
    private Set<Account> accounts;

    public User(){}

    public User(String userName, int age) {
        this.username = userName;
        this.age = age;
        this.accounts = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Account> getAccounts() {
        return Collections.unmodifiableSet(this.accounts);
    }

    public void setAccounts(Account account) {
        this.accounts.add(account);
    }
}
