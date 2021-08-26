package WS;

public class Logradouro
{
    private String logradouro; // nome do atributo seguindo o json
    //getter
    public  String getLogradouro ()
    {
        return this.logradouro;
    }
    //setter
    public void setLogradouro (String logradouro) throws Exception
    {
        if (logradouro==null || logradouro.length()==0)
            throw new Exception ("WS.Logradouro ausente");

        this.logradouro = logradouro;
    }

    private String complemento; // nome do atributo seguindo o json
    public  String getComplemento ()
    {
        return this.complemento;
    }
    public void setComplemento (String complemento) throws Exception
    {
        this.complemento = complemento;
    }

    private String bairro; // nome do atributo seguindo o json
    public  String getBairro ()
    {
        return this.bairro;
    }
    public void setBairro (String bairro) throws Exception
    {
        if (bairro==null || bairro.length()==0)
            throw new Exception ("Bairro ausente");

        this.bairro = bairro;
    }

    private String cidade;
    public  String getCidade ()
    {
        return this.cidade;
    }
    public void setCidade (String cidade) throws Exception
    {
        if (cidade==null || cidade.length()==0)
            throw new Exception ("Cidade ausente");

        this.cidade = cidade;
    }


    //informações desse comentário para cima eram simples, abaixo é uma composta/estruturada:

    private InfoCidade cidade_info; //nome do atributo seguindo o Json - por ser uma informação composta é do tipo WS.InfoCidade (classe)
    // é um atributo que possui com atributo: codigo_ibge, area_km2
    public InfoCidade getCidade_info ()
    {
        return (InfoCidade)this.cidade_info.clone();
    }
    public void setCidade_info (InfoCidade infoCidade) throws Exception
    {
        if (infoCidade==null)
            throw new Exception ("Informacao de cidade ausente");

        this.cidade_info = (InfoCidade)infoCidade.clone();
    }

    //informação simples dnv:
    private String estado;
    public  String getEstado ()
    {
        return this.estado;
    }
    public void setEstado (String estado) throws Exception
    {
        if (estado==null || estado.length()==0)
            throw new Exception ("Estado ausente");

        this.estado = estado;
    }

    //informação composta:

    private InfoEstado estado_info; //nome do atributo seguindo o Json - por ser uma informação composta é do tipo WS.InfoCidade (classe)
    // é um atributo que possui com atributo: nome, codigo_ibge, area_km2
    public  InfoEstado getEstado_info ()
    {
        return (InfoEstado)this.estado_info.clone();
    }
    public void setEstado_info (InfoEstado infoEstado) throws Exception
    {
        if (infoEstado==null)
            throw new Exception ("Informacao de estado ausente");

        this.estado_info = (InfoEstado)infoEstado.clone();
    }

    //atributo simples
    private String cep;
    public  String getCep ()
    {
        return this.cep;
    }
    public void setCep (String cep) throws Exception
    {
        if (cep==null || cep.length()==0)
            throw new Exception ("WS.Logradouro ausente");

        this.cep = cep;
    }

    //construtor que recebe valores para todos os atributos do logradouro
    public Logradouro (String complemento, String logradouro, String bairro, String cidade, InfoCidade cidade_info,
                       String estado, InfoEstado estado_info,
                       String cep) throws Exception
    {
        //setters usando os parametros para coloca-los nos atributos
        this.setComplemento (complemento);
        this.setLogradouro  (logradouro);
        this.setBairro      (bairro);
        this.setCidade      (cidade);
        this.setCidade_info (cidade_info);
        this.setEstado      (estado);
        this.setEstado_info (estado_info);
        this.setCep         (cep);
    }

    // exigencia do mapeador de JSon
    public Logradouro () {}


    //metodos obrigatorios
    public String toString ()
    {
        return /*"WS.Logradouro: "+
                this.logradouro+*/
                "\nComplemento: "+
                this.complemento+
                "; cidade: "+
                this.cidade+
                " / "+
                this.cidade_info+
                "\n estado: "+
                this.estado+
                " / "+
                this.estado_info+
                "\nC.E.P: "+
                this.cep;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        //if (!(this.getClass() != obj.getClass())
        //if (!(obj.getClass != WS.Logradouro.class))
        if (!(obj instanceof Logradouro))
            return false;

        Logradouro cep = (Logradouro)obj;

        if (!this.logradouro.equals(cep.logradouro))
            return false;

        if ((this.complemento==null && cep.complemento!=null) ||
                (this.complemento!=null && cep.complemento==null) ||
                !this.complemento.equals(cep.complemento))
            return false;

        if (!this.cidade.equals(cep.cidade))
            return false;

        if (!this.cidade_info.equals(cep.cidade_info))
            return false;

        if (!this.estado.equals(cep.estado))
            return false;

        if (!this.estado_info.equals(cep.estado_info))
            return false;

        if (!this.cep.equals(cep.cep))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=1;

        ret = 2*ret + this.logradouro .hashCode();

        if (this.complemento!=null)
            ret = 2*ret + this.complemento.hashCode();

        ret = 2*ret + this.cidade     .hashCode();
        ret = 2*ret + this.cidade_info.hashCode();
        ret = 2*ret + this.estado     .hashCode();
        ret = 2*ret + this.estado_info.hashCode();
        ret = 2*ret + this.cep        .hashCode();

        return ret;
    }

    public Logradouro (Logradouro modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo inexistente");

        this.logradouro  = modelo.logradouro;
        this.complemento = modelo.complemento;
        this.cidade      = modelo.cidade;
        this.cidade_info = (InfoCidade)modelo.cidade_info.clone();
        this.estado      = modelo.estado;
        this.estado_info = (InfoEstado)modelo.estado_info.clone();
        this.cep         = modelo.cep;
    }

    public Object clone ()
    {
        Logradouro ret=null;

        try
        {
            ret = new Logradouro (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}