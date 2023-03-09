package guru.springframework.spring6restmvc.controller;


import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {

  private final CustomerService customerService;

  @RequestMapping(method = RequestMethod.GET)
  public List<Customer> customerList() {
    return customerService.listCustomers();
  }

  @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
  public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
    return customerService.getCustomerById(customerId);
  }
}
