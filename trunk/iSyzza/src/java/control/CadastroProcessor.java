package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Cliente;
import dao.ClienteDAO;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author inf4m
 */
public class CadastroProcessor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Setando a codificação para UTF-8
        request.setCharacterEncoding("UTF-8");
        
        //Recuperando dados do formulário
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String repemail = request.getParameter("repemail");
        String senha = request.getParameter("senha");
        String repsenha = request.getParameter("repsenha");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");
        String cpf = request.getParameter("cpf");
        
        //Dados que não vieram do formulário mas farão parte da entidade
        Date data = new Date(System.currentTimeMillis());
        int salt = (int) (1+Math.random()*Integer.MAX_VALUE);
        int qtd_pedidos = 0;

        //ArrayList q conterá os possíveis erros
        ArrayList erros = new ArrayList();

        //Validações do campo nome
        if (nome == null || nome.equals("")) {
            erros.add(1);
        } else if (nome.length() < 8) {
            erros.add(2);
        }

        //Validação de email
        if (email == null || email.equals("")) {
            erros.add(3);
        }
        if (!email.equals(repemail)) {
            erros.add(4);
        }

        //Validação de senha
        if (senha == null || senha.equals("")) {
            erros.add(5);
        }
        if (!senha.equals(repsenha)) {
            erros.add(6);
        }

        //Valicação de telefone
        if (telefone == null || telefone.equals("")) {
            erros.add(7);
        }

        //Validação de endereco
        if (endereco == null || endereco.equals("")) {
            erros.add(8);
        }
        
        //Validação de cpf
        if (cpf == null || cpf.equals("")) {
            erros.add(9);
        }

        if (!erros.isEmpty()) {
            //caso existam erros manda de volta pra página de cadastro
            RequestDispatcher view;
            request.setAttribute("erros", erros);
            view = request.getRequestDispatcher("cadcli.jsp");
            view.forward(request, response);
        } else {
            //caso tudo ok cria o cliente e insere no banco
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setSenha(md5((senha+salt)+salt));
            cliente.setSalt(salt);
            cliente.setQtd_pizzas(qtd_pedidos);
            cliente.setTelefone(telefone);
            cliente.setEndereco(endereco);
            cliente.setCpf(cpf);
            cliente.setData_cadastro(data);
            System.out.println(cliente.toString());
            ClienteDAO.inserirCliente(cliente);
            response.sendRedirect("cadsuc.jsp");
        }
    }

    //Função para criar hash da senha informada  
    public static String md5(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            sen = hash.toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CadastroProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sen;
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
