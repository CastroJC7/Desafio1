package sv.edu.udb.serviciorest;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.edu.udb.model.Artista;
import sv.edu.udb.model.ArtistasDAO;

@Path("artistas")
public class ArtistasRest {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtistas() throws SQLException {
        ArtistasDAO artistaDAO = new ArtistasDAO();
        List<Artista> artistas = artistaDAO.findAll();
        return Response.status(200).entity(artistas).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByArtistaId(@PathParam("id") int id) throws SQLException {
        ArtistasDAO artistaDAO = new ArtistasDAO();
        Artista artista = artistaDAO.findById(id);

        if (artista == null) {
            return Response.status(404).build();
        }

        return Response.status(200).entity(artista).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public Response eliminarArtista(@PathParam("id") int id) throws SQLException {
        ArtistasDAO artistaDAO = new ArtistasDAO();
        Artista artista = artistaDAO.findById(id);

        if (artista == null) {
            return Response.status(400)
                .entity("Artista no corresponde a ninguna existencia")
                .header("Access-Control-Allow-Origin", "*")
                .build();
        }

        try {
            artistaDAO.delete(id);
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Artista eliminado exitosamente")
                    .build();
        } catch (SQLException e) {
            return Response.status(500)
                    .entity("Error al eliminar el artista")
                    .build();
        }
        

    }
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarArtista(@PathParam("id") int id, Artista artistaActualizado) {
        ArtistasDAO artistaDAO = new ArtistasDAO();

        try {
            Artista artista = artistaDAO.findById(id);

            if (artista == null) {
                return Response.status(404)
                        .entity("No se encontró ningún artista con el ID proporcionado.")
                        .build();
            }

            // Actualizar los campos del artista existente
            artista.setNombreArtista(artistaActualizado.getNombreArtista());
            artista.setDescripcion(artistaActualizado.getDescripcion());

            // Guardar los cambios en la base de datos
            artistaDAO.update(artista);

            return Response.status(200)
                    .entity("El artista ha sido actualizado exitosamente.")
                    .build();
        } catch (Exception e) {
            return Response.status(500)
                    .entity("Se produjo un error al intentar actualizar el artista.")
                    .build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearArtista(Artista artista) {
        if (artista == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El disco es nulo")
                    .build();
        }

        ArtistasDAO artistasDAO = new ArtistasDAO();
        
        try {
            artistasDAO.insert(artista);
            return Response.status(Response.Status.CREATED)
                    .entity(artista)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al insertar el disco: " + e.getMessage())
                    .build();
        }
    }
}



