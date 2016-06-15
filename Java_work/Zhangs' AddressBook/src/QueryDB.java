import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryDB {
	private String[][] MyDBdata = new String[1000][15]; 
	private int i = 0;
	
	public QueryDB(){
		String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";// �������ݿ�������(�����΢���������)
		String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ZhangsAddressBook";// ���ݿ���������URL,����Ӧ���������ס�
		String user="sa";// �������ݿ��½�û���
		String password="sql2008";// ������Ӧ�û��ĵ�½����
		try{
			Class.forName(JDriver);// ������������ ����̬�������ݿ������
			Connection conn=DriverManager.getConnection(url, user, password);// ��ȡ���ݿ����� 
			
			String sql="SELECT * FROM AddressTable";// ����SQL��� 
			Statement stmt=conn.createStatement();// ִ��SQL��䣬��SQL����󶨵���Ӧ��������
			ResultSet rs=stmt.executeQuery(sql);//�����
			
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

