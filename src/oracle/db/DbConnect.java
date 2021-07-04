package oracle.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
	String oracleDriver="oracle.jdbc.driver.OracleDriver";
	String oracleUrl="jdbc:oracle:thin:@127.0.0.1:1521:xe";

	public DbConnect() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(oracleDriver);
			//System.out.println("오라클 드라이버 검색 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("오라클 드라이버 검색 실패:"+e.getMessage());
		}
	}

	//db server 연결하는 메서드
	public Connection getConnection()
	{
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(oracleUrl, "angel", "a1234");
			//System.out.println("오라클 서버 연결 성공");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패:"+e.getMessage());
		}
		return conn;
	}

	//db close
	public void dbClose(Statement stmt,Connection conn)
	{
		try {
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {}
	}

	public void dbClose(ResultSet rs,Statement stmt,Connection conn)
	{
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {}
	}
	
	public void dbClose(PreparedStatement pstmt,Connection conn)
	{
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {}
	}

	public void dbClose(ResultSet rs,PreparedStatement pstmt,Connection conn)
	{
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {}
	}

	public void dbClose(CallableStatement cstmt,Connection conn)
	{
		try {
			if(cstmt!=null) cstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {}
	}

	public void dbClose(ResultSet rs,CallableStatement cstmt,Connection conn)
	{
		try {
			if(rs!=null) rs.close();
			if(cstmt!=null) cstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {}
	}

}
