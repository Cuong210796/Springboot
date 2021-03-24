package vn.techmaster.securityjpa.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPOJO {
  private Long id;
  private String title;
  private String content;
  private Long user_id;
  private String userFullName;
  private LocalDateTime lastUpdate;
}
