package net.friend.spring.data.service;

import java.util.List;
import net.friend.spring.data.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {

  Person save(Person person);

  Iterable<Person> save(List<Person> personList);

  Person findById(Long id);

  Page<Person> findByName(String name, Pageable pageable);

  void delete(Long id);

  void delete(Person person);
}
