package controller;

import model.Artigo;
import model.Premio;
import model.Vinculo;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Controlador de leitura dos aquivos XML
 */
public class XmlReader {

    //Nomes das tags no XML para dados dos prêmios
    final String PREMIO = "PREMIO-TITULO";
    final String NOMEPREMIO = "NOME-DO-PREMIO-OU-TITULO";
    final String ANOPREMIO = "ANO-DA-PREMIACAO";

    //Nomes das tags no XML para dados do candidatos
    final String DADOSGERAIS = "DADOS-GERAIS";
    final String NOMECANDIDATO = "NOME-COMPLETO";

    //Nomes das tags no XML para dados de mestrado
    final String MESTRADO = "MESTRADO";
    final String NOMEINSTIUICAO = "NOME-INSTITUICAO";
    final String ANOCONCLUSAOMESTRADO = "ANO-DE-CONCLUSAO";

    //Nomes das tags no XML para dados de trabalhos em eventos
    final String TRABALHOEMEVENTO = "TRABALHO-EM-EVENTOS";
    final String DADOSBASICOSTRABALHO = "DADOS-BASICOS-DO-TRABALHO";
    final String NOMETRABALHO = "TITULO-DO-TRABALHO";
    final String ANOTRABALHO = "ANO-DO-TRABALHO";
    final String DETALHESTRABALHO = "DETALHAMENTO-DO-TRABALHO";
    final String NOMEEVENTO = "NOME-DO-EVENTO";

    //Nomes das tags no XML para dados de trabalhos em periódicos
    final String ARTIGOPUBLICADO = "ARTIGO-PUBLICADO";
    final String DADOSBASICOSARTIGO = "DADOS-BASICOS-DO-ARTIGO";
    final String NOMEARTIGO = "TITULO-DO-ARTIGO";
    final String ANOARTIGO = "ANO-DO-ARTIGO";
    final String DETALHESARTIGO = "DETALHAMENTO-DO-ARTIGO";
    final String NOMEPERIODICO = "TITULO-DO-PERIODICO-OU-REVISTA";

