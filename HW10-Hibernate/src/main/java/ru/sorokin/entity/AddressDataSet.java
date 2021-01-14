package ru.sorokin.entity;

import javax.persistence.*;

@Entity
@Table(name = "address_data")
public class AddressDataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "address")
    private User user;

    public AddressDataSet() {
    }

    public AddressDataSet(long id, String address) {
        this.id = id;
        this.address = address;
    }

    public AddressDataSet(String address) {
        this.address = address;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
