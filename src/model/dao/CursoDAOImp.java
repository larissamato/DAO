package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.db.DB;
import model.entities.Curso;

public class CursoDAOImp implements CursoDAO {

	private Connection conexao;
	
	public CursoDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Curso obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			String sql = "INSERT INTO curso (nomecurso) VALUES (?)";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNomeCurso());
			int linhas = pst.executeUpdate();
			
			if (linhas > 0) {
				rs = pst.getGeneratedKeys();
				rs.next();
				obj.setIdcurso(rs.getInt(1));
				System.out.println(obj.toString() + " foi criado com sucesso !");
			}
			else {
				System.out.println("Nao foi possivel cadastrar o curso !");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Curso obj) {
		 PreparedStatement pst = null;
		    
		    try {
		        String sql = "UPDATE curso SET nomecurso = ? WHERE idcurso = ?";
		        pst = conexao.prepareStatement(sql);
		        pst.setString(1, obj.getNomeCurso());
		        pst.setInt(2, obj.getIdcurso());
		        
		        int linhas = pst.executeUpdate();
		        
		        if (linhas > 0) {
		            System.out.println(obj.toString() + " foi atualizado com sucesso !");
		        }
		        else {
		            System.out.println("Nao foi possivel atualizar o curso !");
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
	        String sql = "DELETE FROM curso WHERE idcurso = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setInt(1, id);
	        
	        int linhas = pst.executeUpdate();
	        
	        if (linhas > 0) {
	            System.out.println("Curso com ID " + id + " foi deletado com sucesso !");
	        }
	        else {
	            System.out.println("N�o foi poss�vel deletar o curso com ID " + id);
	        }
	        
	    }
	    catch(SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public Curso findById(Integer id) {
		PreparedStatement pst = null;
	    ResultSet rs = null;
	    Curso curso = null;
	    
	    try {
	        String sql = "SELECT * FROM curso WHERE idcurso = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setInt(1, id);
	        
	        rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            curso = new Curso();
	            curso.setIdcurso(rs.getInt("idcurso"));
	            curso.setNomeCurso(rs.getString("nomecurso"));
	        }
	        
	    }
	    catch(SQLException e) {
	        e.printStackTrace();
	    }
	    finally {
	    	DB.fechaPreparedStatement(pst);
	    	DB.fechaResultSet(rs);
	    }
		return curso;
	}

	@Override
	public List<Curso> findAll() {
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    List<Curso> cursos = new ArrayList<>();
	    
	    try {
	        String sql = "SELECT * FROM curso";
	        pst = conexao.prepareStatement(sql);
	        rs = pst.executeQuery(sql);
	        
	        while (rs.next()) {
	            Curso curso = new Curso();
	            curso.setIdcurso(rs.getInt("idcurso"));
	            curso.setNomeCurso(rs.getString("nomecurso"));
	            
	            cursos.add(curso);
	        }
	        
	    }
	    catch(SQLException e) {
	        e.printStackTrace();
	    }
	    finally {
	        DB.fechaPreparedStatement(pst);
	        DB.fechaResultSet(rs);
	    }
	    
	    return cursos;
	}

}