    /**
     * Função de leitura dos prêmios dos candidatos
     * @param arquivo: indicação do xml referente ao lattes do candidato
     * @return: lista de prêmios do candidato
     */
    public List<Premio> readPremio(String arquivo) {
        List<Premio> premios = new ArrayList<>();

        try {
            //Criação fábrica
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Setando leitor de eventos XML
            InputStream inputStream = new FileInputStream(arquivo);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
            //Leitura do arquivo XML
            Premio premio = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    //Caso encontre a tag no XML, cria um novo Prêmio
                    if (startElement.getName().getLocalPart().equals(PREMIO)) {
                        premio = new Premio();
                        //Leitura do atributo do elemento
                        Iterator<Attribute> attributeIterator = startElement.getAttributes();
                        while (attributeIterator.hasNext()) {
                            Attribute attribute = attributeIterator.next();
                            if (attribute.getName().toString().equals(NOMEPREMIO)) {
                                premio.setNome(attribute.getValue());
                            }
                            if (attribute.getName().toString().equals(ANOPREMIO)) {
                                premio.setAno(attribute.getValue());
                            }
                        }
                    }
                }
                //Fim do elemento premio no XML
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    //Adição do prêmio da lista de prêmios do candidato
                    if (endElement.getName().getLocalPart().equals(PREMIO)) {
                        premios.add(premio);
                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return premios;
    }

    /**
     * Função de leitura do mestrado do candidato e verificaão se o mesmo ocorreu na UNIRIO
     * @param arquivo: indicação do xml referente ao lattes do candidato
     * @return vínculo do candidato do tipo mestrado
     */
    public Vinculo readMestrado(String arquivo) {

        Vinculo vinculo = null;
        try {
            //Criação fábrica
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Setando leitor de eventos XML
            InputStream inputStream = new FileInputStream(arquivo);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);

            //Leitura do arquivo XML
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    //Caso encontre a tag no XML
                    if (startElement.getName().getLocalPart().equals(MESTRADO)) {
                        //Leitura do atributo
                        Iterator<Attribute> attributeIterator = startElement.getAttributes();
                        while (attributeIterator.hasNext()) {
                            Attribute attribute = attributeIterator.next();
                            if (attribute.getName().toString().equals(NOMEINSTIUICAO)) {
                                //Caso o valor do atributo seja a UNIRIO, adiciona o vínculo
                                if (attribute.getValue().equals("Universidade Federal do Estado do Rio de Janeiro")) {
                                    vinculo = new Vinculo();
                                    vinculo.setTipo("Mestrado");
                                } else {
                                    break;
                                }
                                if (attribute.getName().toString().equals(ANOCONCLUSAOMESTRADO)) {
                                    vinculo.setAno(attribute.getValue());
                                }
                            }
                        }
                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return vinculo;
    }

    /**
     * Função de leitura do nome do candidato
     * @param arquivo: indicação do xml referente ao lattes do candidato
     * @return nome do candidato
     */
    public String readNome(String arquivo) {

        String nome = null;

        try {
            //Criação fábrica
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Setando leitor de eventos XML
            InputStream inputStream = new FileInputStream(arquivo);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
            //Leitura do arquivo XML

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    //Encontra o elemento no XML
                    if (startElement.getName().getLocalPart().equals(DADOSGERAIS)) {
                        //Leitura do atributo do elemento
                        Iterator<Attribute> attributeIterator = startElement.getAttributes();
                        while (attributeIterator.hasNext()) {
                            Attribute attribute = attributeIterator.next();
                            if (attribute.getName().toString().equals(NOMECANDIDATO)) {
                                nome = attribute.getValue();
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return nome;
    }
/*
    public Vinculo readPesquisa(String arquivo){
        try{
            //Criação fábrica
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Setando leitor de eventos XML
            InputStream inputStream = new FileInputStream(arquivo);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
            //Leitura do arquivo XML
            Vinculo vinculo = null;

            while (eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();

                if(event.isStartElement()){
                    StartElement startElement = event.asStartElement();
                    //Caso encontre a tag no XML, cria um novo Prêmio
                    if (startElement.getName().getLocalPart().equals(INTEGRANTES)){
                        premio = new Premio();
                        //Leitura do atributo do elemento
                        Iterator<Attribute> attributeIterator = startElement.getAttributes();
                        while(attributeIterator.hasNext()){
                            Attribute attribute = attributeIterator.next();
                            if(attribute.getName().toString().equals(NOME)){
                                premio.setNome(attribute.getValue());
                            }
                            if(attribute.getName().toString().equals(ANO)){
                                premio.setAno(attribute.getValue());
                            }
                        }
                    }
                }
                //Fim do elemento premio no XML
                if(event.isEndElement()){
                    EndElement endElement = event.asEndElement();
                    //Adição do prêmio da lista de prêmios do candidato
                    if (endElement.getName().getLocalPart().equals(PREMIO)){
                        premios.add(premio);
                    }
                }


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return vinculo;
    }*/

    /**
     * Função de leitura dos trabalhos apresentados em eventos
     * @param arquivo: indicação do xml referente ao lattes do candidato
     * @return lista de trabalhos apresentados em eventos
     */
    public List<Artigo> readTrabalhosEventos(String arquivo) {
        List<Artigo> artigos = new ArrayList<>();

        try {
            //Criação fábrica
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Setando leitor de eventos XML
            InputStream inputStream = new FileInputStream(arquivo);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
            //Leitura do arquivo XML
            Artigo artigo = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    //Caso encontre a tag no XML, cria um novo Prêmio
                    if (startElement.getName().getLocalPart().equals(TRABALHOEMEVENTO)) {
                        artigo = new Artigo();
                    }
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(DADOSBASICOSTRABALHO)) {
                            //Leitura do atributo do elemento
                            Iterator<Attribute> attributeIterator = startElement.getAttributes();
                            while (attributeIterator.hasNext()) {
                                Attribute attribute = attributeIterator.next();
                                if (attribute.getName().toString().equals(NOMETRABALHO)) {
                                    artigo.setNome(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(ANOTRABALHO)) {
                                    artigo.setAno(attribute.getValue());
                                }
                            }
                        }
                        if (event.asStartElement().getName().getLocalPart().equals(DETALHESTRABALHO)) {
                            //Leitura do atributo do elemento
                            Iterator<Attribute> attributeIterator = startElement.getAttributes();
                            while (attributeIterator.hasNext()) {
                                Attribute attribute = attributeIterator.next();
                                if (attribute.getName().toString().equals(NOMEEVENTO)) {
                                    artigo.setLocal(attribute.getValue());
                                }
                            }
                        }
                    }
                }
                //Fim do elemento artigo no XML
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    //Adição do prêmio da lista de prêmios do candidato
                    if (endElement.getName().getLocalPart().equals(TRABALHOEMEVENTO)) {
                        artigos.add(artigo);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return artigos;
    }

    /**
     * Função de leitura de artigos publicados em revistas e/ou periódicos
     * @param arquivo: indicação do xml referente ao lattes do candidato
     * @return lista de artigos publicados
     */
    public List<Artigo> readArtigoPublicado(String arquivo) {
        List<Artigo> artigos = new ArrayList<>();

        try {
            //Criação fábrica
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Setando leitor de eventos XML
            InputStream inputStream = new FileInputStream(arquivo);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
            //Leitura do arquivo XML
            Artigo artigo = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    //Caso encontre a tag no XML, cria um novo Prêmio
                    if (startElement.getName().getLocalPart().equals(ARTIGOPUBLICADO)) {
                        artigo = new Artigo();
                    }
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(DADOSBASICOSARTIGO)) {
                            //Leitura do atributo do elemento
                            Iterator<Attribute> attributeIterator = startElement.getAttributes();
                            while (attributeIterator.hasNext()) {
                                Attribute attribute = attributeIterator.next();
                                if (attribute.getName().toString().equals(NOMEARTIGO)) {
                                    artigo.setNome(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(ANOARTIGO)) {
                                    artigo.setAno(attribute.getValue());
                                }
                            }
                        }
                        if (event.asStartElement().getName().getLocalPart().equals(DETALHESARTIGO)) {
                            //Leitura do atributo do elemento
                            Iterator<Attribute> attributeIterator = startElement.getAttributes();
                            while (attributeIterator.hasNext()) {
                                Attribute attribute = attributeIterator.next();
                                if (attribute.getName().toString().equals(NOMEPERIODICO)) {
                                    artigo.setLocal(attribute.getValue());
                                }
                            }
                        }
                    }
                }
                //Fim do elemento artigo no XML
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    //Adição do prêmio da lista de prêmios do candidato
                    if (endElement.getName().getLocalPart().equals(ARTIGOPUBLICADO)) {
                        artigos.add(artigo);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return artigos;
    }
}
