package net.friend.spring.data;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.friend.spring.data.model.Address;
import net.friend.spring.data.model.Person;
import net.friend.spring.data.service.AddressService;
import net.friend.spring.data.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

  public static void main(String[] args){
    SpringApplication.run(Application.class);
  }

  @Autowired
  private AddressService addressService;

  @Autowired
  private PersonService personService;

  @Override
  public void run(String... strings) throws Exception {
    //init address list
    List<Address> addresses = new ArrayList<>();
    Address streetAddress = new Address(null, "445 Mount Eden Road", "Mount Eden", "Auckland", "US");
    addresses.add(streetAddress);
    Address counterDelivery = new Address(null, "Counter Delivery", "Carters Beach PostCentre", "Westport", "US");
    addresses.add(counterDelivery);
    addressService.save(addresses);

    //get address list
    List<Address> addressList = addressService.findByCountry("US", 0, 10);
    log.info("Address list {}", addressList);

    //init person list
    Person monkeyDLuffy = new Person(null, "Monkey D. Luffy", 21, true);
    personService.save(monkeyDLuffy);
    Person sonGoku = new Person(null, "Son Goku", 34, false);
    personService.save(sonGoku);
    Page<Person> personPage = personService.findByName("Son Goku", new PageRequest(0, 10));
    log.info("Person list {} {}", personPage.getTotalElements(), personPage.getContent().get(0));
  }
}
