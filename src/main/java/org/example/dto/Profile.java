package org.example.dto;

import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "created_date")
    private LocalDateTime created_date;
    @Column(name = "status")
    private GeneralStatus status;
    @Column(name = "role")
    private ProfileRole role;

    public Profile(String sanjar, String aliyev, String s, String md5) {
    }

    public Profile(String name, String surname, String phone, String password, LocalDateTime created_date, GeneralStatus status, ProfileRole role) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.password = password;
        this.created_date = created_date;
        this.status = status;
        this.role = role;



    }


    public Profile(Integer id, String name, String surname, String phone, String password, LocalDateTime created_date, GeneralStatus status, ProfileRole role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.password = password;
        this.created_date = created_date;
        this.status = status;
        this.role = role;
    }

    public Profile() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public ProfileRole getRole() {
        return role;
    }

    public void setRole(ProfileRole role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", created_date=" + created_date +
                ", status=" + status +
                ", role=" + role +
                '}';
    }
}
