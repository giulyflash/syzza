package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import entity.Cliente;
import dao.ClienteDAO;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author inf4m
 */
public class CadastroClienteProcessor extends Processor {

    @Override
    public void execute() throws ServletException, IOException {
        //Setando a codificação para UTF-8
        getRequest().setCharacterEncoding("UTF-8");

        //Recuperando dados do formulário
        String nome = getRequest().getParameter("nome");
        String email = getRequest().getParameter("email");
        String repemail = getRequest().getParameter("repemail");
        String senha = getRequest().getParameter("senha");
        String repsenha = getRequest().getParameter("repsenha");
        String telefone = getRequest().getParameter("telefone");
        String endereco = getRequest().getParameter("endereco");
        String cpf = getRequest().getParameter("cpf");

        //Dados que não vieram do formulário mas farão parte da entidade
        Date data = new Date(System.currentTimeMillis());
        int salt = (int) (1 + Math.random() * Integer.MAX_VALUE);
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
            getRequest().setAttribute("erros", erros);
            forward("cadcli.jsp");
        } else {
            //caso tudo ok cria o cliente e insere no banco
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setSenha(md5((senha + salt) + salt));
            cliente.setSalt(salt);
            cliente.setQtd_pizzas(qtd_pedidos);
            cliente.setTelefone(telefone);
            cliente.setEndereco(endereco);
            cliente.setCpf(cpf);
            cliente.setData_cadastro(data);
            System.out.println(cliente.toString());
            ClienteDAO.inserirCliente(cliente);
            getResponse().sendRedirect("cadsuc.jsp");
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
            Logger.getLogger(CadastroClienteProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sen;
    }
}
