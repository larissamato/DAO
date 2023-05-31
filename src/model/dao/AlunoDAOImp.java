package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.db.DB;
import model.entities.Aluno;

public class AlunoDAOImp implements AlunoDAO{
	private Connection conexao;
	
	public AlunoDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Aluno obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;

        try {
            String sql = "INSERT INTO aluno (nome, sexo, dt_nasc, nota) VALUES (?, ?, ?, ?)";
            pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getSexo());
            pst.setDate(3, obj.getDt_nasc());
            pst.setFloat(4, obj.getNota());
            int linhas = pst.executeUpdate();

            if (linhas > 0) {
		         rs = pst.getGeneratedKeys();
		         rs.next();
		         obj.setIdaluno(rs.getInt(1));
		         System.out.println(obj.toString() + " foi criado com sucesso !");
		     } else {
		          System.out.println("Não foi possível cadastrar o aluno !");
		     }

		  } catch (SQLException e) {
		       e.printStackTrace();
		  } finally {
		        DB.fechaResultSet(rs);
		        DB.fechaPreparedStatement(pst);
		  }
		
	}

	@Override
	public void update(Aluno obj) {
		PreparedStatement pst = null;
	    
	    try {
	        String sql = "UPDATE aluno SET nome = ?, sexo = ?, dt_nasc = ?, nota = ? WHERE idaluno = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setString(1, obj.getNome());
	        pst.setString(2, obj.getSexo());
            pst.setDate(3, obj.getDt_nasc());
            pst.setFloat(4, obj.getNota());
	        
	        int linhas = pst.executeUpdate();
	        
	        if (linhas > 0) {
	            System.out.println(obj.toString() + " foi atualizado com sucesso !");
	        }
	        else {
	            System.out.println("Não foi possível atualizar aluno!");
	        }
	        
	    }
	    catch(SQLException e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement pst = null;
	    
	    try {
	        String sql = "DELETE FROM aluno WHERE idaluno = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setInt(1, id);
	        
	        int linhas = pst.executeUpdate();
	        
	        if (linhas > 0) {
	            System.out.println("Aluno com ID " + id + " foi deletada com sucesso !");
	        }
	        else {
	            System.out.println("Não foi possível deletar a aluno com ID " + id);
	        }
	        
	    }
	    catch(SQLException e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public Aluno findById(Integer id) {
		
		return null;
	}

	@Override
	public List<Aluno> findAll() {
		
		return null;
	}

}
