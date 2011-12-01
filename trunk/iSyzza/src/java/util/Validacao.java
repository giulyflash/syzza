/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Familia
 */
public class Validacao {

    private static Pattern p;

    public static boolean checkEmail(String email) {
        p = Pattern.compile("^[A-Za-z0-9_\\.-]{6,30}@[A-Za-z0-9]{2,30}(\\.[A-Za-z]{2,6})+$");
        Matcher m = p.matcher(email);
        return m.find(0);
    }
    
    public static boolean checkCep(String cep) {
        p = Pattern.compile("^[0-9]{5}-[0-9]{3}$");
        Matcher m = p.matcher(cep); 
        return m.find(0);
    }

    public static boolean checkCpf(String cpf) {
        p = Pattern.compile("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$");
        Matcher m = p.matcher(cpf);
        if (!m.find(0)) {
            return false;
        }
        String acpf = "";
        p = Pattern.compile("[0-9]+");
        m = p.matcher(cpf);
        while (m.find()) {
            acpf += m.group();
        }
        if(acpf.equals("11111111111") || acpf.equals("00000000000")) {
            return false;
        }
        for (int t = 9; t < 11; t++) {
            int sum, i;
            for (sum = 0, i = 0; i < t; i++) {
                sum += Integer.parseInt(acpf.substring(i, i + 1)) * ((t + 1) - i);
            }
            sum = ((10 * sum) % 11) % 10;
            if (Integer.parseInt(acpf.substring(t, t + 1)) != sum) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPhone(String phone) {
        p = Pattern.compile("^\\([0-9]{2}\\)[0-9]{4}-[0-9]{4}$");
        Matcher m = p.matcher(phone);
        return m.find(0);
    }
    
    public static boolean checkDate(String date) {
        p = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
        Matcher m = p.matcher(date);
        if (!m.find(0)) {
            return false;
        }
        String adate = "";
        p = Pattern.compile("[0-9]+");
        m = p.matcher(date);
        int i=0;
        String[] array = new String[3];
        while (m.find()) {
            array[i] = m.group();
            i++;
        }
        int dia = Integer.parseInt(array[0]);
        int mes = Integer.parseInt(array[1]);
        int ano = Integer.parseInt(array[2]);
        DateFormat df = DateFormat.getDateInstance();
        String atual = df.format(new Date(System.currentTimeMillis()));
        m = p.matcher(atual);
        i=0;
        while (m.find()) {
            array[i] = m.group();
            i++;
        }
        int anoAtual = Integer.parseInt(array[2]);
        if (dia<01 || dia>31 || mes<01 || mes>12 || ano<1900 || ano>=anoAtual) {
            return false;    
        }
        switch(mes) {
            case 4:
            case 6:
            case 9:
            case 11:
                if (dia > 30) {
                    return false;
                }
                break;
            case 2:
                if (((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)) {
                    if (dia > 29) {
                        return false;
                    }
                } else {
                    if (dia > 28) {
                        return false;
                    }
                }
                break;
        }
        return true;
    }
    
    public static void main(String[] args) {
        
        String email = "dedsadsadsira@gmail.com.br.poa.ifrs";
        if (checkEmail(email)) {
            System.out.println("Email valido");
        } else {
            System.out.println("email invalido");
        }

        String cpf = "032.500.050-61";
        if (checkCpf(cpf)) {
            System.out.println("Cpf valido");
        } else {
            System.out.println("Cpf invalido");
        }
        
        String phone = "(51)8441-6170";
        if (checkPhone(phone)) {
            System.out.println("Telefone valido");
        } else {
            System.out.println("Telefone invalido");
        }
        
        String date = "29/02/1988";
        if (checkDate(date)) {
            System.out.println("Data valida");
        } else {
            System.out.println("Data invalida");
        }
    }
}
