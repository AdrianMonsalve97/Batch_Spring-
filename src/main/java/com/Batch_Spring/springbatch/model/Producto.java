package com.Batch_Spring.springbatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "marca")
    private String marca;
    @Column(name = "precio")
    private Long precio;
    @Column(name = "estado")
    private String estado;
    @Column(name = "stock")
    private Long stock;
    @Column(name = "descuento")
    private Long descuento;

    // @JoinColumn(name = "carrito_id")
    // @ManyToOne(fetch = FetchType.LAZY)
    // private Set<carrito> carritos = new HashSet<>();

    public Producto() {

    }

    public Producto(Long id, String nombre, String marca, Long precio, String estado, Long descuento) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.estado = estado;
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", marca='").append(marca).append('\'');
        sb.append(", precio=").append(precio);
        sb.append(", estado=").append(estado);
        sb.append(", stock=").append(stock);
        sb.append(", descuento=").append(descuento);
        sb.append('}');
        return sb.toString();
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return String return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return Long return the precio
     */
    public Long getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    /**
     * @return String return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return Long return the descuento
     */
    public Long getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(Long descuento) {
        this.descuento = descuento;
    }

    /**
     * @return Set <carrito> carritos= new return the HashSet<>()
     */

    /**
     * @param HashSet<>() the HashSet<>() to set
     */

}
