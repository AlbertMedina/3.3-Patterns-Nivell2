package reward;

import user.User;
import user.UserDaoImpl;

import java.time.LocalDate;
import java.util.List;

public class RewardService {

    private final RewardDaoImpl rewardDao;
    private final UserDaoImpl userDao;

    public RewardService(RewardDaoImpl rewardDao, UserDaoImpl userDao) {
        this.rewardDao = rewardDao;
        this.userDao = userDao;
    }

    public List<Reward> getRewards() {
        return rewardDao.findAll();
    }

    public List<Reward> getRewardsByUser(int userId) {
        return rewardDao.findAllByUser(userId);
    }

    public Reward getRewardById(int id) {
        return rewardDao.findById(id);
    }

    public boolean addReward(String name, String description, LocalDate date, int userId) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }

        Reward reward = new Reward(name, description, date, userId);
        return rewardDao.insert(reward);
    }

    public boolean updateReward(int id, String newName, String newDescription, LocalDate newDate, int newUserId) {
        Reward reward = rewardDao.findById(id);
        if (reward == null) {
            throw new IllegalArgumentException("Reward with id " + id + " does not exist");
        }

        User user = userDao.findById(newUserId);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + newUserId + " does not exist");
        }

        reward.setName(newName);
        reward.setDescription(newDescription);
        reward.setDate(newDate);
        reward.setUserId(newUserId);

        return rewardDao.update(reward);
    }

    public boolean deleteReward(int id) {
        return rewardDao.delete(id);
    }
}
