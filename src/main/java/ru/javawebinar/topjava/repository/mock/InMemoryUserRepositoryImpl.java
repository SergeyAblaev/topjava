package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        if (repository.containsKey(id)) {
            repository.remove(id);
            return true;}
        else
            log.warn("can NOT delete! {}", id);
            return false;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            int id = counter.incrementAndGet();
            user.setId(id);
            repository.put(id,user);
            return user;
        }
        else
            return  repository.replace(user.getId(),user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return new ArrayList(repository.values());    // ПРОВЕРЬ!  как сделал Григорий!!  предупреждение это игнорировать?!
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
       // return repository.values().stream().filter(email.equals(User->User.getEmail())).findFirst() ;  // ПРОВЕРЬ!!  OPTIONAL ?
        return null;
    }
}
