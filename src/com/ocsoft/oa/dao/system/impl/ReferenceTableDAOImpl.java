package com.ocsoft.oa.dao.system.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.system.IReferenceTableDAO;
import com.ocsoft.oa.vo.system.ReferenceTable;
import com.ocsoft.oa.vo.system.TypeCode;
@Repository("refTableDAO")
public class ReferenceTableDAOImpl extends BaseDaoImpl<ReferenceTable, Long>
		implements IReferenceTableDAO
{

	private static final Log logger = LogFactory.getLog(ReferenceTableDAOImpl.class);
	@Override
	public ReferenceTable getByKey(String key, Long execludeId)
	{
		Criteria criteria = getSession().createCriteria(this.entityClass.getName())
				.add(Restrictions.eq("refKey", key));
		
		if(execludeId!=null)
		{
			criteria.add(Restrictions.ne("refId", execludeId));
		}
		List<ReferenceTable> list = criteria.list();
		if(list != null && list.size()>0)
		{
			return list.iterator().next();
		}
		return null;
	}
	
	public Collection getRefList(String key)
	{
		ReferenceTable refTable = getByKey(key,null);
		String tableName = refTable.getRefTable();
		String textField = refTable.getTextField();
		String valueField = refTable.getValueField();
		
		StringBuffer sql = new StringBuffer("select ");
		sql.append(valueField).append(" as valueField, ");
		sql.append(textField).append(" as textField ");
		sql.append(" from ").append(tableName);
		sql.append(" order by  ").append(textField);
		logger.info("Query"+sql.toString());
		Query query = getSession().createSQLQuery(sql.toString());
		query.setResultTransformer(new TypeCodeResultTransformer());
		return query.list();
	}
	
	public class TypeCodeResultTransformer implements ResultTransformer
	{

		@Override
		public Object transformTuple(Object[] tuple, String[] aliases)
		{
			logger.info("transformTuple 1");
			TypeCode typeCode = new TypeCode();
			int len = aliases.length;
			for(int i=0; i<len;i++)
			{
				String alias = aliases[i];
				if("valueField".equals(alias))
				{
					typeCode.setCodeKey(tuple[i]+"");
				}
				else if("textField".equals(alias))
				{
					typeCode.setDescription(tuple[i]+"");
				}
			}
			return typeCode;
		}

		@Override
		public List transformList(List collection)
		{
			logger.info("transformTuple 2");
			return collection;
		}
		
	}
	
	

}
