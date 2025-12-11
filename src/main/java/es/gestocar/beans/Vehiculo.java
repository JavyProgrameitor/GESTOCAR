package es.gestocar.beans;

import java.util.Date;
import java.util.List;

public class Vehiculo {

    private int idVehiculo;
    private int usuarioId;
    private String marca;
    private String modelo;
    private Vehiculo vehiculo;

    public enum Motor {
        GASOLINA, GASOIL, ELECTRICO
    }
    public Motor motor;

    private String matricula;
    private String cilindrada;
    private String caballos;
    private String color;
    private Date fechaCompra;
    private Date fechaVenta;
    private Double preciocompra;
    private Double precioventa;
    private List<Foto> fotos;
    private List<Gasto> gastos;

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(String cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getCaballos() {
        return caballos;
    }

    public void setCaballos(String caballos) {
        this.caballos = caballos;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(Double preciocompra) {
        this.preciocompra = preciocompra;
    }

    public Double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(Double precioventa) {
        this.precioventa = precioventa;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    
    
}
