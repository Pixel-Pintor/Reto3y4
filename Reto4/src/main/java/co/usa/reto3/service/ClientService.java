package co.usa.reto3.service;

import co.usa.reto3.model.Client;
import co.usa.reto3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Client> getAll() {
        return clientRepository.getAll();
    }
    
    public Optional<Client> getClient(int id) {
        return clientRepository.getClient(id);
    }
    
    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> cliAux = clientRepository.getClient(client.getIdClient());
            if (cliAux.isEmpty()) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public boolean deleteClient(int clientId) {
        System.out.println("Vou a borrar el cliente con el id: " + clientId);
        return getClient(clientId).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
    }

    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> element = clientRepository.getClient(client.getIdClient());
            if (element.isPresent()) {
                if (client.getEmail() != null) {
                    element.get().setEmail(client.getEmail());
                }
                if (client.getPassword() != null) {
                    element.get().setPassword(client.getPassword());
                }
                if (client.getName() != null) {
                    element.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    element.get().setAge(client.getAge());
                }
                clientRepository.save(element.get());
                return element.get();
            }
        }
        return client;
    }
}
