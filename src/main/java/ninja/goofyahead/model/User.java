package ninja.goofyahead.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ninja.goofyahead.views.View;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @JsonView(View.Internal.class)
    private int id;
    @JsonView(View.Public.class)
    private String name;
    @JsonView(View.Public.class)
    private String surname;
    @JsonView(View.Public.class)
    private String company;

}
