import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteDB {
	private String[] MyDBdata = new String[15]; 
	
	public DeleteDB(String[] data){
		String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";// �������ݿ�������(�����΢���������)
		String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ZhangsAddressBook";// ���ݿ���������URL,����Ӧ���������ס�
		String user="sa";// �������ݿ��½�û���
		String password="sql2008";// ������Ӧ�û��ĵ�½����
		MyDBdata = data;
		try{
			Class.forName(JDriver);// ������������ ����̬�������ݿ������
			Connection conn=DriverManager.getConnection(url, user, password);// ��ȡ���ݿ����� 
			
			String sql = "DELETE FROM AddressTable WHERE [First Name]='"+MyDBdata[0]+"'";// ����SQL���   
            Statement stmt = conn.createStatement();// ִ��SQL���  
            stmt.executeUpdate(sql);  
            System.out.println("Delete successfully!");
			
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
