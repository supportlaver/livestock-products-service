package livestock.orderservice.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String roadName; //도로명 주소
    private String moreInfo; //상세 주소

    protected Address() {
    }

    public Address(String roadName, String moreInfo) {
        this.roadName = roadName;
        this.moreInfo = moreInfo;
    }
}
