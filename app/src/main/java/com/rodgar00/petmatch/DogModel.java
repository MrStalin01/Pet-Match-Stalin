package com.rodgar00.petmatch;

import com.google.gson.annotations.SerializedName;

public class DogModel {

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("duenyo")
    private String duenyo;

    @SerializedName("edad")
    private int edad;

    @SerializedName("localizacion")
    private String localizacion;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("categoria")
    private String categoria;

    @SerializedName("esRefugio")
    private boolean esRefugio;

    // Imagen como URL o null (si quieres subir foto, hay que usar Multipart)
    @SerializedName("imagen")
    private String imagen;

    // Constructor
    public DogModel(String nombre, String duenyo, int edad, String localizacion,
                    String descripcion, String categoria, boolean esRefugio, String imagen) {
        this.nombre = nombre;
        this.duenyo = duenyo;
        this.edad = edad;
        this.localizacion = localizacion;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.esRefugio = esRefugio;
        this.imagen = imagen;
    }

    // Getters si necesitas
    public String getNombre() { return nombre; }
    public String getDuenyo() { return duenyo; }
    public int getEdad() { return edad; }
    public String getLocalizacion() { return localizacion; }
    public String getDescripcion() { return descripcion; }
    public String getCategoria() { return categoria; }
    public boolean getEsRefugio() { return esRefugio; }
    public String getImagen() { return imagen; }
}
