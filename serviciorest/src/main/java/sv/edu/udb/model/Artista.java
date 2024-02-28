package sv.edu.udb.model;

public class Artista {
	
    private int idArtista;
    private String nombreArtista;
    private String descripcion;

    // Constructor, getters y setters
    public Artista() {}

    public Artista(String nombreArtista, String descripcion) {
        this.nombreArtista = nombreArtista;
        this.descripcion = descripcion;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
