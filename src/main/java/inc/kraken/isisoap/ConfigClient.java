/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inc.kraken.isisoap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 *
 * @author carlosndiaye
 */
@Configuration
public class ConfigClient {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("inc.kraken.wsdl");
        return marshaller;
    }

    @Bean
    public PersonClient personClient(Jaxb2Marshaller marshaller) {
        PersonClient client = new PersonClient();
        client.setDefaultUri("https://isisoap.herokuapp.com/api/persons.wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
