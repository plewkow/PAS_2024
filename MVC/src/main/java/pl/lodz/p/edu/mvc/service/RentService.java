package pl.lodz.p.edu.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.edu.mvc.dto.ClientDTO;
import pl.lodz.p.edu.mvc.dto.UserDTO;
import pl.lodz.p.edu.mvc.model.Rent;
import pl.lodz.p.edu.mvc.model.user.Client;
import pl.lodz.p.edu.mvc.model.user.Role;

@Service
public class RentService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8080/api";

    @Autowired
    public RentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClientDTO registerClient(ClientDTO clientDTO) {
        try {
            clientDTO.setRole(Role.CLIENT);
            ClientDTO createdClient = restTemplate.postForObject(apiUrl + "/users", clientDTO, ClientDTO.class);

            return createdClient;
        } catch (Exception e) {
            throw new RuntimeException("Failed to register client: " + e.getMessage(), e);
        }
    }


//    public Rent createRent(Long clientId, Long resourceId) {
//        return restTemplate.postForObject(apiUrl + "/allocations",
//                new Allocation(clientId, resourceId), Allocation.class);
//    }
//
//    public List<Allocation> listAllocations(Long clientId) {
//        return restTemplate.getForObject(apiUrl + "/clients/" + clientId + "/allocations", List.class);
//    }
//
//    public void endAllocation(Long allocationId) {
//        restTemplate.delete(apiUrl + "/allocations/" + allocationId);
//    }
}
