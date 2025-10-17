package modelos;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
public class CompromissoDAO {
 
    // --- Configurações de Conexão (ADAPTE AO SEU BANCO) ---
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Exemplo para MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/agendaweb"; // URL do seu banco
    private static final String USER = "root"; // Seu usuário do banco
    private static final String PASS = "suasenha"; // Sua senha do banco
    // ----------------------------------------------------
 
    // Método de Conexão Centralizado
    private Connection conectar() {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASS);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
            return null;
        }
    }
 
    // 1. CREATE (Salvar Novo Compromisso)
    public void salvarCompromisso(Compromisso c) {
        String sql = "INSERT INTO compromissos (descricao, data_hora, local) VALUES (?, ?, ?)";
        try (Connection con = conectar(); 
             PreparedStatement pst = con.prepareStatement(sql)) {
 
            // Converção de Date para um formato aceito pelo SQL
            java.sql.Timestamp sqlDataHora = new java.sql.Timestamp(c.getHora().getTime());
 
            pst.setString(1, c.getDescricao());
            pst.setTimestamp(2, sqlDataHora);
            pst.setString(3, c.getLocal());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    // 2. READ (Listar Todos os Compromissos)
    public List<Compromisso> listarTodos() {
        List<Compromisso> lista = new ArrayList<>();
        String sql = "SELECT id, descricao, data_hora, local FROM compromissos ORDER BY data_hora";
        try (Connection con = conectar(); 
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
 
            while (rs.next()) {
                Compromisso c = new Compromisso();
                c.setId(rs.getInt("id"));
                c.setDescricao(rs.getString("descricao"));
                c.setLocal(rs.getString("local"));
                // Conversão de Timestamp SQL para Date Java
                c.setHora(new java.util.Date(rs.getTimestamp("data_hora").getTime()));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    // 3. UPDATE (Atualizar Compromisso Existente)
    public void atualizarCompromisso(Compromisso c) {
        String sql = "UPDATE compromissos SET descricao=?, data_hora=?, local=? WHERE id=?";
        try (Connection con = conectar(); 
             PreparedStatement pst = con.prepareStatement(sql)) {
 
            java.sql.Timestamp sqlDataHora = new java.sql.Timestamp(c.getHora().getTime());
 
            pst.setString(1, c.getDescricao());
            pst.setTimestamp(2, sqlDataHora);
            pst.setString(3, c.getLocal());
            pst.setInt(4, c.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 4. DELETE (Deletar Compromisso pelo ID)
    public void deletarCompromisso(int id) {
        String sql = "DELETE FROM compromissos WHERE id=?";
        try (Connection con = conectar(); 
             PreparedStatement pst = con.prepareStatement(sql)) {
 
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}