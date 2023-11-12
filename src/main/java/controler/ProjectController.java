package controler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {
    
    public void save(Project project) {
        
        String sql = "INSERT INTO Project (name"
                +"description,"
                +"createdAt,"
                +"updatedAt) VALUES (?, ?, ?, ?)";
   
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao Salvar o Projeto" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
            
        }
 
    }
    
    public void update(Project project) {
        
        String sql = "UPDATE Projects SET "
                +"name = ?"
                +"description = ?"
                +"createdAt = ?"
                +"updatedAt = ?"
                +"WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Estabelecendo a conex�o com o Banco de Dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            
            //Executando a query
            statement.execute();
        
        }catch (Exception ex) {
            throw new RuntimeException("Erro ao Atualizar o Projeto" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
            
        }
   
    }
    
    public void removeById(int projectId) throws SQLException {
        
        String sql = "DELETE FROM Projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Estabelecendo a conex�o com o Banco de Dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setInt(1, projectId);
            
            //Executando a query
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar o Projeto" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    
    }
    
    public List<Project> getAll(int id) {
        
        String sql = "SELECT * FROM Project WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        //Lista de tarefas que sera devolvida quando a chamada do metodo acontecer
        List<Project> projects = new ArrayList<Project>();
        
        try {
            //Estabelecendo a conex�o com o Banco de Dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores que corresponde ao filtro de busca
            statement.setInt(1, id);
            
            //Valor retornado pela execu��o da query
            resultSet = statement.executeQuery();
            
            //Enquanto houverem valores a serem percorridos do meu resultSet
            while(resultSet.next()){
                Project project = new Project();
                project.setId(resultSet.getInt("id"));              
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                projects.add(project);
            }
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao Listar o Projeto" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        //Lista de tarefas que foi criada e carregada do Banco de dados
        return projects;
    }
    
}
