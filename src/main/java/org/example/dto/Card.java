package org.example.dto;

import org.example.enums.GeneralStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "number", unique = false)
    private String number;
    @Column(name = "exp_date")
    private Date exp_date;
    @Column(name = "balance")
    private Long balance;
    @Column(name = "status")
    private String status;
    @Column(name = "phone")
    private String phone;
    @Column(name = "created_date")
    private LocalDateTime created_date;

    public Card() {
    }

    public Card(String number, Date exp_date, Long balance, String status, String phone, LocalDateTime created_date) {
        this.number = number;
        this.exp_date = exp_date;
        this.balance = balance;
        this.status = status;
        this.phone = phone;
        this.created_date = created_date;
    }

    public Card(Integer id, String number, Date exp_date, Long balance, String status, String phone, LocalDateTime created_date) {
        this.id = id;
        this.number = number;
        this.exp_date = exp_date;
        this.balance = balance;
        this.status = status;
        this.phone = phone;
        this.created_date = created_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExp_date() {
        return exp_date;
    }

    public void setExp_date(Date exp_date) {
        this.exp_date = exp_date;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", exp_date=" + exp_date +
                ", balance=" + balance +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", created_date=" + created_date +
                '}';
    }
}
