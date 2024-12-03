package pl.lodz.p.edu.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.edu.mvc.dto.ClientDTO;
import pl.lodz.p.edu.mvc.dto.RentDTO;
import pl.lodz.p.edu.mvc.dto.UserDTO;
import pl.lodz.p.edu.mvc.model.Rent;
import pl.lodz.p.edu.mvc.model.user.Client;
import pl.lodz.p.edu.mvc.model.user.Role;

import java.util.List;

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

    public RentDTO createRent(RentDTO rentDTO) {
        try {
            RentDTO createdRent = restTemplate.postForObject(apiUrl + "/rents", rentDTO, RentDTO.class);

            return createdRent;
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new RuntimeException("Failed to create rent: " + ex.getResponseBodyAsString(), ex);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to create rent: " + e.getMessage(), e);
        }
    }

    public List<RentDTO> getRents() {
        try {
            return restTemplate.getForObject(apiUrl + "/rents/active", List.class);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new RuntimeException("Failed to create rent: " + ex.getResponseBodyAsString(), ex);
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
