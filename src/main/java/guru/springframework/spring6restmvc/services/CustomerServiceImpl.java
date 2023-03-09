package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements
    CustomerService {

  private Map<UUID, Customer> customerMap;

  private CustomerServiceImpl() {
    this.customerMap = new HashMap<>();

    Customer customer1 = Customer.builder()
        .id(UUID.randomUUID())
        .customerName("Some Customer")
        .version(1)
        .createdDate(LocalDateTime.now())
        .lastModifiedDate(LocalDateTime.now())
        .build();
    Customer customer2 = Customer.builder()
        .id(UUID.randomUUID())
        .customerName("Some other Customer")
        .version(1)
        .createdDate(LocalDateTime.now())
        .lastModifiedDate(LocalDateTime.now())
        .build();
    Customer customer3 = Customer.builder()
        .id(UUID.randomUUID())
        .customerName("Some newer Customer")
        .version(1)
        .createdDate(LocalDateTime.now())
        .lastModifiedDate(LocalDateTime.now())
        .build();

    customerMap.put(customer1.getId(), customer1);
    customerMap.put(customer2.getId(), customer2);
    customerMap.put(customer3.getId(), customer3);

    log.debug("Get by Id: " );
  }
  @Override
  public List<Customer> listCustomers() {
    return new ArrayList<>(customerMap.values());
  }

  @Override
  public Customer getCustomerById(UUID id) {
    return customerMap.get(id);
  }
}
