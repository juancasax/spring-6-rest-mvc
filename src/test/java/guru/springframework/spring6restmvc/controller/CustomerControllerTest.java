package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import guru.springframework.spring6restmvc.services.CustomerServiceImpl;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CustomerService customerService;

  CustomerServiceImpl customerServiceImp = new CustomerServiceImpl();

  @Test
  void testListCostumers() throws Exception {
    // arrange
    given(customerService.getAllCustomers()).willReturn(customerServiceImp.getAllCustomers());

    // act

    // assert
    mockMvc.perform(get("/api/v1/customer")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()", is(3)));

  }

  @Test
  void testGetCustomersById() throws Exception {
    // arrange
    final var example = Customer.builder()
        .id(UUID.randomUUID())
        .name("some Name")
        .version(2)
        .createdDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();

    given(customerService.getCustomerById(example.getId())).willReturn(example);
    // act

    // assert
    mockMvc.perform(get("/api/v1/customer/" + example.getId())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(example.getId().toString())))
        .andExpect(jsonPath("$.name", is(example.getName())));
  }
}