package pl.lodz.p.edu.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.edu.mvc.dto.RentDTO;

import java.util.List;

@Service
public class RentService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8080/api";

    @Autowired
    public RentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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

    public List<RentDTO> getRentsByClientId(String clientId) {
        try {
            return restTemplate.getForObject(apiUrl + "/rents/active/client/" + clientId, List.class);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new RuntimeException("Failed to create rent: " + ex.getResponseBodyAsString(), ex);
        }
    }

    public void returnRent(String id) {
        try {
            restTemplate.put(apiUrl + "/rents/return/" + id, null);
//            restTemplate.put(apiUrl + "/rents/" + id);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new RuntimeException("Failed to return rent: " + ex.getResponseBodyAsString(), ex);
        }
    }
}
