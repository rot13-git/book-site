package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class LibroEntity {

    @Id
    private long isbn;

    @Column
    private String nome;

    @Column
    private String autore;

    @Column
    private String categoria;

    @Column(columnDefinition = "TEXT")
    private String descrizione;

    @Column()
    private String imageName;

    public LibroEntity(long isbn, String nome, String autore, String categoria, String descrizione,String imageName) {
        this.isbn = isbn;
        this.nome = nome;
        this.autore = autore;
        this.categoria = categoria;
        this.descrizione = descrizione;
        this.imageName = imageName;
    }

    public LibroEntity() {

    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibroEntity that = (LibroEntity) o;
        return isbn == that.isbn && Objects.equals(nome, that.nome) && Objects.equals(autore, that.autore) && Objects.equals(categoria, that.categoria) && Objects.equals(descrizione, that.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, nome, autore, categoria, descrizione);
    }

    @Override
    public String toString() {
        return "LibroEntity{" +
                "isbn=" + isbn +
                ", nome='" + nome + '\'' +
                ", autore='" + autore + '\'' +
                ", categoria='" + categoria + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
