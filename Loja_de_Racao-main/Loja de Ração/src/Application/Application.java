package Application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Entities.Cadastro;

public class Application {
    public static void main(String[] args) {
        String Path = "C:\\Users\\ruben\\OneDrive\\�rea de Trabalho\\java_files\\Cadastros.txt";
        File file = new File(Path);

        Scanner sc = new Scanner(System.in); //scanner para Cadastro
        Scanner fr = new Scanner(System.in); //scanner para Fileread

        Cadastro Cliente = null;

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
                    if (object[0] == nome) {
                        System.out.println(object[0]);
                        System.out.println("Localizei seu cadastro!");
                    }
                    System.out.println("Qual o peso atual do seu Pet?");
                    double novopeso = sc.nextDouble();
                    Cliente = new Cadastro(object[0], object[1], object[2], novopeso);
                }
            } else if (resposta == 'n') {
                System.out.println("Qual o seu nome �?");
                String nome = sc.next().toLowerCase();
                System.out.println("Qual o nome do seu pet?");
                String nomepet = sc.next().toLowerCase();
                System.out.println("O seu pet � um gato, cachorro ou passaro?");
                String TipoDePet = sc.next().toLowerCase();
                System.out.println("Qual o peso do seu pet?");
                double PesoDoPet = sc.nextDouble();
                Cliente = new Cadastro(nome, nomepet, TipoDePet, PesoDoPet);
                while (!fr.hasNextLine()) {
                    bw.write(Cliente.toString());
                    System.out.println("Cadastro Adicionado!");
                    break;
                }
            } else {
                System.out.println("Opera��o invalida");
                throw new RuntimeException();
            }

            System.out.println("Ent�o " + Cliente.getNome() + " deseja comprar ra��o para " + Cliente.getNomepet() + "?");
            System.out.println(Cliente.toString());




        } catch (IOException e) {
            System.out.println("Arquivo n�o encontrado ou n�o pode ser lido!");

        } catch (NullPointerException e) {
            System.out.println("Usuario n�o encontrado!");

        } finally {

            if (sc != null) {
                sc.close();
                fr.close();
            }
        }
    }
}
