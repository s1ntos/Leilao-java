package atvc;

import atvc.beans.ProdutosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        // conn = new conectaDAO().connectDB();
        // Implementar lógica para cadastrar produto
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> vendidos = new ArrayList<>();
    String sql = "SELECT * FROM produtoDTO WHERE status = 'Vendido'";
    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            vendidos.add(produto);
        }
    } catch (Exception e) {
        System.out.println("Erro ao listar produtos vendidos: " + e.getMessage());
    }
    return vendidos;
}
     
    public void inserir(produtosDTO produtoDTO) {
     String sql = "INSERT INTO produtoDTO(nome, valor, status) VALUES "
          + "(?, ?, ?)";
    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setString(1, produtoDTO.nome));
        stmt.setString(2, produtoDTO.valor());
        stmt.setString(2, produtoDTO.status());
        stmt.execute();
            
    } catch (Exception e) {
       System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }
    
    public void venderProduto(int id) {
    String sql = "UPDATE produtoDTO SET status = 'Vendido' WHERE id = ?";
    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (Exception e) {
        System.out.println("Erro ao vender produto: " + e.getMessage());
    }
}

       
}

   
