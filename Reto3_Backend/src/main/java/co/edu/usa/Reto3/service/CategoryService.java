/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.Reto3.service;

import co.edu.usa.Reto3.Repository.CategoryRepository;
import co.edu.usa.Reto3.model.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laura
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository metodosCrud;

    public List<Category> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Category> getCategory(int CategoriaId) {
        return metodosCrud.getCategory(CategoriaId);
    }

    public Category save(Category category) {
        if (category.getId()== null) {
            return metodosCrud.save(category);
        } else {
            Optional<Category> category1 = metodosCrud.getCategory(category.getId());
            if (category1.isEmpty()) {
                return metodosCrud.save(category);
            } else {
                return category;
            }
        }
    }

    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category>g=metodosCrud.getCategory(category.getId());
            if(!g.isEmpty()){
                if(category.getDescription()!=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    g.get().setName(category.getName());
                }
                return metodosCrud.save(g.get());
            }
        }
        return category;
    }
    public boolean deletecategory(int categoriaId){
        Boolean d=getCategory(categoriaId).map(category -> {
            metodosCrud.delete(category);
            return true;
        }).orElse(false);
        return d;
    }
    
    
}
