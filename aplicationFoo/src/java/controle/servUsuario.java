
package controle;

import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.Usuario;

@WebServlet("/servUsuario")
public class servUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Usuario usu = new Usuario();
            String escolha = request.getParameter("botao");
            if(escolha.equals("Incluir")){
                try{
                    usu.setNome(request.getParameter("txtnome"));
                    usu.setLogin(request.getParameter("txtlogin"));
                    usu.setSenha(request.getParameter("txtsenha"));
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usuarioDAO.cadastrarUsuario(usu);
                    
                    out.println("<body>");
                    out.println("<h1>Servlet servUsuario at " + request.getContextPath() + "</h1>");
                    out.println("<h1>Cadastrado!</h1>");
                    out.println("<h2>Nome: "+usu.getNome()+"</h2>");
                    out.println("<a href='index.html'>Voltar</a>");
                    out.println("</body>");  
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }

            }if(escolha.equals("Excluir")){
                try{
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usuarioDAO.excluirUsuario(request.getParameter("txtnome"));
                    
                    out.println("<body>");
                    out.println("<h1>Servlet servUsuario at " + request.getContextPath() + "</h1>");
                    out.println("<h1>Registro excluido!</h1>");
                    out.println("<a href='index.html'>Voltar</a>");
                    out.println("</body>");
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }//botao excluir
            if(escolha.equals("Alterar")){
                try{
                    usu.setNome(request.getParameter("txtnome"));
                    usu.setLogin(request.getParameter("txtlogin"));
                    usu.setSenha(request.getParameter("txtsenha"));
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usuarioDAO.alterarUsuario(usu); 
                    
                    out.println("<body>");
                    out.println("<h1>Servlet servUsuario at " + request.getContextPath() + "</h1>");
                    out.println("<h1>Edição concluida!</h1>");
                    out.println("<a href='index.html'>Voltar</a>");
                    out.println("</body>");
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }if(escolha.equals("Consultar")){
                try{
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usu=usuarioDAO.consultarUsuarioNome(request.getParameter("txtnome"));
                    
                    out.println("<body>");
                    out.println("<h1>Servlet servUsuario at " + request.getContextPath() + "</h1>");
                    out.println("<h2>Nome selecionado: "+usu.getNome()+"</h2>");
                    out.println("<h2>Login do Usuário: "+usu.getLogin()+"</h2>");
                    out.println("<h2>Senha do Usuário? : "+usu.getSenha()+"</h2>");
                    out.println("<a href='index.html'>Voltar</a>");
                    out.println("<body>");
                    
                }catch(NullPointerException ex){
                    ex.printStackTrace();
                }
            }if(escolha.equals("Entrar")){
                try{
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usu=usuarioDAO.realizaLogin(request.getParameter("txtlogin"), request.getParameter("txtsenha"));
                    
                    out.println("<body>");
                    out.println("<h1>Servlet servUsuario at " + request.getContextPath() + "</h1>");
                    out.println("<h2>Usuário "+usu.getLogin()+" Logado</h2>");
                    out.println("<h2>Senha do Usuário? : "+usu.getSenha()+"</h2>");
                    out.println("<a href='login.html'>Voltar</a>");
                    out.println("</body>");
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                
            }

    }catch(Exception ex){
        ex.printStackTrace();
    }

    }
      @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    processRequest(request, response);
    }

}
