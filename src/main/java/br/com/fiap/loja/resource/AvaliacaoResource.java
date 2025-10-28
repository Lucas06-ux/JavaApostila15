package br.com.fiap.loja.resource;


import br.com.fiap.dto.avaliacao.CadastroAvaliacaoDto;
import br.com.fiap.dto.avaliacao.DetalhesAvaliacaoDto;
import br.com.fiap.loja.dao.AvaliacaoDAO;
import br.com.fiap.loja.model.Avaliacao;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;

@Path("/doces/{codigoDoce}/avaliacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {
    @Inject
    private AvaliacaoDAO avaliacaoDAO;
    @Inject
    private ModelMapper mapper;

    @POST
    public Response criar (@PathParam("codigoDoce") int codigoDoce,
                       @Valid CadastroAvaliacaoDto dto, @Context UriInfo uriInfo) throws SQLException {

        Avaliacao avaliacao = mapper.map(dto, Avaliacao.class);
        avaliacao.setCodigoDoce(codigoDoce);

        avaliacaoDAO.cadastrar(avaliacao);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(avaliacao.getCodigo()));
        return Response.created(uriBuilder.build()).entity(mapper.map(avaliacao, DetalhesAvaliacaoDto.class)).build();

    }

    @GET
    public List<DetalhesAvaliacaoDto> listar(@PathParam("codigoDoce")int codigoDoce) throws SQLException{
       return avaliacaoDAO.buscarPorDoce(codigoDoce);
    }
}
