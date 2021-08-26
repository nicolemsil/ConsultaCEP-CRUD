package dao;

import dao.core.MeuResultSet;
import model.Livraria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivrariaDao extends Livraria {

    public Livraria listar(){
        List<Livraria> livrarias = new ArrayList<Livraria>();

        try {
            String sql;

            sql = "SELECT * FROM Livraria WHERE certificado = 36524";

            BDSQLServer.COMANDO.prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            Livraria livraria = new Livraria (resultado.getInt   ("certificado"),
                    resultado.getString("nome"),
                    resultado.getString ("horario"),
                    resultado.getString("cep"));
            return livraria;
        }catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public Livraria buscaPeloCep(String cep) {
        Livraria livraria = null;
        try{
            String sql;
            sql = "SELECT * FROM Livraria WHERE cep=" + cep;

            BDSQLServer.COMANDO.prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            livraria = new Livraria (resultado.getInt   ("certificado"),
                    resultado.getString("nome"),
                    resultado.getString ("horario"),
                    resultado.getString("cep"));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return livraria;
    }

    public Livraria buscaPeloCertificado(String certificado) {
        Livraria livraria = null;
        try{
            String sql;
            sql = "SELECT * FROM Livraria WHERE certificado=" + certificado;

            BDSQLServer.COMANDO.prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            livraria = new Livraria (resultado.getInt   ("certificado"),
                    resultado.getString("nome"),
                    resultado.getString ("horario"),
                    resultado.getString("cep"));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return livraria;
    }

    public void inserir (String textoRecebido ) {
        Livraria livraria = null;
        try{

            String infomracao[] = textoRecebido.split(" ");

            if(infomracao.length > 4 || infomracao.length < 4)
            {
                throw new Exception("Quantidade de informações passadas é inválida");
            }

            String sql;

            sql = "INSERT INTO Livraria " +
                    "VALUES " +
                    "(?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString  (1, infomracao[0]);
            BDSQLServer.COMANDO.setString  (2, infomracao[1]);
            BDSQLServer.COMANDO.setString  (3, infomracao[2]);
            BDSQLServer.COMANDO.setString  (4, infomracao[3]);


            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void deletar (String cep) {

        try {
            String sql;

            sql = "DELETE FROM Livraria " +
                    "WHERE cep=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, cep);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();


        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void alterarCertificado (String textoRecebido) throws Exception
    {
        Livraria livraria = new Livraria();
        if (livraria==null)
            throw new Exception ("Livraria nao fornecido");

        try
        {
            String infomracao[] = textoRecebido.split(" ");

            if(infomracao.length > 2 || infomracao.length < 2)
            {
                throw new Exception("Quantidade de informações passadas é inválida");
            }

            String sql;

            sql = "UPDATE Livraria " +
                    "SET certificado=? " +
                    "WHERE cep = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString  (1, infomracao[0]);
            BDSQLServer.COMANDO.setString  (2, infomracao[1]);


            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de livro" + erro);
        }
    }

    public static void alterarNome (String textoRecebido) throws Exception
    {
        Livraria livraria = new Livraria();
        if (livraria==null)
            throw new Exception ("Livraria nao fornecido");

        try
        {
            String infomracao[] = textoRecebido.split(" ");

            if(infomracao.length > 2 || infomracao.length < 2)
            {
                throw new Exception("Quantidade de informações passadas é inválida");
            }

            String sql;

            sql = "UPDATE Livraria " +
                    "SET nome=? " +
                    "WHERE cep = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString  (1, infomracao[0]);
            BDSQLServer.COMANDO.setString  (2, infomracao[1]);


            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de livro" + erro);
        }
    }

    public static void alterarHorario (String textoRecebido) throws Exception
    {
        Livraria livraria = new Livraria();
        if (livraria==null)
            throw new Exception ("Livraria nao fornecido");

        try
        {
            String infomracao[] = textoRecebido.split(" ");

            if(infomracao.length > 2 || infomracao.length < 2)
            {
                throw new Exception("Quantidade de informações passadas é inválida");
            }

            String sql;

            sql = "UPDATE Livraria " +
                    "SET horario=? " +
                    "WHERE cep = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString  (1, infomracao[0]);
            BDSQLServer.COMANDO.setString  (2, infomracao[1]);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de livro: " + erro);
        }
    }

    public static void alterarCep (String textoRecebido) throws Exception
    {
        Livraria livraria = new Livraria();
        if (livraria==null)
            throw new Exception ("Livraria nao fornecido");

        try
        {
            String infomracao[] = textoRecebido.split(" ");

            if(infomracao.length > 2 || infomracao.length < 2)
            {
                throw new Exception("Quantidade de informações passadas é inválida");
            }

            String sql;

            sql = "UPDATE Livraria " +
                    "SET cep=? " +
                    "WHERE certificado = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString  (1, infomracao[0]);
            BDSQLServer.COMANDO.setString  (2, infomracao[1]);


            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de livro" + erro);
        }
    }
}
