package com.example.PostawIWygraj.service;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.example.PostawIWygraj.model.ChartOrder;

@Service
public class ChartOrderServiceImpl implements ChartOrderService {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<ChartOrder> getChartOrderLasWeek() {
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DATE, - 30);   
	cal.set(Calendar.HOUR, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);

	return em.createQuery("select createDate , count(*)  from Order where createDate between :date and CURDATE() group by createDate")
		.setParameter("date",cal.getTime()).getResultList();
    }

}
