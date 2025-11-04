package br.com.fiap.loja.resource;

import br.com.fiap.dto.doce.CadastroDoceDto;
import br.com.fiap.dto.doce.DetalhesDoceDto;
import br.com.fiap.exception.EntidadeNaoEncontrada;
import br.com.fiap.loja.dao.DoceDAO;
import br.com.fiap.loja.model.Doce;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.modelmapper.ModelMapper;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;


@Path("/doces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoceResource {

    @Inject
    private DoceDAO doceDAO;

    @Inject
    private ModelMapper mapper;

    @GET
    @Path("/pesquisa")
    public List<DetalhesDoceDto> buscar(@QueryParam("valor") double valor) throws SQLException{
        return  doceDAO.buscarPorPrecoMenor(valor).stream().map(
                d -> mapper.map(d, DetalhesDoceDto.class)
        ).toList();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int codigo) throws SQLException, EntidadeNaoEncontrada{
        doceDAO.remover(codigo);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int codigo, Doce doce) throws SQLException, EntidadeNaoEncontrada{
        doce.setCodigo(codigo);
        doceDAO.atualizar(doce);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") int codigo) throws SQLException, EntidadeNaoEncontrada {
        Doce doce = doceDAO.buscar(codigo);
        return Response.ok(doce).build();

    }

    @GET
    public List<Doce> listar() throws SQLException{
        return doceDAO.listar();
    }

    @POST
    public Response criar (@Valid CadastroDoceDto dto, @Context UriInfo uriInfo) throws SQLException {
        //Cria um doce com base nos dados do DTO
        Doce doce = mapper.map(dto, Doce.class);

        doceDAO.cadastrar(doce);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(doce.getCodigo())).build();

        return Response.created(uri).entity(mapper.map(doce, DetalhesDoceDto.class)).build();
        //Retornar o status 200
    }
}
