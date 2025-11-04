package br.com.fiap.mapper;

import br.com.fiap.dto.exception.ErroResponse;
import br.com.fiap.exception.EntidadeNaoEncontrada;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.RestResponse;

@Provider
public abstract class ApiException implements ExceptionMapper <Exception> {
    @Override
    public Response toResponse(Exception e) {
        if( e instanceof EntidadeNaoEncontrada){
            return Response.status(RestResponse.Status.NOT_FOUND)
                    .entity(new ErroResponse("Entidade não encontrada", e.getMessage())).build();
        }
        return Response.serverError().entity(new ErroResponse("Deu ruim", e.getMessage())).build();
    }
}
