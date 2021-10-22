/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.sbsemana3.repositorios;

import co.edu.usa.sbsemana3.modelo.Producto;
import co.edu.usa.sbsemana3.repositorios.crud.ProductoCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juvinao
 */
@Repository
public class ProductoRepository {

    @Autowired
    private ProductoCrudRepository repo;

    public List<Producto> getAll() {
        return (List<Producto>) repo.findAll();
    }
    
    public List<Producto> getByIdCategoria(int idCategoria){
        return (List<Producto>) repo.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }
    
    public Producto save(Producto producto){
        return repo.save(producto);
    }
    
    public Optional<Producto> getById(int id){
        return repo.findById(id);
    }
    
}
