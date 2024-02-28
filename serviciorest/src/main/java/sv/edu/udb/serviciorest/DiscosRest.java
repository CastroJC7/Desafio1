package sv.edu.udb.serviciorest;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sv.edu.udb.model.Artista;
import sv.edu.udb.model.ArtistasDAO;
import sv.edu.udb.model.Disco;
import sv.edu.udb.model.DiscosDAO;

@Path("discos")
public class DiscosRest {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscos() throws SQLException {
        DiscosDAO discoDAO = new DiscosDAO();
        List<Disco> discos = discoDAO.findAll();

        return Response.status(200).entity(discos).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDiscoId(@PathParam("id") int id) throws SQLException {
        DiscosDAO discoDAO = new DiscosDAO();
        Disco disco = discoDAO.findById(id);

        if (disco == null) {
            return Response.status(404).build();
        }

        return Response.status(200).entity(disco).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public Response eliminarDisco(@PathParam("id") int id) throws SQLException {
        DiscosDAO discoDAO = new DiscosDAO();
        Disco disco = discoDAO.findById(id);

        if (disco == null) {
            return Response.status(404)
                .entity("Disco no corresponde a ninguna existencia")
                .header("Access-Control-Allow-Origin", "*")
                .build();
        }

        try {
            discoDAO.delete(id);
            return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Disco eliminado exitosamente")
                .build();
        
        } catch (SQLException e) {
            return Response.status(500)
                .entity("Error al eliminar el disco")
                .build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearArtista(Disco disco) {
        if (disco == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El objeto Artista es nulo")
                    .build();
        }

        DiscosDAO discosDAO = new DiscosDAO();
        
        try {
        	discosDAO.insert(disco);
            return Response.status(Response.Status.CREATED)
                    .entity(disco)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al insertar el artista: " + e.getMessage())
                    .build();
        }
    }
    
}

