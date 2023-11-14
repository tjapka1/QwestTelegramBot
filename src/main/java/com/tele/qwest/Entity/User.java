package com.tele.qwest.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "chart_id", unique = true)
    private Long chartId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "userName")
    private String userName;
    @Column(name = "registeredAt")
    private Timestamp registeredAt;
    @Column(name = "balls")
    private int Balls;
    @Column(name = "currentQuestion")
    private int currentQuestion;
    @Column(name = "live")
    private int live;
    @Column(name = "countFalse")
    private int countFalse;
    @Column(name = "questionContain")
    private int questionContain;



}
