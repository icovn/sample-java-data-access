package net.friend.jdbc;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.friend.jdbc.model.Person;
import net.friend.jdbc.service.PersonService;

@Slf4j
public class Application {

  public static void main(String[] args){
    PersonService personService = new PersonService();
    List<Person> personList = personService.findByName("Son Goku");
    log.info("Person list size {}", personList.size());
    if(personList.size() > 0){
      log.info("Person {}", personList.get(0));
    }
  }
}
