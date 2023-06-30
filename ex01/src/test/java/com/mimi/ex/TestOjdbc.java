package com.mimi.ex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class TestOjdbc {
	
	@Test
	public void CalcTest() {
		Calc calc = new Calc();
		int res = calc.add(10, 20);
		System.out.println("10 + 20 = " + res);
		
		// 			예상 결과값, 실제 결과값
		// assertEquals(30, res);
		assertEquals(300, res);
	}
	
	
	@Test
	public void ojdbcTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "jsp", "1234");
			
			ResultSet rs = conn.createStatement().executeQuery("select to_char(sysdate,'yyyy-mm-dd')|| '입니다옹' from dual");
			rs.next();
			System.out.println(rs.getString(1));
			System.out.println(conn);
			
			assertNotNull(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
