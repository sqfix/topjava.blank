package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        if (repository.get(id) != null) {
            repository.remove(id);
            return true;
        } else
            return false;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        List<User> users = (List<User>) repository.values();
        users.sort(Comparator.comparing(User::getName));
        return users;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        //TODO check for any errors this piece of code!
        return repository.values().stream().filter(u -> u.getEmail().equals(email)).findFirst().get();
    }
}
