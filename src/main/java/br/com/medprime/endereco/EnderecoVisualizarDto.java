package br.com.medprime.endereco;

public record EnderecoVisualizarDto(String logradouro, String numero, String complemento, String bairro, String cidade
                                    ,String uf, String cep) {

    public EnderecoVisualizarDto(Endereco endereco){
        this(endereco.getLogradouro(),endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCidade(),
                endereco.getUf(), endereco.getCep());
    }

}


