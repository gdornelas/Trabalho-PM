package controller;

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

public class XmlReader {

    final String PREMIO = "PREMIO-TITULO";
    final String NOME = "NOME-DO-PREMIO-OU-TITULO";
    final String ANO = "ANO-DA-PREMIACAO";

    final String DADOSGERAIS = "DADOS-GERAIS";
    final String NOMECANDIDATO = "NOME-COMPLETO";


    final String MESTRADO = "MESTRADO";
    final String NOMEINSTIUICAO = "NOME-INSTITUICAO";
    final String ANOCONCLUSAO = "ANO-DE-CONCLUSAO";

    public List<Premio> readPremio(String arquivo){
        List<Premio> premios = new ArrayList<>();

        try{
            //Criação fábrica
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Setando leitor de eventos XML
            InputStream inputStream = new FileInputStream(arquivo);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
            //Leitura do arquivo XML
            Premio premio = null;

            while (eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();

                if(event.isStartElement()){
                    StartElement startElement = event.asStartElement();
                    //Caso encontre a tag no XML, cria um novo Prêmio
                    if (startElement.getName().getLocalPart().equals(PREMIO)){
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
        return premios;
    }

    public Vinculo readMestrado(String arquivo){

        Vinculo vinculo = null;
        try{
            //Criação fábrica
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Setando leitor de eventos XML
            InputStream inputStream = new FileInputStream(arquivo);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);

            //Leitura do arquivo XML
            while (eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();

                if(event.isStartElement()){
                    StartElement startElement = event.asStartElement();
                    //Caso encontre a tag no XML
                    if (startElement.getName().getLocalPart().equals(MESTRADO)){
                        //Leitura do atributo
                        Iterator<Attribute> attributeIterator = startElement.getAttributes();
                        while(attributeIterator.hasNext()){
                            Attribute attribute = attributeIterator.next();
                            if(attribute.getName().toString().equals(NOMEINSTIUICAO)){
                                //Caso o valor do atributo seja a UNIRIO, adiciona o vínculo
                                if (attribute.getValue().equals("Universidade Federal do Estado do Rio de Janeiro")){
                                    vinculo = new Vinculo();
                                    vinculo.setTipo("Mestrado");
                                }else{
                                    break;
                                }
                                if(attribute.getName().toString().equals(ANOCONCLUSAO)){
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

    public String readNome(String arquivo){

        String nome = null;

        try{
            //Criação fábrica
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Setando leitor de eventos XML
            InputStream inputStream = new FileInputStream(arquivo);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
            //Leitura do arquivo XML

            while (eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();

                if(event.isStartElement()){
                    StartElement startElement = event.asStartElement();
                    //Encontra o elemento no XML
                    if (startElement.getName().getLocalPart().equals(DADOSGERAIS)){
                        //Leitura do atributo do elemento
                        Iterator<Attribute> attributeIterator = startElement.getAttributes();
                        while(attributeIterator.hasNext()){
                            Attribute attribute = attributeIterator.next();
                            if(attribute.getName().toString().equals(NOMECANDIDATO)){
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

}
