package co.usa.reto3.repository;

import co.usa.reto3.model.Client;
import co.usa.reto3.model.Reservation;
import co.usa.reto3.model.custom.CountClient;
import co.usa.reto3.repository.crud.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public void delete(Reservation reservation) {
        reservationCrudRepository.delete(reservation);
    }

    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<Reservation> getReservationPeriod(Date dateOne, Date dateTwo) {
        return reservationCrudRepository.findByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }

    public List<CountClient> getTopClients() {

        List<CountClient> response = new ArrayList<>();
        List<Object[]> report = reservationCrudRepository.countTotalReservationByClient();
        for (int i = 0; i < report.size(); i++) {
            Client category = (Client) report.get(i)[0];
            Long amount = (Long) report.get(i)[i];
            CountClient countClient = new CountClient(amount, category);
            response.add(countClient);
        }
        return response;
    }
}
