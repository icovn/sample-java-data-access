package net.friend.spring.data.repository;

import net.friend.spring.data.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

  Page<Person> findByName(String name, Pageable pageable);

  Page<Person> findByNameAndIsActive(String name, boolean isActive, Pageable pageable);
}
