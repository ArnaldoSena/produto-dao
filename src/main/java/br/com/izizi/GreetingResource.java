package br.com.izizi;

import java.util.List;

import br.com.izizi.domain.Product;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("read/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product readProduct(@PathParam("id") long id){
        return Product.findById(id);
    }

    @GET
    @Path("readall")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> listProducts(){
        return Product.listAll();
    }

    @POST
    @Path("create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void saveProduct(Product produto){
        Product.persist(produto);
    }

    @DELETE
    @Path("delete/{id}")
    @Transactional
    public void deleteProduct(@PathParam("id") long id){
        Product.deleteById(id);
    }

    @PUT
    @Path("update/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updateProduct(@PathParam("id") long id, Product produto){
        Product existingProduct = Product.findById(id);
        if (existingProduct != null) {
            existingProduct.name = produto.name;
            existingProduct.value = produto.value;
            Product.persist(existingProduct);
        }
    }

    @PATCH
    @Path("patch/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void patchProduct(@PathParam("id") long id, Product produto){
        Product existingProduct = Product.findById(id);
        if (existingProduct != null) {
            if (produto.name != null) {
                existingProduct.name = produto.name;
            }
            if (produto.value != null) {
                existingProduct.value = produto.value;
            }
            Product.persist(existingProduct);
        }
    }
}