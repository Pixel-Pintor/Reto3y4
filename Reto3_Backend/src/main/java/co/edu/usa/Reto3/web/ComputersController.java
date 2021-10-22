/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3.web;

import co.edu.usa.Reto3.model.Computer;
import co.edu.usa.Reto3.service.ComputersService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Laura
 */
@RestController
@RequestMapping("/api/Computer")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ComputersController {
    
    @Autowired
    private ComputersService computersService;
    
    @GetMapping("/all")
    public List<Computer> getComputers(){
        return computersService.getAll();
    }
    
   @GetMapping("/{idComputadores}")
   public Optional<Computer> getComputers(@PathVariable("idComputadores")int idComputadores){
       return computersService.getComputers(idComputadores);
   }
           
   @PostMapping("/save")
   @ResponseStatus(HttpStatus.CREATED)
   public Computer save(@RequestBody Computer c){
       return computersService.save(c);
   }
   @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Computer update(@RequestBody Computer c) {
        return computersService.update(c);
    }

    @DeleteMapping("/{idComputadores}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("idComputadores") int idComputadores) {
        return computersService.deleteComputadores(idComputadores);
    }
}
