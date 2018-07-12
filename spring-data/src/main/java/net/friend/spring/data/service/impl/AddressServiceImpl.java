package net.friend.spring.data.service.impl;

import java.util.List;
import net.friend.spring.data.model.Address;
import net.friend.spring.data.repository.AddressRepository;
import net.friend.spring.data.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  private AddressRepository addressRepository;

  @Override
  public Iterable<Address> save(List<Address> addresses) {
    return addressRepository.save(addresses);
  }

  @Override
  public List<Address> findByCountry(String country, int pageIndex, int pageSize) {
    return addressRepository.findByCountry(country, new PageRequest(pageIndex, pageSize)).getContent();
  }
}
