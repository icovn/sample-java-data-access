package net.friend.spring.data.service.impl;

import java.util.List;
import net.friend.spring.data.model.Person;
import net.friend.spring.data.repository.PersonRepository;
import net.friend.spring.data.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  private PersonRepository personRepository;

  @Override
  public Person save(Person person) {
    return personRepository.save(person);
  }

  @Override
  public Iterable<Person> save(List<Person> personList) {
    return personRepository.save(personList);
  }

  @Override
  public Person findById(Long id) {
    return personRepository.findOne(id);
  }

  @Override
  public Page<Person> findByName(String name, Pageable pageable) {
    return personRepository.findByName(name, pageable);
  }

  @Override
  public void delete(Long id) {
    personRepository.delete(id);
  }

  @Override
  public void delete(Person person) {
    personRepository.delete(person);
  }
}
