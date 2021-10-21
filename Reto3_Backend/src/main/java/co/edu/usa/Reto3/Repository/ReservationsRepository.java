/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3.Repository;

import co.edu.usa.Reto3.model.Reservation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.usa.Reto3.CrudRepository.ReservationsCrudRepository;

/**
 *
 * @author Laura
 */
@Repository
public class ReservationsRepository {
    
    @Autowired
    private ReservationsCrudRepository crud4;
    public List<Reservation> getAll(){
        return (List<Reservation>) crud4.findAll();
    }
    public Optional<Reservation> getReservations(int id){
        return crud4.findById(id);
    }

    public Reservation save(Reservation reservations){
        return crud4.save(reservations);
    }
    public void delete(Reservation reservations){
        crud4.delete(reservations);
    }  
}
    

