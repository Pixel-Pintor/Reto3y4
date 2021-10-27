package co.usa.reto3.service;

import co.usa.reto3.model.Computer;
import co.usa.reto3.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService {

    @Autowired
    private ComputerRepository computerRepository;

    public List<Computer> getAll() {
        return computerRepository.getAll();
    }

    public Optional<Computer> getComputer(int id) {
        return computerRepository.getComputer(id);
    }

    public Computer save(Computer computer) {
        if (computer.getId() == null) {
            return computerRepository.save(computer);
        } else {
            Optional<Computer> comAux = computerRepository.getComputer(computer.getId());
            if (comAux.isEmpty()) {
                return computerRepository.save(computer);
            } else {
                return computer;
            }
        }
    }

    public boolean deleteComputer(int computerId) {
        System.out.println("Voy a borrar el computador con el id : " + computerId);
        return getComputer(computerId).map(computer -> {
            computerRepository.delete(computer);
            return true;
        }).orElse(false);
    }

    public Computer update(Computer computer) {
        if (computer.getId() != null) {
            Optional<Computer> element = computerRepository.getComputer(computer.getId());
            if (element.isPresent()) {
                if (computer.getName() != null) {
                    element.get().setName(computer.getName());
                }
                if (computer.getBrand() != null) {
                    element.get().setBrand(computer.getBrand());
                }
                if (computer.getYear() != null) {
                    element.get().setYear(computer.getYear());
                }
                if (computer.getDescription() != null) {
                    element.get().setDescription(computer.getDescription());
                }
                if (computer.getCategory() != null) {
                    element.get().setCategory(computer.getCategory());
                }
                computerRepository.save(element.get());
                return element.get();
            }
        }
        return computer;
    }
}
