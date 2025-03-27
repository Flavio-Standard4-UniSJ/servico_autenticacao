<%-- 
    Document   : listagem
    Created on : 25/03/2025, 13:06:49
    Author     : FLAVIO
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Usuários</title>
    </head>
    <body>
       <table border="1">
            <thead>
           <tr>
           <th>Nome</th>
           <th>Login</th>
           <th>Senha</th>
           </tr>
           </thead>
           <tbody>
           <%
                Connection conn = null;
                Statement st = null;
                ResultSet rs = null;
                try{
                Class.forName("org.postgresql.Driver").newInstance();
                conn=DriverManager.getConnection("jdbc:postgresql:aplicacaoweb","postgresql","postgres");
                st=conn.createStatement();
                rs=st.executeQuery("SELECT * FROM usuario");
                while(rs.next()){
            %>
            <tr>
            <td><%=rs.getString("nome")%></td>
            <td><%=rs.getString("login")%></td>
            <td><%=rs.getString("senha")%></td>
            </tr>
            <%
                }
                }catch(Exception e){
                e.printStackTrace();
                } finally{
                if(rs != null){ rs.close(); }
                if(st != null){ st.close(); }
                if(conn != null){ conn.close(); }
                }   
            %>
            </tbody>
        </table>
        <a href="index.html">Voltar</a>
    </body>

</html>
