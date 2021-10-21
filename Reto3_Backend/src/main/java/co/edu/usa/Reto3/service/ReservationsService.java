/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3.service;

import co.edu.usa.Reto3.Repository.ReservationsRepository;
import co.edu.usa.Reto3.model.Reservation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laura
 */
@Service
public class ReservationsService {
    
     @Autowired
    private ReservationsRepository metodosCrud;

    public List<Reservation> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Reservation> getReservations(int reservationId) {
        return metodosCrud.getReservations(reservationId);
    }

    public Reservation save(Reservation reservations){
        if(reservations.getIdReservation()==null){
            return metodosCrud.save(reservations);
        }else{
            Optional<Reservation> e= metodosCrud.getReservations(reservations.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservations);
            }else{
                return reservations;
            }
        }
    }

    public Reservation update(Reservation reservations){
        if(reservations.getIdReservation()!=null){
            Optional<Reservation> e= metodosCrud.getReservations(reservations.getIdReservation());
            if(!e.isEmpty()){

                if(reservations.getStartDate()!=null){
                    e.get().setStartDate(reservations.getStartDate());
                }
                if(reservations.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservations.getDevolutionDate());
                }
                if(reservations.getStatus()!=null){
                    e.get().setStatus(reservations.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservations;
            }
        }else{
            return reservations;
        }
    }

    public boolean deleteReservations(int reservationId) {
        Boolean aBoolean = getReservations(reservationId).map(reservations -> {
            metodosCrud.delete(reservations);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
