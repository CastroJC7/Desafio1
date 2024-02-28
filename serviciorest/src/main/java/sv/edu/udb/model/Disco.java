package sv.edu.udb.model;



public class Disco {
	private int idDisco;
    private String nombreDisco;
    private int numeroCanciones;
    private double precio;

   
    private Artista artista;

    // Constructor, getters y setters
    public Disco() {}

    public Disco(String nombreDisco, int numeroCanciones, double precio, Artista artista) {
        this.nombreDisco = nombreDisco;
        this.numeroCanciones = numeroCanciones;
        this.precio = precio;
        this.artista = artista;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public String getNombreDisco() {
        return nombreDisco;
    }

    public void setNombreDisco(String nombreDisco) {
        this.nombreDisco = nombreDisco;
    }

    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

}
