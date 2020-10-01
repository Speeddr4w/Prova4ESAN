package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;

public class FuncionarioDAO {

	private Connection connection;
	
	
	
	public FuncionarioDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public void cadastrar(Funcionario funcionario) {
		
		String sql = "insert into funcionario (nome, data_nascimento, salario) values (?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, funcionario.getNome());
			statement.setDate(2, funcionario.getDateDataNascimento());
			statement.setFloat(3, funcionario.getSalario());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public void atualizar(Funcionario funcionario) {
		
		String sql = "update funcionario set nome=?, data_nascimento=?, salario=? where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, funcionario.getNome());
			statement.setDate(2, funcionario.getDateDataNascimento());
			statement.setFloat(3, funcionario.getSalario());
			statement.setInt(4, funcionario.getId());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public void excluir(int id) {
		
		String sql = "delete from funcionario where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public int NumeroDeRegistros() {
		int num = 0;
		String sql = "select count(*) as num from funcionario";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				
			num =  resultSet.getInt("num");
			
			}
			
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return num;
		
	}
	
	public Funcionario pesquisar(int id) {
		Funcionario funcionario = new Funcionario();
		
		String sql = "select * from funcionario where id = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				int idaux = resultSet.getInt(1);
				String nomeaux = resultSet.getString(2);
				LocalDate dateaux = resultSet.getDate(3).toLocalDate();
				Float salarioaux = resultSet.getFloat(4);
				
				funcionario.setId(idaux);
				funcionario.setNome(nomeaux);
				funcionario.setDataNascimento(dateaux);
				funcionario.setSalario(salarioaux);
				
			}
			
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return funcionario;
		
	}

}
