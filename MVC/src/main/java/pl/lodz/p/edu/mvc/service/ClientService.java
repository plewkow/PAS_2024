package pl.lodz.p.edu.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.edu.mvc.dto.ClientDTO;
import pl.lodz.p.edu.mvc.model.user.Role;

@Service
public class ClientService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8080/api";

    @Autowired
    public ClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClientDTO registerClient(ClientDTO clientDTO) {
        try {
            clientDTO.setRole(Role.CLIENT);
            return restTemplate.postForObject(apiUrl + "/users", clientDTO, ClientDTO.class);
        } catch (HttpClientErrorException.Conflict ex) {
            throw new RuntimeException("Login już istnieje. Wybierz inny.", ex);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new RuntimeException("Błąd rejestracji: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString(), ex);
        } catch (Exception ex) {
            throw new RuntimeException("Błąd rejestracji: " + ex.getMessage(), ex);
        }
    }
}
