package net.friend.spring.data.service;

import java.util.List;
import net.friend.spring.data.model.Address;

public interface AddressService {

  Iterable<Address> save(List<Address> addresses);

  List<Address> findByCountry(String country, int pageIndex, int pageSize);
}
