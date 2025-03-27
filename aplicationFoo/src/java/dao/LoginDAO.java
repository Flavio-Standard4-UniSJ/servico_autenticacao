
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Acesso;

public class LoginDAO {
    private Connection conexao;
    public LoginDAO() throws Exception{
        this.conexao=CriaConexao.getConexao();
    }
 
    public boolean ValidarLogin(String login, String senha) throws SQLException{
        boolean autenticado=false;
        String sql = "SELECT * FROM usuario WHERE login=? and senha=md5(?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            autenticado=true;
            EntradaAcesso(login);
        }
        rs.close();
        stmt.close();
        return autenticado;        
    }

    public void EntradaAcesso(String nome_usuario)
        throws SQLException{
            String sql = "insert into acesso(nome_usuario,data_acesso,hora_acesso) values (?,?,?) ";
            Acesso acesso = new Acesso();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome_usuario);
            stmt.setString(2, acesso.getDataAcesso());
            stmt.setString(3, acesso.getHoraAcesso());
            stmt.execute();
            stmt.close();
    }

}
