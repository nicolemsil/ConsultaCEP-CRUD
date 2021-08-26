package WS;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class ClienteWS
{
    //metodo getObjeto serve para acionar o web service e mapear o Json num objeto java e receber como objeto Java
    public static Object getObjeto (Class tipoObjetoRetorno, //recebe como parametro o tipo de objeto que vai ser retornado
                                    String urlWebService, // é a url do web service
                                    String... parametros) //esse "..."servem para fazer esse parametro aceitar mais de 2 parametros, nesse caso de String (os outros dois - tipoObjetoRetorno urlWebService - estão ali pq são obrigatorios)
    // então se eu colocar quantos parametros de string a mais depois desses dois obrigatorios, serao aceitos -- só pode ter um parametro desse tipo variavel e tem que ser o ultimo
    //ele é como se fosse um vetor - vai ser processado como um

    {
        Object objetoRetorno = null; //mais tarde vai ser retornado

        try
        {
            /*  é o que o for debaixo faz
            for (int i=0; i<parametros.length; i++)
                urlWebService = urlWebService + "/" + parametros[i].replaceAll(" ", "%20"); //os parametros que estiverem em branco serão completados com %20, pq urls não podem ter espaços em branco, então fica url/%20
            */
            //esse for coloca os parametros na url
            for (String parametro : parametros) //para cada string parametro dentro dos parametros (como um vetor):
                urlWebService = urlWebService + "/" + parametro.replaceAll(" ", "%20"); //os parametros que estiverem em branco serão completados com %20, pq urls não podem ter espaços em branco, então concatena ficando url/%20


            //declaro e instancio um objeto da classe url, passando como parametro a string que foi gerada no for
            URL url = new URL (urlWebService);

            //declaro um objeto chamado de connection do tipo HttpURLConnection, inatacio fazendo url.openConnection() e faz um cast:
            //representa a conexão com o servidor que hospeda o web service
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            //métodos para fazer requisições em http: get, post, delet, put
            //uso o connection feito anteriormente para usar essa conexão
            connection.setRequestMethod("GET"); //escolho o metodo get - obter dados a partir da conexao
            connection.setConnectTimeout(15000); // tempo maximo de espera para a conexao - espero ate 15 segundos
            //connection.setRequestProperty("login", "seulogin"); se precisasse de autenticação seria isso
            //connection.setRequestProperty("senha", "suasenha");
            connection.connect(); //acontece a conexão com requisição do tipo get

            String responseJson = inputStreamToString(connection.getInputStream()); //recebe Json e guardo ele numa String
            //se printasse aqui, veriamos exatamente a mesma url de quando pesquisamos no navegador
            connection.disconnect(); //disconecto, porque já estou com o json

            objetoRetorno = fromJson(responseJson, tipoObjetoRetorno);//fromJson: mapeia o json nm objeto ; (responseJson -- passo o json que estava na variavel logo acima , tipoObjetoRetorno -- e o tipo do objeto (nesse caso WS.Logradouro.class))
        }
        catch (Exception erro)
        {
            erro.printStackTrace();
        }

        return objetoRetorno;
    }

    //método postObjeto recebe um objeto, de que classe ele é e a url e vai mandar lá para o web service (nesse programa não está sendo usado)
    public static Object postObjeto (Object objetoEnvio,
                                     Class tipoObjetoRetorno,
                                     String urlWebService)
    {
        Object objetoRetorno = null;

        try
        {
            String requestJson = toJson(objetoEnvio);

            URL url = new URL(urlWebService);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);
            //connection.setRequestProperty("login", "seulogin");
            //connection.setRequestProperty("senha", "suasenha");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(requestJson.length()));

            DataOutputStream stream =
                    new DataOutputStream (connection.getOutputStream());
            stream.write (requestJson.getBytes("UTF-8"));
            stream.flush ();
            stream.close ();
            connection.connect ();

            String responseJson = inputStreamToString (connection.getInputStream());
            connection.disconnect();
            objetoRetorno = fromJson (responseJson, tipoObjetoRetorno);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return objetoRetorno;
    }


    //a partir daqui métodos auxiliares para os dois de cima

    //esse pega o Json enviado, coloca numa string e retorna
    public static String inputStreamToString (InputStream is) throws IOException
    {
        if (is != null)
        {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try
            {
                Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1)
                {
                    writer.write(buffer, 0, n);
                }
            }
            finally
            {
                is.close();
            }

            return writer.toString();
        }
        else
        {
            return "";
        }
    }


    //esse vai mapear um objeto Java para json e retornar uma string contendo esse json
    public static String toJson(Object objeto) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter jsonValue = new StringWriter();
        mapper.writeValue(new PrintWriter(jsonValue), objeto);
        return jsonValue.toString();
    }

    //esse vai mapear um objeto Json para java e retornar uma string contendo esse json
    public static Object fromJson(String json, Class objectClass) throws Exception
    {
        JsonFactory f = new MappingJsonFactory();
        JsonParser jp = f.createJsonParser(json);
        Object obj = jp.readValueAs(objectClass);
        return obj;
    }
}
