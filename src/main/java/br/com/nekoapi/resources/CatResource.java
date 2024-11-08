/*
 * @author Marina Yumi Kanadani - RM 558404 - 1TDSPX
 */

package br.com.nekoapi.resources;

import br.com.nekoapi.model.vo.CatVO;
import br.com.nekoapi.model.bo.CatBO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatResource {

    private final CatBO catBO = new CatBO();

    // Endpoint para obter todas as raças de gatos (GET /cats)
    @GET
    public Response getAllCats() {
        try {
            List<CatVO> cats = catBO.readAllCats();
            return Response.ok(cats).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao buscar raças de gatos. " + e.getMessage())
                           .build();
        }
    }

    // Endpoint para buscar uma raça específica pelo ID (GET /cats/{id})
    @GET
    @Path("/{id}")
    public Response getCatById(@PathParam("id") String id) {
    	CatVO cat = catBO.readCatById(id);
        if (cat != null) {
            return Response.ok(cat).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Raça de gato não encontrada para o ID: " + id)
                           .build();
        }
    }

    // Endpoint para criar uma nova raça de gato (POST /cats)
    @POST
    public Response createCat(CatVO cat) {
        try {
            boolean created = catBO.createCat(cat);
            if (created) {
                return Response.status(Response.Status.CREATED).entity("Raça de gato criada com sucesso!").build();
            } else {
                return Response.status(Response.Status.CONFLICT)
                               .entity("Raça de gato com ID já existente.")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao criar a raça de gato. " + e.getMessage())
                           .build();
        }
    }

    // Endpoint para atualizar uma raça de gato existente (PUT /cats/{id})
    @PUT
    @Path("/{id}")
    public Response updateCat(@PathParam("id") String id, CatVO updatedCat) {
        try {
            boolean updated = catBO.updateCat(id, updatedCat);
            if (updated) {
                return Response.ok("Raça de gato atualizada com sucesso!").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Raça de gato não encontrada para o ID: " + id)
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao atualizar a raça de gato. " + e.getMessage())
                           .build();
        }
    }

    // Endpoint para deletar uma raça de gato pelo ID (DELETE /cats/{id})
    @DELETE
    @Path("/{id}")
    public Response deleteCat(@PathParam("id") String id) {
        try {
            boolean deleted = catBO.deleteCat(id);
            if (deleted) {
                return Response.ok("Raça de gato deletada com sucesso!").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Raça de gato não encontrada para o ID: " + id)
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao deletar a raça de gato. " + e.getMessage())
                           .build();
        }
    }
}
