package atvc;

import atvc.beans.ProdutosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES (?, ?, ?)";
        try {
            conn = conectaDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        try {
            conn = conectaDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        return listagem;
    }
}