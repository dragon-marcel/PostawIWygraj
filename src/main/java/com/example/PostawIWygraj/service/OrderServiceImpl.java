package com.example.PostawIWygraj.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PostawIWygraj.model.Order;
import com.example.PostawIWygraj.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
	return orderRepository.findAll();
    }

    @Override
    public void save(Order order) {
	String nrOrder = getNewNrOrder();
	order.setCreateDate(new Date());
	order.setNumber(nrOrder);
	orderRepository.save(order);

    }

    @Override
    public void editOrder(Order order) {
	orderRepository.save(order);

    }

    @Override
    public Order findById(Long id) {
	return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> findAllByIdUser(Long id) {
	return em.createQuery("from Order where user_id = ?1").setParameter(1, id).getResultList();
    }

    @Override
    public String getNewNrOrder() {

	StringBuilder sqlSB = new StringBuilder();
	sqlSB.append(
		"select concat(concat(COALESCE(cast(max(SUBSTRING(o.number,LOCATE(o.number,'/',2),LENGTH(o.number)-8)) as int) + 1,'1') ,'/ZAM/'),year(current_date()))  AS NUMER ")
		.append("FROM ").append(" Order o ").append("WHERE o.number <> '' ")
		.append("AND o.number LIKE  concat('%',year(current_date())) ");

	String numer = (String) em.createQuery(sqlSB.toString()).getSingleResult();

	return numer;

    }

    @Override
    public List<Order> getOrdersRaport(String start, String end, Long idUser) {
	
	StringBuilder hqlQuerry = new StringBuilder();
	hqlQuerry.append("from Order where createDate >= cast (:start as date) and createDate <= cast (:end as date) ");
	if (!idUser.equals(0L) && idUser != null) {
	    hqlQuerry.append("and :idUser = user_id ");
	}
	Query query = em.createQuery(hqlQuerry.toString());
	query.setParameter("start", start).setParameter("end", end);
	if (!idUser.equals(0L) && idUser != null) {
	    query.setParameter("idUser", idUser);

	}
	List<Order> orders = query.getResultList();
	return orders;
    }

}
