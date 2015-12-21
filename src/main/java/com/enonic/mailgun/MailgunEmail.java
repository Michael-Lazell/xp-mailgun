package com.enonic.mailgun;

import java.io.IOException;
import java.io.StringWriter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mla on 12/15/15.
 */
public class MailgunEmail
{
    private final Logger LOG = LoggerFactory.getLogger( MailgunEmail.class );


    public String sendSimpleMessage( String apiKey, String resourceUrl, String path, String to, String from, String subject, String text,
                                     boolean debug) {

        Client client = ClientBuilder.newClient();

        client.register( HttpAuthenticationFeature.basic( "api", apiKey) );
        //client.register( new Authenticator( "api", apiKey ) );

        WebTarget target = client.target( resourceUrl ).path( path );

        Form form = new Form();
        form.param("from", from);
        form.param("to", to);
        form.param("subject", subject);
        form.param("text", text);

        JSONObject json = new JSONObject();
        Response response;

        if (debug) {
            json.put("success", new Boolean( true ));
            json.put("message", "Simulated sending email.");
        } else {
            response = target.request( MediaType.MULTIPART_FORM_DATA).post( Entity.form( form ) );
            if(response.getStatus() == 200) {
                json.put("success", new Boolean( true ));
                json.put("message", "The email was sent!");
            } else {
                json.put("success", new Boolean( false ));
                json.put("message", "The email was not sent! Status: " + response.getStatus() );
            }
        }

        StringWriter out = new StringWriter();
        try
        {
            json.writeJSONString( out );
        } catch ( IOException e ) {
            LOG.error( "Error writing JSON. " + e.getMessage() );
        }

        return out.toString();
    }
}
