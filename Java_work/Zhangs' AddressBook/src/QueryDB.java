import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryDB {
	private String[][] MyDBdata = new String[1000][15]; 
	private int i = 0;
	
	public QueryDB(){
		String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";// 声明数据库驱动名(这个是微软的驱动名)
		String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ZhangsAddressBook";// 数据库驱动程序URL,和相应的驱动配套。
		String user="sa";// 定义数据库登陆用户名
		String password="sql2008";// 定义相应用户的登陆密码
		try{
			Class.forName(JDriver);// 加载驱动程序 ，动态导入数据库的驱动
			Connection conn=DriverManager.getConnection(url, user, password);// 获取数据库链接 
			
			String sql="SELECT * FROM AddressTable";// 创造SQL语句 
			Statement stmt=conn.createStatement();// 执行SQL语句，将SQL命令绑定到相应的连接上
			ResultSet rs=stmt.executeQuery(sql);//结果集
			
			while(rs.next()){
				for(int j=0;j<15;j++){
					MyDBdata[i][j]=rs.getString(j+1);
				}
				i++;
			}
			
//			for(int a=0;a<2;a++){
//				for(int b=0;b<15;b++){
//					System.out.println(MyDBdata[a][b]);
//				}
//			}
			
			
			System.out.println("Connecting SQL successfully!");
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String[][] getDBdata(){
		return MyDBdata;
	}
	
	public int getDBRowCount(){
		return i;
	}
}

