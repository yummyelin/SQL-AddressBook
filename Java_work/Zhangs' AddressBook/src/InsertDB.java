import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertDB {
	private String[] MyDBdata = new String[15]; 
	
	public InsertDB(String[] data){
		String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";// �������ݿ�������(�����΢���������)
		String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ZhangsAddressBook";// ���ݿ���������URL,����Ӧ���������ס�
		String user="sa";// �������ݿ��½�û���
		String password="sql2008";// ������Ӧ�û��ĵ�½����
		MyDBdata = data;
		try{
			Class.forName(JDriver);// ������������ ����̬�������ݿ������
			Connection conn=DriverManager.getConnection(url, user, password);// ��ȡ���ݿ����� 
			String sql = "INSERT INTO AddressTable ([First Name],[Last Name],Gender,[Address Line1],[Address Line2],[Address Line3],City,State,[Zip Code],Country,Phone,[Phone(home)],[Phone(other)],Email,Label) VALUES ('"+MyDBdata[0]+"','"+MyDBdata[1]+"','"+MyDBdata[2]+"','"+MyDBdata[3]+"','"+MyDBdata[4]+"','"+MyDBdata[5]+"','"+MyDBdata[6]+"','"+MyDBdata[7]+"','"+MyDBdata[8]+"','"+MyDBdata[9]+"','"+MyDBdata[10]+"','"+MyDBdata[11]+"','"+MyDBdata[12]+"','"+MyDBdata[13]+"','"+MyDBdata[14]+"')";  
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);  
            System.out.println("Insert successfully!");
			
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
