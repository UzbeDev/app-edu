package it.city.edu.payload;

import it.city.edu.entity.District;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResContact {

    private District district;

    private String email;

    private String fax;

    private Set<String> phoneNumbers;
}
