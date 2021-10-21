/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.usa.Reto3.CrudRepository;

import co.edu.usa.Reto3.model.Message;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Laura
 */
public interface MessageCrudRepository extends CrudRepository<Message, Integer> {
    
}
