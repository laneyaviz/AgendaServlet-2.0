package modelos;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
public class CompromissoDAO {
 
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // 
    private static final String URL = "jdbc:mysql://localhost:3306/agendaweb"; 
    private static final String USER = "root"; 
    private static final String PASS = "root"; 
    // ----------------------------------------------------
 
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
 
    public void salvarCompromisso(Compromisso c) {
        String sql = "INSERT INTO compromissos (descricao, data_hora, local) VALUES (?, ?, ?)";
        try (Connection con = conectar(); 
             PreparedStatement pst = con.prepareStatement(sql)) {
 
            java.sql.Timestamp sqlDataHora = new java.sql.Timestamp(c.getHora().getTime());
 
            pst.setString(1, c.getDescricao());
            pst.setTimestamp(2, sqlDataHora);
            pst.setString(3, c.getLocal());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
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
                c.setHora(new java.util.Date(rs.getTimestamp("data_hora").getTime()));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
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