import java.util.Date;


public class Test
{

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException
	{
		Date date1 = new Date();
		Date date2 = null;
		for(int i=0;i<10;i++)
		{
			Thread.sleep(3000);
			date2 = new Date();
			System.out.println(date1.getTime() - date2.getTime());
		}

	}

}
