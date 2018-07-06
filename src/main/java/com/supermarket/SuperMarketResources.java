package com.supermarket;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * This Class contains all Resources for Super Market Application.
 * SuperMarketResource  class hosted at the URI path "supermarket".
 * @author MukulJaiswal
 *
 */
@Path("supermarket")
public class SuperMarketResources {
	SuperMarketRepository superMarketRepository = new SuperMarketRepository();

	
	/**
	 *
	 * @return This function returns List of all Items in the supermarket.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Items> getItems() {
		System.out.println("Alien get called");
		return superMarketRepository.getItems();
	}
	
	/**
	 * This function is hosted at the URI path "item/{id}"
	 * @param id
	 * @return It returns the particular Item from the Store.
	 */
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Items> getItem(@PathParam("id") int id) {
		return superMarketRepository.getItem(id);
	}
	
	/**
	 * This function is hosted at the URI path "create"
	 * @param item
	 * @return Response
	 */
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addItems(Items item){
    	
        item.setName(item.getName());
        item.setQuantity(item.getQuantity());
        item.setDate(item.getDate());
        
                 
        superMarketRepository.create(item);
        
        return "Successfully Inserted";
    }
    
    /**
     * This function is hosted at the URI path "update/{id}"
     * @param id
     * @param item
     * @return
     */
	@PUT
    @Path("update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
    public String update(@PathParam("id") int id, Items item){
       
        int count = superMarketRepository.updateItem(id, item);
        if(count==0){
            return "Not a Successful Update.";
        }
        return "Updated Seccessfully!";
	}
	
	/**
	 * This function is hosted at the URI path "item/{id}"
	 * @param id
	 * @return
	 */
    @DELETE
    @Path("delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id") int id){
       
        int count = superMarketRepository.deleteItem(id);
        if(count==0){
            return "Bad Request";
        }
        return "ID: "+id +" is Successfully Deleted";
    }
    
    /**
     * This function is hosted at the URI path "/update"
     * @param item
     * @return
     */
    
    @PUT
    @Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
    public String updateBulk(Items item){
       
        int count = superMarketRepository.updateItem(item);
        if(count==0){
            return "Not a Successful Update.";
        }
        return "Bulk Update is Seccessful!";
	}
}




