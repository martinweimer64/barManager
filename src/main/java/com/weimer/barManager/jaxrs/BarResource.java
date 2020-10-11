package com.weimer.barManager.jaxrs;

import com.weimer.barManager.ApiException;
import com.weimer.barManager.jpa.BarEntity;
import com.weimer.barManager.repository.BarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Path("/v1/bars")
public class BarResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(BarResource.class);

    @Inject
    BarRepository barRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BarEntity> list() {
        LOGGER.info("Bar list invoked");
        final List<BarEntity> bars = new ArrayList<>();
        barRepository.findAll().forEach(bars::add);
        LOGGER.info("list result: {}", bars);

        return bars;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public BarEntity getBarById(@PathParam("id") Integer id) {
        LOGGER.info("Bar by ID invoked, ID: {}", id);
        BarEntity bar = barRepository.findByCuit(id).orElseThrow(() -> {return new ApiException("ID NOT FOUND", id.toString());});
        LOGGER.info("Find Bar by id result: {}", bar);
        return bar;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BarEntity save(BarEntity barEntity) {
        LOGGER.info("Bar save invoked, params: {}", barEntity);
        try {
            final BarEntity bar = barEntity;
            barRepository.save(bar);
            LOGGER.info("list result: {}", bar);
            return bar;
        } catch (Exception e) {
            LOGGER.info("Bar not created ERROR: {}", e);
            throw new RuntimeException();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void deleteBar(@PathParam("id") Integer id) {
        LOGGER.info("Delete Bar invoked, ID: {}", id);
        BarEntity barEntity = barRepository.findByCuit(id).orElseThrow(() -> {return new ApiException("ID NOT FOUND", id.toString());});
        try {
            barRepository.delete(barEntity);
        } catch (final DataAccessException e) {
            LOGGER.error("Cannot Delete Bar, ERROR: {}", e);
            throw new RuntimeException();
        }
    }
}
