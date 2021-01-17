package ru.sorokin.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "homework_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressDataSet address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PhoneDataSet> phoneDataSet = new HashSet<>();

    public User() {
    }

    public User(long id, AddressDataSet address, Set<PhoneDataSet> phoneDataSet) {
        this.id = id;
        this.address = address;
        this.phoneDataSet = phoneDataSet;
    }

    public AddressDataSet getAddress() {
        return address;
    }

    public void setAddress(AddressDataSet addressDataSet) {
        this.address = addressDataSet;
        addressDataSet.setUser(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<PhoneDataSet> getPhoneDataSet() {
        return phoneDataSet;
    }

    public void setPhoneDataSet(Set<PhoneDataSet> phoneDataSet) {
        this.phoneDataSet = phoneDataSet;
        phoneDataSet.forEach(p -> p.setUser(this));
    }
}
