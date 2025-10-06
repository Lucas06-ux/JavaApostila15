package br.com.fiap.loja.resource;

import br.com.fiap.loja.dao.DoceDAO;
import br.com.fiap.loja.model.Doce;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;


@Path("/doces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoceResource {

    @Inject
    private DoceDAO doceDAO;

    @POST
    public Response criar (Doce doce) throws SQLException {
        //Cadastrar
        doceDAO.cadastrar(doce);
        return Response.ok().build();
        //Retornar o status 200
    }
}
