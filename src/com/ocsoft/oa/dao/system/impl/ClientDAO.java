package com.ocsoft.oa.dao.system.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ocsoft.core.dao.impl.BaseDaoImpl;
import com.ocsoft.oa.dao.system.IClientDAO;
import com.ocsoft.oa.vo.system.Client;
@Repository("clientDAO")
public class ClientDAO extends BaseDaoImpl<Client, Long> implements IClientDAO
{


	

}
