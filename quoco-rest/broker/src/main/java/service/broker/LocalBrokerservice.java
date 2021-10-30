package service.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import service.core.ClientApplication;
import service.core.ClientInfo;
import service.core.Quotation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@RestController
public class LocalBrokerservice {

    private static int numApplication = 0;
    private Map<Integer, ClientApplication> clientApplicationMap = new HashMap<>();

    @Autowired
    private ArrayList<String> services;

    @RequestMapping(value="/application",method= RequestMethod.POST)
    public ResponseEntity<ClientApplication> createClientApplication(@RequestBody ClientInfo info) throws URISyntaxException {
        ClientApplication application = new ClientApplication(numApplication++, info, getQuotations(info));
        clientApplicationMap.put(application.getId(), application);
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString()+ "/application/"+application.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));
        return new ResponseEntity<>(application, headers, HttpStatus.CREATED);
    }

    public ArrayList getQuotations(ClientInfo info) {
        ArrayList<Quotation> quotations = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();


        for (String service : services){
            HttpEntity<ClientInfo> request = new HttpEntity<>(info);
            System.out.println("Service: "+ service);
            try {
                Quotation quotation = restTemplate.postForObject("http://" + service + "/quotations", request, Quotation.class);
                quotations.add(quotation);
            }catch (Exception e){
                System.out.println("Cannot connect " + service);
            }
        }

        return quotations;
    }


    @RequestMapping(value="/application/{application-number}",method=RequestMethod.GET)
    public ClientApplication getResource(@PathVariable("application-number") int applicationID) {
        ClientApplication application = clientApplicationMap.get(applicationID);
        if (application == null) throw new NoSuchApplicationException();
        return application;
    }

    @RequestMapping(value="/application",method=RequestMethod.GET)
    public ArrayList<ClientApplication> listApplications() {
        return new ArrayList<>(clientApplicationMap.values());
    }



}
