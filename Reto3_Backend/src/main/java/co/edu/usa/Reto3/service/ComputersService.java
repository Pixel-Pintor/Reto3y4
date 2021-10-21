/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3.service;

import co.edu.usa.Reto3.Repository.ComputersRepository;
import co.edu.usa.Reto3.model.Computer;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laura
 */
@Service
public class ComputersService {
    
    @Autowired
    private ComputersRepository computadoresRepository;
    
    public List<Computer> getAll(){
        return computadoresRepository.getAll();
    }
    
    public Optional<Computer> getComputers(int idComputadores){
        return computadoresRepository.getComputers(idComputadores);
    }
    
    public Computer save(Computer c){
        if(c.getId()==null){
            return computadoresRepository.save(c);
        }else{
            Optional<Computer> caux=computadoresRepository.getComputers(c.getId());
            if(caux.isEmpty()){
                return computadoresRepository.save(c);
            }else{
                return c;
            }
        }
    }
    
       public Computer update(Computer c){
        if(c.getId()!=null){
            Optional<Computer> caux=computadoresRepository.getComputers(c.getId());
            if(!caux.isEmpty()){
                if(c.getName()!=null){
                    caux.get().setName(c.getName());
                }
                if(c.getBrand()!=null){
                    caux.get().setBrand(c.getBrand());
                }
                if(c.getYear()!=null){
                    caux.get().setYear(c.getYear());
                }
                if(c.getDescription()!=null){
                    caux.get().setDescription(c.getDescription());
                }
                if(c.getCategory()!=null){
                    caux.get().setCategory(c.getCategory());
                }
                computadoresRepository.save(caux.get());
                return caux.get();
            }else{
                return c;
            }
        }else{
            return c;
        }
    }


    public boolean deleteComputadores(int idComputadores) {
        Boolean aBoolean = getComputers(idComputadores).map(c -> {
            computadoresRepository.delete(c);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
   
    

    

