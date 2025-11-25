package reward;

import java.time.LocalDate;
import java.util.List;

public class RewardService {

    private RewardDaoImpl rewardDao;
    public RewardService() {
        this.rewardDao = new RewardDaoImpl();
    }

    public boolean addReward(String name, String description, LocalDate date, int userId) {
        Reward reward = new Reward(name, description, date, userId);

        return rewardDao.insert(reward);
    }

    public Reward getReward(int id) {
        return rewardDao.findById(id);
    }

    public List<Reward> listRewards(){
        return rewardDao.findAll();
    }

    public boolean updateReward(int id, String newName, String newDescription, LocalDate newDate, int newUserId) {
        Reward reward = rewardDao.findById(id);
        if (reward == null) return false;

        reward.setName(newName);
        reward.setDescription(newDescription);
        reward.setDate(newDate);
        reward.setUserId(newUserId);
        return rewardDao.update(reward);
    }

    public boolean deleteReward(int id) {
        Reward reward = rewardDao.findById(id);
        if (reward == null) return false;

        return rewardDao.delete(id);
    }
}
