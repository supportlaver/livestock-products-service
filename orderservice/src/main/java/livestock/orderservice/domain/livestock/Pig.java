package livestock.orderservice.domain.livestock;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("PIG")
public class Pig extends LiveStock{

    private String type; //목살 , 삼겹살 ...
}
