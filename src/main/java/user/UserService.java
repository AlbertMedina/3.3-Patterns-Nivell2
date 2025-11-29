package user;

import subscriber.Subscriber;

import java.util.List;

public class UserService {

    private final UserDaoImpl userDao;

    public UserService() {
        userDao = new UserDaoImpl();
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }

    public User getUserById(int id) {
        return userDao.findById(id);
    }

    public boolean addUser(String name, String surnames, String email) {
        User user = new User(name, surnames, email, false);
        return userDao.insert(user);
    }

    public boolean updateUser(int id, String newName, String newSurnames, String newEmail) {
        User user = userDao.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }

        user.setName(newName);
        user.setName(newSurnames);
        user.setName(newEmail);

        return userDao.update(user);
    }

    public boolean updateUserSubscription(int id, boolean subscribed) {
        User user = userDao.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }

        user.setSubscribed(subscribed);

        return userDao.update(user);
    }

    public boolean deleteUser(int id) {
        return userDao.delete(id);
    }

    public void notifySubscribers(String notification) {
        List<Subscriber> subscribers = userDao.findAllSubscribers();
        subscribers.forEach(s -> s.receiveNotification(notification));
    }
}

