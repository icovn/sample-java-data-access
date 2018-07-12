package net.friend.spring.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Address {

  private Long id;

  private String street;

  private String district;

  private String city;

  private String country;
}
