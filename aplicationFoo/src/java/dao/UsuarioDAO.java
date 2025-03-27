
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Acesso;
import modelo.Usuario;

public class UsuarioDAO {
    private Connection conexao;
    public UsuarioDAO() throws Exception{
        this.conexao = CriaConexao.getConexao();
    }

    public void cadastrarUsuario(Usuario usuario) throws SQLException{
        String sql="INSERT INTO usuario (nome,login, senha) VALUES (?,?,md5(?))";
        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setString(1, usuario.getNome());
        preparador.setString(2, usuario.getLogin());
        preparador.setString(3, usuario.getSenha());
        preparador.execute();
        preparador.close();
        System.out.println("Cadastro de " + usuario.getNome() + "  realizado com sucesso");
        registroLogin(usuario.getNome());
    }

    public void registroLogin(String nome_usuario) throws SQLException{
        String sql = "INSERT INTO acesso (nome_usuario,data_acesso,hora_acesso) VALUES (?,?,?)";
        Acesso acesso = new Acesso();
        System.out.println("Nome: " + nome_usuario);
        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setString(1, nome_usuario);
        preparador.setString(2, acesso.getDataAcesso());
        preparador.setString(3, acesso.getHoraAcesso());
        preparador.execute();
        preparador.close();
    }
    
    public void excluirUsuario(String nome) throws Exception{
        String sql = ("DELETE FROM usuario WHERE nome= '"+nome+"'");
        PreparedStatement preparador = this.conexao.prepareStatement(sql);
        preparador.execute();
        preparador.close();
    }

    public void alterarUsuario(Usuario usu) throws Exception{
        String sql = "UPDATE usuario SET login=?, senha=md5(?) WHERE nome=?";
        PreparedStatement preparador = this.conexao.prepareStatement(sql);
        preparador.setString(1, usu.getLogin());
        preparador.setString(2, usu.getSenha());
        preparador.setString(3, usu.getNome());
        preparador.execute();
        preparador.close();
    }  
    
    public Usuario consultarUsuarioNome(String nome) throws Exception{
        Usuario usu = new Usuario();
        String pesq = ("SELECT * FROM usuario WHERE nome=?");
        PreparedStatement preparador = this.conexao.prepareStatement(pesq);
        preparador.setString(1, nome);
        ResultSet rs = preparador.executeQuery();
        if(rs.next()){
            usu.setNome(rs.getString("nome"));
            usu.setLogin(rs.getString("login"));
            usu.setSenha(rs.getString("senha"));
            rs.close();
            preparador.close();
        }
        return usu;
    }
    public Usuario realizaLogin(String login, String senha) throws Exception{
        Usuario usu = new Usuario();
        String log = ("SELECT * FROM usuario WHERE login=? AND senha=md5(?)");
        PreparedStatement preparador = this.conexao.prepareStatement(log);
        preparador.setString(1, login);
        preparador.setString(2, senha);
        ResultSet rs = preparador.executeQuery();
        if(rs.next()){
            usu.setLogin(rs.getString("login"));
            usu.setSenha(rs.getString("senha"));
            rs.close();
            preparador.close();
        }
        return usu;
    }

}
