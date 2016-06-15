import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateDB {
	private String[] MyDBdata = new String[15];
	
	public UpdateDB(String[] data){
		String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";// 声明数据库驱动名(这个是微软的驱动名)
		String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ZhangsAddressBook";// 数据库驱动程序URL,和相应的驱动配套。
		String user="sa";// 定义数据库登陆用户名
		String password="sql2008";// 定义相应用户的登陆密码
		MyDBdata = data;
		try{
			Class.forName(JDriver);// 加载驱动程序 ，动态导入数据库的驱动
			Connection conn=DriverManager.getConnection(url, user, password);// 获取数据库链接 
			
			String sql = "UPDATE AddressTable SET [Last Name]='"+MyDBdata[1]+"', Gender='"+MyDBdata[2]+"',[Address Line1]='"+MyDBdata[3]+"',[Address Line2]='"+MyDBdata[4]+"',[Address Line3]='"+MyDBdata[5]+"',City='"+MyDBdata[6]+"',State='"+MyDBdata[7]+"',[Zip Code]='"+MyDBdata[8]+"',Country='"+MyDBdata[9]+"',Phone='"+MyDBdata[10]+"',[Phone(home)]='"+MyDBdata[11]+"',[Phone(other)]='"+MyDBdata[12]+"',Email='"+MyDBdata[13]+"',Label='"+MyDBdata[14]+"' WHERE [First Name]='"+MyDBdata[0]+"' "; 
//			String sql = "UPDATE AddressTable SET [Last Name]='"+MyDBdata[1]+"', Gender='"+MyDBdata[2]+"', [Address Line 1]='"+MyDBdata[3]+"' WHERE [First Name]='"+MyDBdata[0]+"' "; 

			Statement stmt = conn.createStatement();// 执行SQL语句  
            stmt.executeUpdate(sql);  
            System.out.println("Update successfully!");
            
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String[] getDBdata(){
		return MyDBdata;
	}
	
}
