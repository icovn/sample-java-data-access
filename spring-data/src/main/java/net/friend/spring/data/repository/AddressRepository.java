package net.friend.spring.data.repository;

import java.util.List;
import net.friend.spring.data.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

  Page<Address> findByCountry(String country, Pageable pageable);

  @Query("SELECT obj FROM Address obj WHERE obj.country = :country")
  List<Address> findByCountri(@Param("country") String country);

  List<Address> findByCountry(String country);
}
