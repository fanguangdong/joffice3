package com.ocsoft.oa.dao.flow.impl;

import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.flow.ICustomTableDAO;
import com.ocsoft.oa.vo.form.CustomTable;
@Repository("customTableDAO")
public class CustomTableDAOImpl extends BaseDaoImpl<CustomTable, Long> implements ICustomTableDAO
{

	

}
