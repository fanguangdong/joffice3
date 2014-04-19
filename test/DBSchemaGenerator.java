import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.ocsoft.oa.BaseTest;


public class DBSchemaGenerator extends BaseTest
{
	LocalSessionFactoryBean localSessionFactoryBean;
	@Before
	public void setUp()
	{
		super.setUp();
		localSessionFactoryBean = (LocalSessionFactoryBean)ctx.getBean("&sessionFactory");
	}
	@Test
	public void generate()
	{
        SchemaExport dbExport;
       // LocalSessionFactoryBean localSessionFactoryBean = (LocalSessionFactoryBean)sessionFactory;
        try {
            dbExport = new SchemaExport(localSessionFactoryBean.getConfiguration());
            // dbExport.setOutputFile(path);
            dbExport.create(true, false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
