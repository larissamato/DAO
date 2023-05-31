package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.entities.Disciplina;

public class DisciplinaDAOImp implements DisciplinaDAO {
	private Connection conexao;
	
	public DisciplinaDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Disciplina obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			String sql = "INSERT INTO disciplina (nomedisciplina, cargahoraria) VALUES (?, ?)";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNomedisciplina());
			pst.setInt(2, obj.getCargahoraria());
			int linhas = pst.executeUpdate();
			
			if (linhas > 0) {
				rs = pst.getGeneratedKeys();
				rs.next();
				obj.setIdDisciplina(linhas);
				System.out.println(obj.toString() + " foi criado com sucesso !");
			}
			else {
				System.out.println("Nao foi possivel cadastrar o disciplina!");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Disciplina obj) {
	    PreparedStatement pst = null;
	    
	    try {
	        String sql = "UPDATE disciplina SET nomedisciplina = ?, cargahoraria = ? WHERE iddisciplina = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setString(1, obj.getNomedisciplina());
	        pst.setInt(2, obj.getCargahoraria());
	        pst.setInt(3, obj.getIdDisciplina());
	        
	        int linhas = pst.executeUpdate();
	        
	        if (linhas > 0) {
	            System.out.println(obj.toString() + " foi atualizado com sucesso !");
	        }
	        else {
	            System.out.println("Não foi possível atualizar a disciplina !");
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
	        String sql = "DELETE FROM disciplina WHERE iddisciplina = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setInt(1, id);
	        
	        int linhas = pst.executeUpdate();
	        
	        if (linhas > 0) {
	            System.out.println("Disciplina com ID " + id + " foi deletada com sucesso !");
	        }
	        else {
	            System.out.println("Não foi possível deletar a disciplina com ID " + id);
	        }
	        
	    }
	    catch(SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public Disciplina findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Disciplina> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
