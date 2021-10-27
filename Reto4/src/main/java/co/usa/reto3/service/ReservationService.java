package co.usa.reto3.service;

import co.usa.reto3.model.Reservation;
import co.usa.reto3.model.custom.CountClient;
import co.usa.reto3.model.custom.StatusReservation;
import co.usa.reto3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> resAux = reservationRepository.getReservation(reservation.getIdReservation());
            if (resAux.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public boolean deleteReservation(int reservationId) {
        System.out.println("Voy a borrar la reservacion con el id: " + reservationId);
        return getReservation(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> element = reservationRepository.getReservation(reservation.getIdReservation());
            if (element.isPresent()) {
                if (reservation.getStartDate() != null) {
                    element.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    element.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    element.get().setStatus(reservation.getStatus());
                }
                if (reservation.getScore() != null) {
                    element.get().setScore(reservation.getScore());
                }
                reservationRepository.save(element.get());
                return element.get();
            }
        }
        return reservation;
    }

    public List<CountClient> getTopClients() {
        return reservationRepository.getTopClients();
    }

    public StatusReservation getStatusReport() {
        List<Reservation> completed = reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationByStatus("cancelled");

        return new StatusReservation(completed.size(), cancelled.size());
    }

    public List<Reservation> getReservationPeriod(String date1, String date2) {

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne = new Date();
        Date dateTwo = new Date();
        try {
            dateOne = parser.parse(date1);
            dateTwo = parser.parse(date2);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        if (dateOne.before(dateTwo)) {
            return reservationRepository.getReservationPeriod(dateOne, dateTwo);
        } else {
            return new ArrayList<>();
        }
    }
}
