package co.usa.reto3.web;

import co.usa.reto3.model.Computer;
import co.usa.reto3.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Computer")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    @GetMapping("/all")
    public List<Computer> getComputers() {
        return computerService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Computer> getComputerById(@PathVariable("id") int id) {
        return computerService.getComputer(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Computer save(@RequestBody Computer computer) {
        return computerService.save(computer);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Computer update(@RequestBody Computer computer) {
        return computerService.update(computer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int computerId) {
        return computerService.deleteComputer(computerId);
    }

    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id){
        return new ResponseEntity<>(computerService.delete(id), HttpStatus.NO_CONTENT);
        boolean del = categoriaService.delete(id);
        HttpStatus status = del ? HttpStatus.NO_CONTENT : HttpStatus.RESET_CONTENT;
        return new ResponseEntity<>(del, status);

    }
    */
}
