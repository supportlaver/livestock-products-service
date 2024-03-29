package livestock.orderservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @Embedded
    private Address address;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    private String loginId;
    private String password;



    public Member() {
    }

    public Member(Address address, String name) {
        this.address = address;
        this.name = name;
    }

    public Member(Address address, String name, String loginId, String password) {
        this.address = address;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }
}
