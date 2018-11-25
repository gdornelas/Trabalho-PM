package controller;

import model.Premio;

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

public class Reader {

    static final String PREMIO = "PREMIO-TITULO";
    static final String NOME = "NOME-DO-PREMIO-OU-TITULO";

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
                    //Se tivermos um elemento premio, criamos um novo
                    if (startElement.getName().getLocalPart().equals(PREMIO)){
                        premio = new Premio();
                        //Leitura Attr
                        Iterator<Attribute> attributeIterator = startElement.getAttributes();
                        while(attributeIterator.hasNext()){
                            Attribute attribute = attributeIterator.next();
                            if(attribute.getName().toString().equals(NOME)){
                                premio.setNome(attribute.getValue());
                            }
                        }
                    }
                    //resto
                }
                //Fim do arquivo
                if(event.isEndElement()){
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(PREMIO)){
                        premios.add(premio);
                    }
                }


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e){
            e.printStackTrace();
        }
        return premios;
    }

}
