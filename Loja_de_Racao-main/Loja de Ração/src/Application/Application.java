package Application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import Entities.Cadastro;

public class Application {
    public static void main(String[] args) {
        String Path = "C:\\Users\\ruben\\OneDrive\\�rea de Trabalho\\java_files\\Cadastros.txt";
        File file = new File(Path);

        Scanner sc = new Scanner(System.in); //scanner para Cadastro
        Scanner fr = new Scanner(System.in); //scanner para Fileread

        Cadastro usuariocadastrado = null;
        Cadastro novocadastro = null;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Path, true))) {
            fr = new Scanner(file);
            String[] object = null;

            System.out.println("Bem vindo a loja de ra��o!");
            System.out.println("Voc� tem cadastro na loja? s/n");
            char resposta = sc.next().charAt(0);

            if (resposta == 's') {
                System.out.println("Deixe eu te localizar pelo nome,seu nome �?");
                String nome = sc.next().toLowerCase();
                while (fr.hasNextLine()) {
                    object = fr.nextLine().split(";");
                    if (object[0].toLowerCase(Locale.ROOT) == nome) {
                        System.out.println("Localizei seu cadastro!");
                    } else {
                        throw new NullPointerException("Cadastro n�o localizado, tente novamente;");
                    }
                    System.out.println("Qual o peso atual do seu Pet?");
                    double novopeso = sc.nextDouble();
                    usuariocadastrado = new Cadastro(object[0], object[1], object[2], novopeso);
                    System.out.println("Ent�o " + usuariocadastrado.getNome() + " deseja comprar ra��o para " + usuariocadastrado.getNomepet() + "?");
                }
            } else if (resposta == 'n') {
                System.out.println("Qual o seu nome �?");
                String nome = sc.next();
                System.out.println("Qual o nome do seu pet?");
                String nomepet = sc.next();
                System.out.println("O seu pet � um gato, cachorro ou passaro?");
                String TipoDePet = sc.next().toLowerCase();
                System.out.println("Qual o peso do seu pet?");
                double PesoDoPet = sc.nextDouble();
                novocadastro = new Cadastro(nome, nomepet, TipoDePet, PesoDoPet);
                while (!fr.hasNextLine()) {
                    bw.write(novocadastro.toString());
                    System.out.println("Cadastro Adicionado!");
                    break;
                }
                System.out.println("Ent�o " + novocadastro.getNome() + " deseja comprar ra��o para " + novocadastro.getNomepet() + "?");

            } else {
                System.out.println("Opera��o invalida");
                throw new RuntimeException();
            }

System.out.println(novocadastro.toString());
System.out.println(usuariocadastrado.toString());


        } catch (IOException e) {
            System.out.println("Arquivo n�o encontrado ou n�o pode ser lido!");
        } finally {

            if (sc != null) {
                sc.close();
                fr.close();
            }
        }
    }
}
