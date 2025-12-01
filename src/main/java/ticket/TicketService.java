package ticket;

import room.Room;
import room.RoomDaoImpl;
import user.User;
import user.UserDaoImpl;

import java.time.LocalDate;
import java.util.List;

public class TicketService {

    private final TicketDaoImpl ticketDao;
    private final RoomDaoImpl roomDao;
    private final UserDaoImpl userDao;

    public TicketService(TicketDaoImpl ticketDao, RoomDaoImpl roomDao, UserDaoImpl userDao) {
        this.ticketDao = ticketDao;
        this.roomDao = roomDao;
        this.userDao = userDao;
    }

    public List<Ticket> getTickets() {
        return ticketDao.findAll();
    }

    public List<Ticket> getTicketsByRoom(int roomId) {
        return ticketDao.findAllByRoom(roomId);
    }

    public List<Ticket> getTicketsByUser(int userId) {
        return ticketDao.findAllByUser(userId);
    }

    public Ticket getTicketById(int id) {
        return ticketDao.findById(id);
    }

    public boolean addTicket(LocalDate date, int roomId, int userId) {
        Room room = roomDao.findById(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room with id " + roomId + " does not exist");
        }

        User user = userDao.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }

        Ticket ticket = new Ticket(date, room.getPrice(), roomId, userId);
        return ticketDao.insert(ticket);
    }

    public boolean updateTicket(int id, LocalDate newDate, double newPrice, int newRoomId, int newUserId) {
        Ticket ticket = ticketDao.findById(id);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket with id " + id + " does not exist");
        }

        Room room = roomDao.findById(newRoomId);
        if (room == null) {
            throw new IllegalArgumentException("Room with id " + newRoomId + " does not exist");
        }

        User user = userDao.findById(newUserId);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + newUserId + " does not exist");
        }

        ticket.setDate(newDate);
        ticket.setPrice(newPrice);
        ticket.setRoomId(newRoomId);
        ticket.setUserId(newUserId);

        return ticketDao.update(ticket);
    }

    public boolean deleteTicket(int id) {
        return ticketDao.delete(id);
    }

    public double getTicketsTotalRevenue() {
        return getTickets().stream().mapToDouble(Ticket::getPrice).sum();
    }
}
