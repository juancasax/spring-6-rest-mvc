package guru.springframework.spring6restmvc.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
  private UUID id;
  private String customerName;
  private int version;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;

}
