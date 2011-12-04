package control;

import java.io.IOException;
import java.text.ParseException;
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
import java.text.DateFormat;
import util.Validacao;

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
        String dataNasc = getRequest().getParameter("data");
        String cpf = getRequest().getParameter("cpf");
        String cep = getRequest().getParameter("cep");
        String log = getRequest().getParameter("log");
        String num = getRequest().getParameter("num");
        String comp = getRequest().getParameter("comp");
        String bairro = getRequest().getParameter("bairro");
        String cid = getRequest().getParameter("cid");

        //Dados que não vieram do formulário mas farão parte da entidade
        Date data = new Date(System.currentTimeMillis());
        int salt = (int) (1 + Math.random() * Integer.MAX_VALUE);
        int qtd_pizzas = 0;

        //ArrayList q conterá os possíveis erros
        ArrayList erros = new ArrayList();

        //Validações do campo nome
        if (nome == null || nome.equals("")) { //Campo nome em branco
            erros.add(1);
        } else if (nome.length() < 8) { //Campo nome com menos de 8 caracteres
            erros.add(2);
        }
        
        //Validação de email
        if (email == null || email.equals("")) {
            erros.add(3);
        } else if (!Validacao.checkEmail(email)) {
            erros.add(4);
        } else {
            Cliente cliente = ClienteDAO.getClienteByEmail(email);
            if (cliente != null) {
                erros.add(5);
            }
        }
        
        //Validação de repetir email
        if (repemail == null || repemail.equals("")) {
            erros.add(6);
        } else if (!email.equals(repemail)) {
            erros.add(7);
        }

        //Validação de senha
        if (senha == null || senha.equals("")) {
            erros.add(8);
        } else if (senha.length() < 8 || senha.length() > 25) {
            erros.add(9);
        }
        
        //Validação de repetir senha
        if (repsenha == null || repsenha.equals("")) {
            erros.add(10);
        } else if (!senha.equals(repsenha)) {
            erros.add(11);
        }

        //Valicação de telefone
        if (telefone == null || telefone.equals("")) {
            erros.add(12);
        } else if (Validacao.checkPhone(telefone)) {
            erros.add(13);
        }

        //Validação de data
        if (dataNasc == null || dataNasc.equals("")) {
            erros.add(14);
        } else if (!Validacao.checkDate(dataNasc)) {
            erros.add(15);
        } else {
            DateFormat df = DateFormat.getDateInstance();
            String atual = df.format(new Date(System.currentTimeMillis()));
            if (Integer.parseInt(dataNasc.substring(6))>Integer.parseInt(atual.substring(6))-16) {
                erros.add(16);
            }
        }
        
        //Validação de cpf
        if (cpf == null || cpf.equals("")) {
            erros.add(17);
        } else if (!Validacao.checkCpf(cpf)) {
            erros.add(18);
        } else {
            Cliente cliente = ClienteDAO.getClienteByCpf(cpf);
            if (cliente != null) {
                erros.add(19);
            }
        }
        
        //Validação de cep
        if (cep == null || cep.equals("")) {
            erros.add(20);
        } else if (!Validacao.checkCep(cep)) {
            erros.add(21);
        }
        
        //Validação de logradouro
        if (log == null || log.equals("")) {
            erros.add(22);
        }
        
        //Validação de numero
        if (num == null || num.equals("")) {
            erros.add(23);
        } else {
            try {
                int aux = Integer.parseInt(num);
            } catch (NumberFormatException ex) {
                erros.add(24);
            }
        }
        
        //Validação de bairro
        if (bairro == null || bairro.equals("")) {
            erros.add(25);
        }
        
        //Validação de cidade
        if (cid == null || cid.equals("")) {
            erros.add(26);
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
            cliente.setQtd_pizzas(qtd_pizzas);
            cliente.setTelefone(telefone);
            DateFormat df1 = DateFormat.getDateInstance();
            try {
                cliente.setData_nasc(df1.parse(dataNasc));
            } catch (ParseException ex) {
                Logger.getLogger(CadastroClienteProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            cliente.setCpf(cpf);
            String endereco="";
            if (comp == null || comp.equals("")) {
                endereco = log+"|"+bairro+"|"+cid+"|"+cep+"|"+num;
            } else {
                endereco = log+"|"+bairro+"|"+cid+"|"+cep+"|"+num+"|"+comp;
            }
            cliente.setEndereco(endereco);
            cliente.setData_cadastro(data);
            System.out.println(cliente.toString());
            ClienteDAO.addCliente(cliente);
            getResponse().sendRedirect("main.do?action=cadsuc");
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
