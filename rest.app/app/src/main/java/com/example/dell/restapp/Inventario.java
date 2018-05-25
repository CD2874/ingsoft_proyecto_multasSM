package com.example.dell.restapp;

/**
 * Created by DELL on 8/04/2017.
 */

public class Inventario {
    int id;
    String fecha_i;
    String fecha_f;
    int cantidad;
    double precio;
    double total;
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_i() {
        return fecha_i;
    }

    public void setFecha_i(String fecha_i) {
        this.fecha_i = fecha_i;
    }

    public String getFecha_f() {
        return fecha_f;
    }

    public void setFecha_f(String fecha_f) {
        this.fecha_f = fecha_f;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
