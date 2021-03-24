package vn.techmaster.securityjpa.controller.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.techmaster.securityjpa.model.Tag;

import javax.validation.constraints.Size;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
  private Long id;
  @NotNull
	@Size(min=10, max=300, message="title must be with 10 and 300")
  private String title;

  @NotNull
	@Size(min=20, max=5000, message="content must be with 20 and 5000")
  private String content;

  private Long user_id;
  private Set<Tag> tags;
}
