package com.eriegarbage.garbageapp.models;

import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int accountId;

    private String firstName;
    private String lastName;
    private String userName;
    private String address;
    private String password;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "account")
    private List<Bill> bills;

    private String pickupTime;
    private boolean isAdmin;
    private boolean isSuspended;
    private boolean requestSuspend;
    private boolean suspensionApproved;

    public void addBill(Bill bill) {
        this.bills.add(bill);
    }

    public int daysOverdue() {
        if (bills == null || bills.size() == 0) {
            return 0;
        }
        return bills.stream().mapToInt(Bill::daysOverdue).max().orElse(0);
    }
}
