package ServicesTest;

import db.DBConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reward.Reward;
import reward.RewardDaoImpl;
import reward.RewardService;
import user.User;
import user.UserDaoImpl;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RewardServiceTest {

    private static RewardDaoImpl rewardDaoMock;
    private static UserDaoImpl userDaoMock;
    private static RewardService rewardService;


    @BeforeEach
    void setUp() throws Exception{
        rewardDaoMock = mock(RewardDaoImpl.class);
        userDaoMock = mock(UserDaoImpl.class);
        rewardService = new RewardService();

        Field rewardDaoField = RewardService.class.getDeclaredField("rewardDao");
        rewardDaoField.setAccessible(true);
        rewardDaoField.set(rewardService, rewardDaoMock);

        Field userDaoField = RewardService.class.getDeclaredField("userDao");
        userDaoField.setAccessible(true);
        userDaoField.set(rewardService, userDaoMock);

    }



    @Test
    public void addRewardTest() {
        LocalDate date = LocalDate.of(2025, 11, 27);

        when(userDaoMock.findById(1)).thenReturn(new User("John", "Doe Santos", "john@example.com", false));
        when(rewardDaoMock.insert(any(Reward.class))).thenReturn(true);

        boolean result = rewardService.addReward("Medalla", "Pasar la aventura en el tiempo previsto.", date, 1);

        assertTrue(result);
        verify(rewardDaoMock, times(1)).insert(any(Reward.class));
    }



    @Test
    public void updateRewardTest() {
        Reward existing = new Reward("Old name", "Old description", LocalDate.now(), 1);
        existing.setId(10);

        when(rewardDaoMock.findById(10)).thenReturn(existing);
        when(userDaoMock.findById(2)).thenReturn(new User("John", "Doe Santos", "john@example.com", false));
        when(rewardDaoMock.update(existing)).thenReturn(true);

        boolean result = rewardService.updateReward(10, "New name", "New description",
                LocalDate.of(2025, 4, 1), 2);

        assertTrue(result);
        verify(rewardDaoMock, times(1)).update(existing);

        assertEquals("New name", existing.getName());
        assertEquals("New description", existing.getDescription());
        assertEquals(2, existing.getUserId());
    }

    @Test
    public void deleteRewardTest() {
        when(rewardDaoMock.delete(5)).thenReturn(true);

        boolean result = rewardService.deleteReward(5);

        assertTrue(result);
        verify(rewardDaoMock, times(1)).delete(5);

    }

    @Test
    void getRewardByIdTest() {
        Reward reward = new Reward("Medal of Honor", "Description", LocalDate.now(), 3);
        reward.setId(99);

        when(rewardDaoMock.findById(99)).thenReturn(reward);

        Reward result = rewardService.getRewardById(99);

        assertNotNull(result);
        assertEquals(99, result.getId());
        verify(rewardDaoMock, times(1)).findById(99);
    }

    @Test
    void getAllRewardsTest() {
        List<Reward> mockList = Arrays.asList(
                new Reward("Reward1", "Description1", LocalDate.now(), 1),
                new Reward("Reward2", "Description2", LocalDate.now(), 2)
        );

        when(rewardDaoMock.findAll()).thenReturn(mockList);
        List<Reward> result = rewardService.getRewards();

        assertEquals(2, result.size());
        verify(rewardDaoMock,times(1)).findAll();
    }

    @Test
    void getRewardsByUserIdTest() {
        List<Reward> mockList = Arrays.asList(new Reward("Reward2", "Description2", LocalDate.now(), 10),
                                new Reward("Reward1", "Description1", LocalDate.now(), 10));

        when(rewardDaoMock.findAllByUser(10)).thenReturn(mockList);
        List<Reward> result = rewardService.getRewardsByUser(10);

        assertEquals(2, result.size());
        verify(rewardDaoMock, times(1)).findAllByUser(10);

    }


}
