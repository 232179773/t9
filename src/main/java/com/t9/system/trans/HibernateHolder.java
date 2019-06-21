package com.t9.system.trans;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import com.t9.system.web.RequestContext;

public class HibernateHolder {
	
	
	/**
	 * log
	 */
	private final Logger log = LoggerFactory.getLogger(HibernateHolder.class);
	/**
	 * 可选的hibernate模板
	 */
	private Map<String, HibernateTemplate> hibernateTemplates = new ConcurrentHashMap<String, HibernateTemplate>();
	
	
	public Map<String, HibernateTemplate> getHibernateTemplates() {
		return hibernateTemplates;
	}


	public void setHibernateTemplates(
			Map<String, HibernateTemplate> hibernateTemplates) {
		this.hibernateTemplates = hibernateTemplates;
	}


	public HibernateTemplate getDefatultHibernateTemplate() {
		return defatultHibernateTemplate;
	}


	public void setDefatultHibernateTemplate(
			HibernateTemplate defatultHibernateTemplate) {
		this.defatultHibernateTemplate = defatultHibernateTemplate;
	}


	/**
	 * 默认的hibernate模板
	 */
	private HibernateTemplate defatultHibernateTemplate;
	public HibernateTemplate getHibernateTemplate() {

		//返回默认的defatultHibernateTemplate
		HibernateTemplate findHibernateTemplate = defatultHibernateTemplate;
		
		return findHibernateTemplate;
	}
}
