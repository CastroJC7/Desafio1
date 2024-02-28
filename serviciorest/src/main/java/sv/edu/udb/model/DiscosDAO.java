package sv.edu.udb.model;
import java.sql.SQLException;
import java.util.ArrayList;

public class DiscosDAO extends AppConnection {

	public void insert(Disco disco) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("INSERT INTO discos (nombre_disco, numero_canciones, precio, id_artista) VALUES (?, ?, ?, ?)");
        pstmt.setString(1, disco.getNombreDisco());
        pstmt.setInt(2, disco.getNumeroCanciones());
        pstmt.setDouble(3, disco.getPrecio());
        pstmt.setInt(4, disco.getArtista().getIdArtista());
        pstmt.execute();
        close();
    }

    public void update(Disco disco) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("UPDATE discos SET nombre_disco = ?, numero_canciones = ?, precio = ?, id_artista = ? WHERE id_disco = ?");
        pstmt.setString(1, disco.getNombreDisco());
        pstmt.setInt(2, disco.getNumeroCanciones());
        pstmt.setDouble(3, disco.getPrecio());
        pstmt.setInt(4, disco.getArtista().getIdArtista());
        pstmt.setInt(5, disco.getIdDisco());
        pstmt.execute();
        close();
    }

    public void delete(int id) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("DELETE FROM discos WHERE id_disco = ?");
        pstmt.setInt(1, id);
        pstmt.execute();
        close();
    }

    public ArrayList<Disco> findAll() throws SQLException {
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("SELECT d.id_disco, d.nombre_disco, d.numero_canciones, d.precio, a.id_artista, a.nombre_artista, a.descripcion FROM discos d JOIN artistas a ON d.id_artista = a.id_artista");
        ArrayList<Disco> discos = new ArrayList<>();

        while (resultSet.next()) {
            Disco tmp = new Disco();
            tmp.setIdDisco(resultSet.getInt(1));
            tmp.setNombreDisco(resultSet.getString(2));
            tmp.setNumeroCanciones(resultSet.getInt(3));
            tmp.setPrecio(resultSet.getDouble(4));

            Artista artista = new Artista();
            artista.setIdArtista(resultSet.getInt(5));
            artista.setNombreArtista(resultSet.getString(6));
            artista.setDescripcion(resultSet.getString(7));

            tmp.setArtista(artista);

            discos.add(tmp);
        }

        close();

        return discos;
    }

    public Disco findById(int id) throws SQLException {
        Disco disco = null;

        connect();
        pstmt = conn.prepareStatement("SELECT d.id_disco, d.nombre_disco, d.numero_canciones, d.precio, a.id_artista, a.nombre_artista, a.descripcion FROM discos d JOIN artistas a ON d.id_artista = a.id_artista WHERE d.id_disco = ?");
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            disco = new Disco();
            disco.setIdDisco(resultSet.getInt(1));
            disco.setNombreDisco(resultSet.getString(2));
            disco.setNumeroCanciones(resultSet.getInt(3));
            disco.setPrecio(resultSet.getDouble(4));

            Artista artista = new Artista();
            artista.setIdArtista(resultSet.getInt(5));
            artista.setNombreArtista(resultSet.getString(6));
            artista.setDescripcion(resultSet.getString(7));

            disco.setArtista(artista);
        }

        close();
        return disco;
    }
}
