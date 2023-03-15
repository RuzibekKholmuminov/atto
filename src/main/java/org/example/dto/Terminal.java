package org.example.dto;

import org.example.enums.GeneralStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Terminal")
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code")
    private String code;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private String status;
    @Column(name = "created_date")
    private LocalDateTime created_date;

    public Terminal() {
    }

    public Terminal(String code, String address, String status, LocalDateTime created_date) {
        this.code = code;
        this.address = address;
        this.status = status;
        this.created_date = created_date;
    }

    public Terminal(Integer id, String code, String address, String status, LocalDateTime created_date) {
        this.id = id;
        this.code = code;
        this.address = address;
        this.status = status;
        this.created_date = created_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", created_date=" + created_date +
                '}';
    }
}
