package co.usa.reto3.interfaces;

import co.usa.reto3.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface InterfaceReservation extends CrudRepository<Reservation, Integer> {

    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c group by c.client order by COUNT(c.client) DESC")
    public List<Object[]> countTotalReservationByClient();

    public List<Reservation> findByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

    public List<Reservation> findAllByStatus(String status);
}
