package dao;

import dao.core.*;

public class BDSQLServer
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
        MeuPreparedStatement comando = null;

        try
        {
            comando =
                    new MeuPreparedStatement (
                            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
                            "jdbc:sqlserver://localhost:1433;databasename=master",
                            "livraria", "livraria");
            System.out.println("teste");
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD" + erro);
            System.exit(0); // aborta o programa
        }

        COMANDO = comando;
    }
}