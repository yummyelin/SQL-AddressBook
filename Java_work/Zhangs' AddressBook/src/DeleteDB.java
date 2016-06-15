import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteDB {
	private String[] MyDBdata = new String[15]; 
	
	public DeleteDB(String[] data){
		String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";// 声明数据库驱动名(这个是微软的驱动名)
		String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ZhangsAddressBook";// 数据库驱动程序URL,和相应的驱动配套。
		String user="sa";// 定义数据库登陆用户名
		String password="sql2008";// 定义相应用户的登陆密码
		MyDBdata = data;
		try{
			Class.forName(JDriver);// 加载驱动程序 ，动态导入数据库的驱动
			Connection conn=DriverManager.getConnection(url, user, password);// 获取数据库链接 
			
			String sql = "DELETE FROM AddressTable WHERE [First Name]='"+MyDBdata[0]+"'";// 创造SQL语句   
            Statement stmt = conn.createStatement();// 执行SQL语句  
            stmt.executeUpdate(sql);  
            System.out.println("Delete successfully!");
			
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
