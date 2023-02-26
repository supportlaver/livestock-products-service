package livestock.orderservice.domain;

import javax.persistence.Embeddable;

@Embeddable
public enum OrderStatus {
    ORDER,CANCEL
}
