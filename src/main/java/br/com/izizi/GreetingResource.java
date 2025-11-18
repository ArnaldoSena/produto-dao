package br.com.izizi;

import java.util.List;
import java.util.Objects;

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
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;


@Path("/products")
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
        Product product = Product.findById(id);
        if (product == null) {
            throw new NotFoundException("Product not found with id " + id);
        }
        return product;
    }

    @GET
    @Path("readall")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> listProducts(){
        List<Product> products = Product.listAll();
        if(Objects.isNull(products)){
            throw new NotFoundException("");
        }
        if(products.isEmpty()){
            throw new BadRequestException("No products found");
        }
        return products;
    }

    @POST
    @Path("create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Void saveProduct(Product produto)throws NotFoundException{
        Product.persist(produto);
        return null;
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public boolean deleteProduct(@PathParam("id") long id){
        boolean deleted = Product.deleteById(id);
        return deleted;
    }

    @PUT
    @Path("update/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Void updateProduct(@PathParam("id") long id, Product produto){
        Product existingProduct = Product.findById(id);
        if (existingProduct != null) {
            existingProduct.name = produto.name;
            existingProduct.value = produto.value;
            Product.persist(existingProduct);
        }
        return null;
    }

    @PATCH
    @Path("patch/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Void patchProduct(@PathParam("id") long id, Product produto){
        Product existingProduct = Product.findById(id);
        if (existingProduct != null) {
            if (produto.name != null) {
                existingProduct.name = produto.name;
            }
            if (produto.value != null) {
                existingProduct.value = produto.value;
            }
            Product.persist(existingProduct);
        } else {
            throw new NotFoundException("Product not found with id " + id);
        }
        return null;
    }
    
}