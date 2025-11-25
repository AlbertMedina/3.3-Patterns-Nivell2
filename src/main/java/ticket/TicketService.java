package ticket;

import java.time.LocalDate;
import java.util.List;

public class TicketService {
    private TicketDaoImpl ticketDao;

    public TicketService() {
        this.ticketDao = new TicketDaoImpl();
    }

    public boolean addTicket(LocalDate date, double price, int roomId, int userId) {
        Ticket ticket = new Ticket(date, price, roomId, userId);

        return ticketDao.insert(ticket);
    }

    public Ticket getTicket(int id) {
        return ticketDao.findById(id);
    }

    public List<Ticket> listTicket(){
        return ticketDao.findAll();
    }

    public boolean updateTicket(int id, LocalDate newDate, double newPrice, int newRoomId, int newUserId ) {
        Ticket ticket = ticketDao.findById(id);
        if (ticket == null) return false;

        ticket.setDate(newDate);
        ticket.setPrice(newPrice);
        ticket.setRoomId(newRoomId);
        ticket.setUserId(newUserId);
        return ticketDao.update(ticket);
    }

    public boolean deleteTicket(int id) {
        Ticket ticket = ticketDao.findById(id);
        if (ticket == null) return false;

        return ticketDao.delete(id);
    }
}
