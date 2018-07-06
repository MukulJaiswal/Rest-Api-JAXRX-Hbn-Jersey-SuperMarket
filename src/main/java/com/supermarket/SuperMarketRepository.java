package com.supermarket;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * This is the Database Connectivity File for SuperMarket Application.
 * @author MukulJaiswal
 *
 */

public class SuperMarketRepository {
	
	ConfigureSetting configuresetting = new ConfigureSetting();
	
	/**
	 * This function is used to List all Items  in the Store.
	 * @return
	 */
	public List<Items> getItems() {
		Session session = configuresetting.database();

		session.beginTransaction();

		Query query = session.createQuery("FROM Items");

		List<Items> items = query.list();

		if (items.isEmpty()) {
			System.out.println("Sorry! No Items in the Store.");
		} else {
			for (Items item : items) {
				System.out.println(item);
			}
		}
		session.getTransaction().commit();
		session.close();

		return items;
	}

	/**
	 * This Function returns the particular item from Store by providing ID.
	 * @param id
	 * @return
	 */
	public List<Items> getItem(int id) {
		Session session = configuresetting.database();

		session.beginTransaction();

		Query query = session.createQuery("FROM Items WHERE id = :id");
		query.setParameter("id", id);

		@SuppressWarnings("unchecked")
		List<Items> items = query.list();

		session.getTransaction().commit();
		session.close();

		return items;

	}
    
	/**
	 * This function is used to Add new items in the store.
	 * @param alien
	 */
    public void create( Items alien){
    	
    	Session session = configuresetting.database();
    	
    	session.beginTransaction();
        
        alien.setName(alien.getName());
        alien.setQuantity(alien.getQuantity());
        alien.setDate(alien.getDate());
        
        session.save(alien);
        
        session.getTransaction().commit();
        session.close();
        
    }
    
    /**
     * This function is used to Update all Items in the Store.
     * @param id
     * @param item
     * @return
     */
	public int updateItem(int id, Items item) {
		
		if (id <= 0)
			return 0;
		Session session = configuresetting.database();
		session.beginTransaction();
		
		String hql = "update Items set name = :name, quantity=:quantity , date = :date where id = :id";
		Query query = session.createQuery(hql);
		
		query.setInteger("id", id);
		query.setString("name", item.getName());
		query.setInteger("quantity", item.getQuantity());
		query.setString("date", item.getDate());
		
		int rowCount = query.executeUpdate();
		
		System.out.println("Rows affected: " + rowCount);
		session.getTransaction().commit();
		session.close();
		
		return rowCount;
	}
	
	/**
	 * This function deletes the item from the Store.
	 * @param id
	 * @return
	 */
    public int deleteItem(int id) {
    	
    	Session session = configuresetting.database();
    	
        session.beginTransaction();
        
        String hql = "delete from Items where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        session.getTransaction().commit();
        
        return rowCount;
    }
    /**
     * This function performs the bulk update in the Database.
     * @param item
     * @return
     */
    
    public int updateItem(Items item) {
		
		Session session = configuresetting.database();
		session.beginTransaction();
		
		String hql = "update Items set quantity=:quantity , date = :date";
		Query query = session.createQuery(hql);
		
		query.setInteger("quantity", item.getQuantity());
		query.setString("date", item.getDate());	
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		
		session.getTransaction().commit();
		
		session.close();		
		return rowCount;
	}

}






