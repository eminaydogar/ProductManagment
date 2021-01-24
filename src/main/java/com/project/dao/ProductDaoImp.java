
package com.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.project.model.Product;

/*
 * 
 * @Author:emin.aydogar
 * 
 */

@Transactional
@Repository
public class ProductDaoImp implements ProductDao {

	private static Logger log = LoggerFactory.getLogger(ProductDaoImp.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Value("${entity.product}")
	private String product;
	private Map<String, String> map = new HashMap<String, String>();

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	Session CurrentSession() {
		log.info("Session called.");
		return this.sessionFactory.getCurrentSession();
	}

	public void closeSession() {
		CurrentSession().close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		// map.put("status", "Y");
		String naviteQuery = createNaviteQuery(product, map);
		Query<Product> query = CurrentSession().createQuery(naviteQuery);
		List<Product> productList = query.list();
		return productList;
	}

	@Override
	public Product getProduct(long id) {
		Product product = (Product) CurrentSession().get(Product.class, id);
		return product;
	}

	@Override
	public Product addProduct(Product product) {
		validationControl(product);
		CurrentSession().save(product);
		return product;
	}

	@Override
	public Product updateProduct(Product product) {
		validationControl(product);
		CurrentSession().saveOrUpdate(product);
		CurrentSession().flush();
		return product;

	}

	@Override
	public void deleteProduct(long id) {
		Product product = (Product) CurrentSession().load(Product.class, new Long(id));
		validationControl(product);
		CurrentSession().delete(product);

	}

	// create generic query
	private String createNaviteQuery(String entity, Map<String, String> map) {
		int mapSize = 0;
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("from " + entity);
		if (map.size() > 0 && map != null) {
			sql.append(" where ");
			for (String key : map.keySet()) {
				mapSize++;
				sql.append(key + " = '" + map.get(key) + "'");
				if (mapSize == map.size()) {
					break;
				}
				sql.append(" and ");

			}
		}
		log.info("createNaviteQuery called.Query:" + sql.toString());
		return sql.toString();
	}

	// entity control.
	private void validationControl(Product product) {
		if (product == null) {
			throw new NullPointerException("Entity was null " + product);
		}

		else if (product.getId() == null || product.getCategory() == null || product.getCategoryType() == null
				|| product.getName() == null || product.getStatus() == null || product.getCount() == null
				|| product.getPrice() == null) {
			throw new IllegalArgumentException("Entity was not valid." + product);
		}

	}

}
