package com.sda.weddingApp.configuration;

import com.sda.weddingApp.model.Account;
import com.sda.weddingApp.model.AccountRole;
import com.sda.weddingApp.model.Person;
import com.sda.weddingApp.model.TypeOfTask;
import com.sda.weddingApp.repository.AccountRepository;
import com.sda.weddingApp.repository.AccountRoleRepository;
import com.sda.weddingApp.repository.PersonRepository;
import com.sda.weddingApp.repository.TypeofTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.sda.weddingApp.configuration.TaskNames.*;

/**
 * Klasa inicjalizuje dane i metoda onApplicationEvent jest wywołana RAZ w momencie załdowania aplikacji.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final static String ADMIN_USERNAME = "admin";
    private final static String ADMIN_PASSWORD = "nimda";

    public final static String ROLE_ADMIN = "ROLE_ADMIN";
    public final static String ROLE_USER = "ROLE_USER";
    public final static String ROLE_SUPERVISOR = "ROLE_SUPERVISOR";

    private final static String[] AVAILABLE_ROLES = {ROLE_ADMIN, ROLE_USER, ROLE_SUPERVISOR};

    private final static String[] DEFAULT_TYPES_OF_TASKS = {
            TASK_BAND,
            TASK_DJ,
            TASK_VENUE,
            TASK_AUTO,
            TASK_ALCOHOL,
            TASK_INVITATIONS,
            TASK_RINGS,
            TASK_PHOTOGRAPHER,
            TASK_CAMERAMAN,

            TASK_RINGS1,
            TASK_OUTFIT1,
            TASK_HAIRDRESSER1,
            TASK_BARBER1,
            TASK_MAKEUP_ARTIST1,
            TASK_MANI_PEDI_CURE1,

            TASK_RINGS2,
            TASK_OUTFIT2,
            TASK_HAIRDRESSER2,
            TASK_BARBER2,
            TASK_MAKEUP_ARTIST2,
            TASK_MANI_PEDI_CURE2,
    };

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final PersonRepository personRepository;

    private final TypeofTaskRepository typeofTaskRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for (String role : AVAILABLE_ROLES) {
            addRole(role);
        }

        addUser(ADMIN_USERNAME, ADMIN_PASSWORD, "admin@admin.pl", AVAILABLE_ROLES);
        addUser("user", "resu", "user@user.pl", new String[]{ROLE_USER});

        for (String task : DEFAULT_TYPES_OF_TASKS) {
            addTask(task);
        }
    }

    private void addTask(String task) {
        Optional<TypeOfTask> optionalTypeOfTask = typeofTaskRepository.findByName(task);
        if (!optionalTypeOfTask.isPresent()) {
            TypeOfTask typeOfTask = TypeOfTask.builder()
                    .name(task)
                    .build();

            typeofTaskRepository.save(typeOfTask);
        }
    }

    private void addUser(String username, String password, String email, String[] roles) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (!optionalAccount.isPresent()) {
            Person person = Person.builder()
                    .firstName(username)
                    .lastName("admin")
                    .email(email)
                    .build();
            personRepository.save(person);

            Account account = Account.builder()
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .person(person)
                    .build();

            Set<AccountRole> rolesSet = new HashSet<>();
            for (String role : roles) {
                Optional<AccountRole> optionalAccountRole = accountRoleRepository.findByName(role);
                if (optionalAccountRole.isPresent()) {
                    AccountRole accountRole = optionalAccountRole.get();
                    rolesSet.add(accountRole);
                }
            }

            account.setRoles(rolesSet);

            accountRepository.save(account);
        }
    }

    private void addRole(String role) {
        Optional<AccountRole> optionalAccountRole = accountRoleRepository.findByName(role);
        if (!optionalAccountRole.isPresent()) {
            AccountRole accountRole = AccountRole.builder()
                    .name(role)
                    .build();

            accountRoleRepository.save(accountRole);
        }
    }
}
