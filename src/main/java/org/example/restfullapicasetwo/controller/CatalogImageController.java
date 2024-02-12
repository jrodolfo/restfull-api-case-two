package org.example.restfullapicasetwo.controller;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.example.restfullapicasetwo.exception.CatalogImageDoesNotExistException;
import org.example.restfullapicasetwo.model.CatalogImage;
import org.example.restfullapicasetwo.model.CatalogMetaDatum;
import org.example.restfullapicasetwo.util.ImageTestGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Component
@Path("/images")
@Produces("application/json")
public class CatalogImageController {

    @GET
    @Path("/{image-id}")
    public CatalogImage getImage(@PathParam("image-id") String imageId) {
        return ImageTestGenerator.generateTestImage(imageId);
    }

    @GET
    public List<CatalogImage> getImages(@QueryParam("meta-data") List<String> imageMetadata) {

        List<CatalogMetaDatum> metaData = imageMetadata.stream().map(c -> {
            String[] nameValue = c.split("=");
            CatalogMetaDatum item = new CatalogMetaDatum();
            item.setName(nameValue[0]);
            item.setValue(nameValue[1]);
            return item;
        }).collect(Collectors.toList());

        metaData.forEach(c -> System.out.println(c.toString()));

        return ImageTestGenerator.generateTestImages();
    }

    @POST
    public Response createImage(CatalogImage image) {
        System.out.println(image.toString());
        int imageId = (new Random()).nextInt();
        String imageURL = "http://www.nowhere.com/image-" + imageId + ".jpg";
        image.setImagePath(imageURL);
        Response.ResponseBuilder responseBuilder = Response.ok();
        String message = "{\"CatalogImage " + imageId + " created successfully.\"}";

        return responseBuilder.entity(message).build();
    }

    /**
     * The throw-away code simply takes a CatalogImage, prints it, and then changes the image’s
     * path so we can ensure the path was changed by reviewing the response. Remember, PUT overwrites
     * a resource, not update, but suspend disbelief and pretend our code replaces a CatalogImage
     * and then returns that CatalogImage, i.e. this implementation is hypothetical, a placeholder.
     * In reality, it would replace the CatalogImage in an application’s data store with the
     * CatalogImage passed as a parameter.
     *
     * @param imageId
     * @param image
     * @return
     */
    @PUT
    @Path("/{image-id}")
    public CatalogImage replaceImage(@PathParam("image-id") String imageId, CatalogImage image) {
        System.out.println(image.toString());
        image.setImagePath("http://www.nowhere.com/changed-path.jpg");
        return image;
    }

    @PATCH
    @Path("/{image-id}")
    public CatalogImage updateImage(@PathParam("image-id") String imageId, CatalogImage image) {
        System.out.println(image.toString());
        image.setImageId("http://www.nowhere.com/patch-path.jpg");
        if (image != null && image.getMetaData() != null && !image.getMetaData().isEmpty()) {
            CatalogMetaDatum val = image.getMetaData().get(0);
            val.setName("patch");
            val.setValue("patch-value");
            List<CatalogMetaDatum> theList = new ArrayList<>();
            theList.add(val);
            image.setMetaData(theList);
        }
        return image;
    }

    @DELETE
    @Path("/{image-id}")
    public Response deleteImage(@PathParam("image-id") String imageId) throws CatalogImageDoesNotExistException {

        if ((new Random()).nextBoolean()) {
            throw new CatalogImageDoesNotExistException();
        }

        Response.ResponseBuilder responseBuilder = Response.ok();
        String message = "{\"CatalogImage " + imageId + " deleted successfully.\"}";

        return responseBuilder.entity(message).build();
    }
}
