package com.codebind;

import dao.LivrariaDao;
import model.Livraria;
import WS.*;

import javax.swing.*;
import java.awt.event.*;

public class Tela extends JDialog {
    private JPanel contentPane;
    private JButton buttonConsultar;
    private JButton buttonRemover;
    private JButton buttonAtualizar;
    private JButton buttonInserir;
    private JTextArea textResultado;
    private JTextField textInfoConsultar;
    private JTextField textInfoInserir;
    private JTextField textInfoRemover;
    private JTextField textAtualizaCertificado;
    private JTextField textAtualizaNome;
    private JTextField textAtualizaHorario;
    private JTextField textAtualizaCep;
    private JButton buttonLer;

    public Tela() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonConsultar);


        buttonConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cep;
                cep = textInfoConsultar.getText();
                textResultado.setText(cep);

                if(cep.equals(""))
                    textResultado.setText("Digite um CEP válido");

                Livraria livraria = new Livraria();
                LivrariaDao livrariaDao = new LivrariaDao();
                livraria = livrariaDao.buscaPeloCep(cep);

                Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", cep);

                textResultado.setText(livraria.toString() + "\n" +
                        "\n Seu logradouro é:" + logradouro);
            }
        });


        buttonInserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String informacoes;

                LivrariaDao livrariaDao = new LivrariaDao();
                informacoes = textInfoInserir.getText();
                livrariaDao.inserir(informacoes);

                String cep[] = informacoes.split(" ");

                if(informacoes.equals("") || cep.length != 4)
                    textResultado.setText("Digite informações válidas");

                Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", cep[3]);
                textResultado.setText(livrariaDao.buscaPeloCep(cep[3]) + "\n Adicionada ao banco de dados!" + "\n Suas informações de logradouro são: " + "\n" +
                        logradouro);
            }
        });


        buttonRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cep;
                cep = textInfoRemover.getText();
                LivrariaDao livrariaDao = new LivrariaDao();

                Livraria livraria = new Livraria();
                livraria = livrariaDao.buscaPeloCep(cep);

                Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", cep);
                textResultado.setText(livraria.toString() + "\nDe logradouro: " + logradouro +"\n \n Informações excluídas!" );

                livrariaDao.deletar(cep);
            }
        });


        buttonAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String informacoes;

                Livraria livraria = new Livraria();

                LivrariaDao livrariaDao = new LivrariaDao();


                if (!textAtualizaCertificado.getText().equals("") && (textAtualizaCep.getText().equals("") && textAtualizaHorario.getText().equals("") && textAtualizaNome.getText().equals("")))
                {
                    informacoes = textAtualizaCertificado.getText();
                    try {
                        String cep[];
                        cep = informacoes.split(" ");

                        livraria = livrariaDao.buscaPeloCep(cep[1]);
                        Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", cep[1]);

                        textResultado.setText(livraria.toString() + "\n \n -----> Você mudou o certificado de " + livrariaDao.buscaPeloCep(cep[1]).getCertificado() + " para " + cep[0] + " <-----\n" +
                                "\n O endereço continua: " + logradouro);
                        livrariaDao.alterarCertificado(cep[0]);
                    }

                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                else if (!textAtualizaNome.getText().equals("") && (textAtualizaCep.getText().equals("") && textAtualizaHorario.getText().equals("") && textAtualizaCertificado.getText().equals("")))
                {
                    informacoes = textAtualizaNome.getText();
                    try {
                        String infosDigitadas[];
                        infosDigitadas = textAtualizaNome.getText().split(" ");

                        livraria = livrariaDao.buscaPeloCep(infosDigitadas[1]);
                        Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", infosDigitadas[1]);

                        textResultado.setText(livraria.toString() + "\n \n -----> Você mudou o nome da livraria de " + livrariaDao.buscaPeloCep(infosDigitadas[1]).getNome() + " para " + infosDigitadas[0] + "<-----" +
                                "\n Seu logradouro continua: \n" + logradouro);
                        livrariaDao.alterarNome(informacoes);
                    }

                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                else if (!textAtualizaHorario.getText().equals("") && (textAtualizaCep.getText().equals("") && textAtualizaNome.getText().equals("") && textAtualizaCertificado.getText().equals("")))
                {
                    informacoes = textAtualizaHorario.getText();
                    try {
                        String infosDigitadas[];
                        infosDigitadas = textAtualizaHorario.getText().split(" ");

                        livraria = livrariaDao.buscaPeloCep(infosDigitadas[1]);
                        Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "http://api.postmon.com.br/v1/cep", infosDigitadas[1]);
                        textResultado.setText(livraria.toString() + "\n \n -----> Você mudou o horário de funciomento de " + livrariaDao.buscaPeloCep(infosDigitadas[1]).getHorario() + " \n para " + infosDigitadas[0] + "<-----" +
                                "\n Seu logradouro continua: \n" + logradouro);
                        livrariaDao.alterarHorario(informacoes);
                    }

                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                else if (!textAtualizaCep.getText().equals("") && (textAtualizaHorario.getText().equals("") && textAtualizaNome.getText().equals("") && textAtualizaCertificado.getText().equals("")))
                {
                    informacoes = textAtualizaCep.getText();
                    try {
                        String infosDigitadas[];
                        infosDigitadas = textAtualizaCep.getText().split(" ");

                        livraria = livrariaDao.buscaPeloCertificado(infosDigitadas[1]);

                        Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "http://api.postmon.com.br/v1/cep", "37550175" );

                        textResultado.setText(livraria.toString() + "\n \n -----> Você mudou o CEP da livraria de " + livrariaDao.buscaPeloCertificado(infosDigitadas[1]).getCep() + " para " + infosDigitadas[0] + "<-----" +
                                "\n Agora o logradouro é: \n" + logradouro);
                        livrariaDao.alterarCep(informacoes);
                    }

                    catch (Exception exception) {
                        exception.printStackTrace();
                    }

                }
                else
                {
                    textResultado.setText("Adicione as informações corretamento em seu devido campo \npara que a ação possa ser realizada.");
                }

            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Tela dialog = new Tela();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public JTextField getTextInfo() {
        return textInfoConsultar;
    }
}
