package livestock.orderservice.domain.livestock;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("COW")
public class Cow extends LiveStock{
    private int quality; // 1 -> + 2-> ++
    private String type; //토시살 , 살치살 , 등심...
}
