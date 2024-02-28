package sv.edu.udb.model;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArtistasDAO extends AppConnection {
	
	public void insert(Artista artista) throws SQLException {
	    connect();
	    pstmt = conn.prepareStatement("INSERT INTO artistas (id_artista, nombre_artista, descripcion) VALUES (?, ?, ?)");
	    pstmt.setInt(1, artista.getIdArtista()); 
	    pstmt.setString(2, artista.getNombreArtista());
	    pstmt.setString(3, artista.getDescripcion());
	    pstmt.execute();
	    close();
	}
    public void update(Artista artista) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("UPDATE artistas SET nombre_artista = ?, descripcion = ? WHERE id_artista = ?");
        pstmt.setString(1, artista.getNombreArtista());
        pstmt.setString(2, artista.getDescripcion());
        pstmt.setInt(3, artista.getIdArtista());
        pstmt.execute();
        close();
    }

 
    public ArrayList<Artista> findAll() throws SQLException {
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("SELECT id_artista, nombre_artista, descripcion FROM artistas");
        ArrayList<Artista> artistas = new ArrayList<>();

        while (resultSet.next()) {
            Artista tmp = new Artista();
            tmp.setIdArtista(resultSet.getInt(1));
            tmp.setNombreArtista(resultSet.getString(2));
            tmp.setDescripcion(resultSet.getString(3));

            artistas.add(tmp);
        }

        close();

        return artistas;
    }

    public Artista findById(int id) throws SQLException {
        Artista artista = null;

        connect();
        pstmt = conn.prepareStatement("SELECT id_artista, nombre_artista, descripcion FROM artistas WHERE id_artista = ?");
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            artista = new Artista();
            artista.setIdArtista(resultSet.getInt(1));
            artista.setNombreArtista(resultSet.getString(2));
            artista.setDescripcion(resultSet.getString(3));
        }

        close();
        return artista;
    }

    public void delete(int id) throws SQLException {
        try {
            connect();
            pstmt = conn.prepareStatement("DELETE FROM artistas WHERE id_artista = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar el artista: " + e.getMessage());
            throw e; 
        } finally {
            close(); 
        }
    }

    }


