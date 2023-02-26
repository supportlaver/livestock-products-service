package livestock.orderservice.domain.livestock;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("CHIC")
public class Chicken extends LiveStock{

    private String manufacturer; //하림1 , 하림2 ...
}
