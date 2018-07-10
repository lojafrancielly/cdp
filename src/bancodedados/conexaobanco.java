
package bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class conexaobanco {
    String driver = "com.mysql.jdbc.Driver";
    String banco = "banco";
    String host = "127.0.0.1";
    String str_conn = "jdbc:mysql://"+host+":3306/"+banco;
    String usuario = "root";
    String senha = "12345";
    
    public Connection conexao;
    Statement estados;
   
   public void conectar(){
       try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(str_conn, usuario, senha);
            estados = conexao.createStatement();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro no driver!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexao!");
        }
   }
    
     public void fecharconexao(){
        try {
            estados.close();
            conexao.close();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro ao fechar conexao!");   
        }    
   }
    
    public void cadastrafuncionario(String cpf, String rg, String nome, String datadenascimento, String sexo, String cor, String estadocivil, String nacionalidade, String cargoprofissional, String cep, String estado, String cidade, String logradouro,String bairro, int numero, String complemento,String telefone, String celular, String email){
        String sqlinserir= "Insert into funcionario (cpf,rg,nome,datadenascimento,sexo,cor,estadocivil,nacionalidade,cargoprofissional,cep,estado,cidade,logradouro,bairro,numero,complemento,telefone,celular,email) values"
                +"('"+cpf+"','"+rg+"','"+nome+"','"+datadenascimento+"','"+sexo+"','"+cor+"','"+estadocivil+"','"+nacionalidade+"','"+cargoprofissional+"','"+cep+"','"+estado+"','"+cidade+"','"+logradouro+"','"+bairro+"',"+numero+",'"+complemento+"','"+telefone+"','"+celular+"','"+email+"')";
        try {
            int x = estados.executeUpdate(sqlinserir);
             JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error ao efetuar cadastro!"); 
        }
    }
    
    
   
    public void atualizarfuncionario (String cpf, String rg, String nome, String datadenascimento, String sexo, String cor, String estadocivil, String nacionalidade, String cargoprofissional, String cep, String estado, String cidade, String logradouro,String bairro, int numero, String complemento, String celular, String email){
       String sqlatualizarfuncionario= "Update fncionario set rg='"+rg+"',nome='"+nome+"',datadenascimento='"+datadenascimento+"',sexo='"+sexo+"',cor='"+cor+"',estadocivil='"+estadocivil+"',nacionalidade='"+nacionalidade+"',cargoproficional='"+cargoprofissional+"',cep='"+cep+"',estado='"+estado+"',cidade='"+cidade+"',logradouro='"+logradouro+"',bairro='"+bairro+"',numero="+numero+",complemento='"+complemento+"',celular='"+celular+"',email='"+email+"' "
               + "where cpf='"+cpf+"';";
        try {
            int x= estados.executeUpdate(sqlatualizarfuncionario);
            JOptionPane.showMessageDialog(null, "Atualização efetuada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ao atualizar!");
        }
    } 
   
    public void deletarfuncionario(String cpf){
        String sqldeletar= "Deletar from paciente where cpf='"+cpf+"';";
        try {
            int x= estados.executeUpdate(sqldeletar);
            JOptionPane.showMessageDialog(null, "Paciente removido com sucesso!");
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error ao remover!");
        }
       
            
   } 
    public ArrayList<funcionario> pesquisarfuncionario(){
        ResultSet resultado= null;
        String sqlconsultar= "Selec * from funcionario";
        ArrayList<funcionario> lista= new ArrayList<funcionario>();
        try {
            resultado= estados.executeQuery(sqlconsultar);
            while(resultado.next()){
                funcionario f= new funcionario();
                f.setCpf(resultado.getString("cpf"));
                f.setRg(resultado.getString("rg"));
                f.setNome(resultado.getString("nome"));
                f.setDatadenascimento(resultado.getString("datadenascimento"));
                f.setSexo(resultado.getString("sexo"));
                f.setCor(resultado.getString("cor"));
                f.setEstadocivil(resultado.getString("estadocivil"));
                f.setNacionalidade(resultado.getString("nacionalidade"));
                f.setCargoprofissional(resultado.getString("cargoproficional"));
                f.setCep(resultado.getString("cep"));
                f.setEstado(resultado.getString("estado"));
                f.setCidade(resultado.getString("cidade"));
                f.setLogradouro(resultado.getString("logradouro"));
                f.setBairro(resultado.getString("bairro"));
                f.setNumero(Integer.parseInt(resultado.getString("numero")));
                f.setComplemento(resultado.getString("complemento"));
                f.setTelefone(resultado.getString("telefone"));
                f.setCecular(resultado.getString("celular"));
                f.setEmail(resultado.getString("email"));
                
                lista.add(f);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar a consultar");
        }
        return lista;
    }
    
    public void prencherParaTeste(){
        
    }
    
 /*            
          //sistema de login
         
         public void CadastrarLogin (String nomeusuario, String senha){
             String cadastraLogin= "insert into login (nomeusuario,senha) values ('"+nomeusuario+"','"+senha+"');";
        try {
            int x = estados.executeUpdate(cadastraLogin);
            JOptionPane.showMessageDialog(null, "Úsuario cadastrado com sucesso!");
        } catch (SQLException ex) {
              System.out.println(ex);
           JOptionPane.showMessageDialog(null, "Error ao cadastra úsuario!");
        }
         }
         public ArrayList<Login> acessa(String nomeusuario, String senha) throws SQLException{
             ResultSet resultado = null;
             String acessa= "select * from login where nomeusuario='"+nomeusuario+"' and senha='"+senha+"';;";
             ArrayList<Login> lista = new ArrayList<Login>();
              try{
               resultado = estados.executeQuery(acessa);
                while(resultado.next()){
                 Login f =new Login();
                 f.setNomeusuario(resultado.getString("nomeusuario"));
                 f.setSenha(resultado.getString("senha"));
                }   
                 JOptionPane.showMessageDialog(null, "Login Efetuado com Sucesso!");
              }catch(SQLException ex){
                 JOptionPane.showMessageDialog(null, "Erro ao acessar"); 
              }
              return lista;
         }
         
         public void alterarsenha (String nomeusuario, String senha){
             String SqlAlterarSenha="update login set senha='"+senha+"' where nomeusuario='"+nomeusuario+"';";
        try {
            JOptionPane.showMessageDialog(null, "Senha Alterada Com Sucesso!");
            int x = estados.executeUpdate(SqlAlterarSenha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ao Alterar Senha!");
        }    
         }
    
    */
    
   
    // Cadastrar EMPENHOS
   
    
    
    
    
    
    
    
    
    
    
    
    



}
