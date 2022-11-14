package com.example.demo.entities;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class ContenutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String tag;

    @Column()
    private String titolo;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "libro_entity_isbn")
    private LibroEntity libroEntity;

    public LibroEntity getLibroEntity() {
        return libroEntity;
    }

    @Column(columnDefinition = "TEXT")
    private String contenuto;

    public ContenutoEntity() {

    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public ContenutoEntity(String tag, UserEntity userEntity,LibroEntity libroEntity,String contenuto,String titolo) {
        this.tag = tag;
        this.userEntity = userEntity;
        this.libroEntity = libroEntity;
        this.contenuto = contenuto;
        this.titolo = titolo;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setLibroEntity(LibroEntity libroEntity) {
        this.libroEntity = libroEntity;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContenutoEntity that = (ContenutoEntity) o;
        return id == that.id && Objects.equals(tag, that.tag) && Objects.equals(userEntity, that.userEntity) && Objects.equals(libroEntity, that.libroEntity) && Objects.equals(contenuto, that.contenuto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag, userEntity, libroEntity, contenuto);
    }

    @Override
    public String toString() {
        return "ContenutoEntity{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", userEntity=" + userEntity +
                ", libroEntity=" + libroEntity +
                ", contenuto='" + contenuto + '\'' +
                '}';
    }
}
