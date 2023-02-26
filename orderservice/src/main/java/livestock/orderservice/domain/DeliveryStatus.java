package livestock.orderservice.domain;

import javax.persistence.Embeddable;

@Embeddable
public enum DeliveryStatus {
    READY,ING,COMP
}
