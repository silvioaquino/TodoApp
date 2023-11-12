package controler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class ProjectController {
    
    public void save(Task task) {
        
        String sql = "INSERT INTO Project (idProject"
                +"name,"
                +"description,"
                +"completed,"
                +"notes,"
                +"deadline,"
                +"createdAt,"
                +"updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao Salvar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
            
        }
 
    }
    
    public void update(Task task) {
        
        String sql = "UPDATE Tasks SET "
                +"idProject = ?"
                +"name = ?"
                +"description = ?"
                +"completed = ?"
                +"notes = ?"
                +"deadline = ?"
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
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            
            //Executando a query
            statement.execute();
        
        }catch (Exception ex) {
            throw new RuntimeException("Erro ao Atualizar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
            
        }
   
    }
    
    public void removeById(int taskId) throws SQLException {
        
        String sql = "DELETE FROM Tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Estabelecendo a conex�o com o Banco de Dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setInt(1, taskId);
            
            //Executando a query
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    
    }
    
    public List<Task> getAll(int idProject) {
        
        String sql = "SELECT * FROM Tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        //Lista de tarefas que sera devolvida quando a chamada do metodo acontecer
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            //Estabelecendo a conex�o com o Banco de Dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores que corresponde ao filtro de busca
            statement.setInt(1, idProject);
            
            //Valor retornado pela execu��o da query
            resultSet = statement.executeQuery();
            
            //Enquanto houverem valores a serem percorridos do meu resultSet
            while(resultSet.next()){
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                tasks.add(task);
            }
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao Listar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        //Lista de tarefas que foi criada e carregada do Banco de dados
        return tasks;
    }
    
}
