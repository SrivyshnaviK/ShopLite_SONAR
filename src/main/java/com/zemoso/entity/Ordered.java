package com.zemoso.entity;

import javax.persistence.*;

@Entity
@Table(name = "ordered")
public class Ordered {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "pid")
    int pid;
    @Column(name = "uid")
    int uid;

    public Ordered(int pid, int uid) {
        this.pid = pid;
        this.uid = uid;
    }

    public Ordered() {
    }

    @Override
    public String toString() {
        return "Ordered{" +
                "id=" + id +
                ", pid=" + pid +
                ", uid=" + uid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


}
