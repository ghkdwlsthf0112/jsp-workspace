package chap08.daao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chap08.dto.EmployeeDTO;

public class EmployeeDAO {
	
	public List<EmployeeDTO> search(String type, String query) {
		String sql = "SELECT employee_id, first_name, last_name, salary, "
				+ "hire_date, department_name FROM employees e, departments d "
				+ "WHERE e.department_id = d.department_id AND lower(" + type + ") LIKE ?";
		List<EmployeeDTO> emps = new ArrayList<>();
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, "%" + query.toLowerCase() + "%");
			
			try(ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					emps.add(new EmployeeDTO(
							rs.getInt("employee_id"),
							rs.getString("first_name"),
							rs.getString("last_name"),
							rs.getDouble("salary"),
							rs.getDate("hire_date"),
							rs.getString("department_id")));
				}
			}
			return emps;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;	
		}
	}

}
