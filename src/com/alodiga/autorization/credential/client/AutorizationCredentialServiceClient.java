/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alodiga.autorization.credential.client;

import com.alodiga.autorization.credential.response.CardToCardTransferResponse;
import com.alodiga.atorization.utils.Constants;
import com.alodiga.autorization.credential.response.DispertionResponse;
import com.alodiga.autorization.credential.response.LastMovementsResponse;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author ltoro
 */
public class AutorizationCredentialServiceClient {

    private static final int CONNECTION_TIMEOUT = 5000;
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        AutorizationCredentialServiceClient autorizationCredentialServiceClient = new AutorizationCredentialServiceClient();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        SimpleDateFormat sdg = new SimpleDateFormat("yyyyMMdd");
        String hour = sdf.format(timestamp);
        System.out.println(hour);
        String date = sdg.format(timestamp);
        System.out.println(date);

        CardToCardTransferResponse credentialServiceClient = new AutorizationCredentialServiceClient().cardToCardTransfer(date, hour, "2308840100400161", "2308840100107501", "1");
        //DispertionResponse dispertionResponse = new AutorizationCredentialServiceClient().dispertionTransfer(date, hour, "2308840100107501", "");
        System.out.println(credentialServiceClient.getCodigoError());
    }

    public CardToCardTransferResponse cardToCardTransfer(String date, String hour, String numberCardOrigin, String numberCardDestinate, String balance) throws MalformedURLException, IOException, Exception {
        String formattedSOAPResponse = "";

        try {

            ignoreSSL();
            String responseString = "";
            String outputString = "";
            String wsEndPoint = Constants.URL_PROD;
            URL url = new URL(wsEndPoint);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            httpConn.setConnectTimeout(CONNECTION_TIMEOUT);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.synapse/xsd\">");
            builder.append("<soapenv:Header/>");
            builder.append("<soapenv:Body>");
            builder.append("<xsd:Autorizar>");
            builder.append("<xsd:Canal>" + Constants.USER_PROD + "</xsd:Canal>");
            builder.append("<xsd:TipoTransaccion>" + Constants.NUMBER_TRANSACTION_TYPE_CARD_TO_CARD + "</xsd:TipoTransaccion>");
            builder.append("<xsd:FechaTransaccion>" + date + "</xsd:FechaTransaccion>");
            builder.append("<xsd:HoraTransaccion>" + hour + "</xsd:HoraTransaccion>");
            builder.append("<xsd:Terminal>" + Constants.IP_NUMBER_TEST + "</xsd:Terminal>");
            builder.append("<xsd:ModoIngreso>" + Constants.NUMBER_ENTRY_MODE + "</xsd:ModoIngreso>");
            builder.append("<xsd:Tarjeta>" + numberCardOrigin + "</xsd:Tarjeta>");
            builder.append("<xsd:CodigoMoneda>" + Constants.CURRENCY_CODE + "</xsd:CodigoMoneda>");
            builder.append("<xsd:Importe>" + balance + "</xsd:Importe>");
            builder.append("<xsd:TarjetaDestino>" + numberCardDestinate + "</xsd:TarjetaDestino>");
            builder.append("<xsd:NombreComercio>" + Constants.CONCEPT_TRANSFER + "</xsd:NombreComercio>");
            builder.append("<xsd:SubTipoTransaccion>" + Constants.NUMBER_SUB_TRANSACTION_TYPE_CARD_TO_CARD + "</xsd:SubTipoTransaccion>");
            builder.append("</xsd:Autorizar>");
            builder.append("</soapenv:Body>");
            builder.append("</soapenv:Envelope>");
            byte[] buffer = new byte[builder.toString().length()];
            buffer = builder.toString().getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            String SOAPAction = "Consultar";
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            // Write the content of the request to the outputstream of the HTTP
            // Connection.
            out.write(b);
            out.close();
            // Ready with sending the request.
            // Read the response.
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            // Write the SOAP message response to a String.

            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            formattedSOAPResponse = formatXML(outputString);
            System.out.println(outputString);
            CardToCardTransferResponse cardToCardTransferResponse = new CardToCardTransferResponse();
            try {
                cardToCardTransferResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                cardToCardTransferResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                cardToCardTransferResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                cardToCardTransferResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));
                cardToCardTransferResponse.setCodigoAutorizacion(getTagValue("ns:CodigoAutorizacion", formattedSOAPResponse));
                cardToCardTransferResponse.setSaldoPosterior(getTagValue("ns:SaldoPosterior", formattedSOAPResponse));
                cardToCardTransferResponse.setSaldo(getTagValue("ns:Saldo", formattedSOAPResponse));
                cardToCardTransferResponse.setSaldoPosteriorCuentaDestino(getTagValue("ns:SaldoPosteriorCuentaDestino", formattedSOAPResponse));
                cardToCardTransferResponse.setSaldoCuentaDestino(getTagValue("ns:SaldoCuentaDestino", formattedSOAPResponse));

            } catch (ArrayIndexOutOfBoundsException e) {
                cardToCardTransferResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                cardToCardTransferResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                cardToCardTransferResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                cardToCardTransferResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));

            }

            return new CardToCardTransferResponse(cardToCardTransferResponse.getCodigoError(), cardToCardTransferResponse.getMensajeError(), cardToCardTransferResponse.getCodigoRespuesta(), cardToCardTransferResponse.getMensajeRespuesta(), cardToCardTransferResponse.getCodigoAutorizacion(), cardToCardTransferResponse.getSaldoPosterior(), cardToCardTransferResponse.getSaldo(), cardToCardTransferResponse.getSaldoPosteriorCuentaDestino(), cardToCardTransferResponse.getSaldoCuentaDestino());

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new MalformedURLException();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

//    public LastMovementsResponse lastMovements() throws MalformedURLException, IOException, Exception {
//        String formattedSOAPResponse = "";
//
//        try {
//
//            ignoreSSL();
//            String responseString = "";
//            String outputString = "";
//            String wsEndPoint = Constants.URL_PROD;
//            URL url = new URL(wsEndPoint);
//            URLConnection connection = url.openConnection();
//            HttpURLConnection httpConn = (HttpURLConnection) connection;
//            ByteArrayOutputStream bout = new ByteArrayOutputStream();
//            StringBuilder builder = new StringBuilder("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsd=\"http://org.apache.synapse/xsd\">");
//            builder.append("<soapenv:Header/>");
//            builder.append("<soapenv:Body>");
//            builder.append("<xsd:Autorizar>");
//            builder.append("<xsd:Canal>PilotoWS</xsd:Canal>");
//            builder.append("<xsd:TipoTransaccion>129</xsd:TipoTransaccion>");
//            builder.append("<xsd:SecuenciaTransaccion>1234</xsd:SecuenciaTransaccion>");
//            builder.append("<xsd:FechaTransaccion>20200122</xsd:FechaTransaccion>");
//            builder.append("<xsd:HoraTransaccion>112021</xsd:HoraTransaccion>");
//            builder.append("<xsd:Comercio>12345</xsd:Comercio>");
//            builder.append("<xsd:Terminal>192.168.3.20</xsd:Terminal>");
//            builder.append("<xsd:ModoIngreso>1</xsd:ModoIngreso>");
//            builder.append("<xsd:Tarjeta>2308840100400161</xsd:Tarjeta>");
//            builder.append("<xsd:CodigoMoneda>484</xsd:CodigoMoneda>");
//            builder.append("<xsd:NombreComercio>consulta de saldo</xsd:NombreComercio>");
//            builder.append("<xsd:SubTipoTransaccion>1</xsd:SubTipoTransaccion>");
//            builder.append("</xsd:Autorizar>");
//            builder.append("</soapenv:Body>");
//            builder.append("</soapenv:Envelope>");            
//            byte[] buffer = new byte[builder.toString().length()];
//            buffer = builder.toString().getBytes();
//            bout.write(buffer);
//            byte[] b = bout.toByteArray();
//            String SOAPAction = "Consultar";
//            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
//            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
//            httpConn.setRequestProperty("SOAPAction", SOAPAction);
//            httpConn.setRequestMethod("POST");
//            httpConn.setDoOutput(true);
//            httpConn.setDoInput(true);
//            OutputStream out = httpConn.getOutputStream();
//            // Write the content of the request to the outputstream of the HTTP
//            // Connection.
//            out.write(b);
//            out.close();
//            // Ready with sending the request.
//            // Read the response.
//            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
//            BufferedReader in = new BufferedReader(isr);
//            // Write the SOAP message response to a String.
//
//            while ((responseString = in.readLine()) != null) {
//                outputString = outputString + responseString;
//            }
//            formattedSOAPResponse = formatXML(outputString);
//            
//            LastMovementsResponse lastMovementsResponse = new LastMovementsResponse();
//            
//            
//            return new CardToCardTransferResponse(cardToCardTransferResponse.getCodigoError(), cardToCardTransferResponse.getMensajeError(), cardToCardTransferResponse.getCodigoRespuesta(), cardToCardTransferResponse.getMensajeRespuesta(), cardToCardTransferResponse.getCodigoAutorizacion(), cardToCardTransferResponse.getSaldoPosterior(), cardToCardTransferResponse.getSaldo(), cardToCardTransferResponse.getSaldoPosteriorCuentaDestino(), cardToCardTransferResponse.getSaldoCuentaDestino());
//
//        } catch (MalformedURLException ex) {
//            ex.printStackTrace();
//            throw new MalformedURLException();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            throw new IOException();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new Exception();
//        }
//    }
    public DispertionResponse dispertionTransfer(String date, String hour, String numberCard, String balance) throws MalformedURLException, IOException, Exception {
        String formattedSOAPResponse = "";

        try {

            ignoreSSL();
            String responseString = "";
            String outputString = "";
            String wsEndPoint = Constants.URL_PROD;
            URL url = new URL(wsEndPoint);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://org.apache.synapse/xsd\">");
            builder.append("<soapenv:Header/>");
            builder.append("<soapenv:Body>");
            builder.append("<xsd:Autorizar>");
            builder.append("<xsd:Canal>" + Constants.USER_PROD + "</xsd:Canal>");
            builder.append("<xsd:TipoTransaccion>" + Constants.NUMBER_TRANSACTION_TYPE_DISPERTION + "</xsd:TipoTransaccion>");
            builder.append("<xsd:FechaTransaccion>" + date + "</xsd:FechaTransaccion>");
            builder.append("<xsd:HoraTransaccion>" + hour + "</xsd:HoraTransaccion>");
            builder.append("<xsd:Terminal>" + Constants.IP_NUMBER_TEST + "</xsd:Terminal>");
            builder.append("<xsd:ModoIngreso>" + Constants.NUMBER_ENTRY_MODE + "</xsd:ModoIngreso>");
            builder.append("<xsd:Tarjeta>" + numberCard + "</xsd:Tarjeta>");
            builder.append("<xsd:CodigoMoneda>" + Constants.CURRENCY_CODE + "</xsd:CodigoMoneda>");
            builder.append("<xsd:Importe>" + balance + "</xsd:Importe>");
            builder.append("<xsd:NombreComercio>" + Constants.CONCEPT_TRANSFER + "</xsd:NombreComercio>");
            builder.append("<xsd:SubTipoTransaccion>" + Constants.NUMBER_SUB_TRANSACTION_TYPE_DISPERTION + "</xsd:SubTipoTransaccion>");
            builder.append("</xsd:Autorizar>");
            builder.append("</soapenv:Body>");
            builder.append("</soapenv:Envelope>");
            byte[] buffer = new byte[builder.toString().length()];
            buffer = builder.toString().getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            String SOAPAction = "Consultar";
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            // Write the content of the request to the outputstream of the HTTP
            // Connection.
            out.write(b);
            out.close();
            // Ready with sending the request.
            // Read the response.
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            // Write the SOAP message response to a String.

            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            formattedSOAPResponse = formatXML(outputString);
            System.out.println(outputString);
            DispertionResponse dispertionResponse = new DispertionResponse();
            try {
                dispertionResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                dispertionResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                dispertionResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                dispertionResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));
                dispertionResponse.setCodigoAutorizacion(getTagValue("ns:CodigoAutorizacion", formattedSOAPResponse));
            } catch (ArrayIndexOutOfBoundsException e) {
                dispertionResponse.setCodigoError(getTagValue("ns:CodigoError", formattedSOAPResponse));
                dispertionResponse.setMensajeError(getTagValue("ns:MensajeError", formattedSOAPResponse));
                dispertionResponse.setCodigoRespuesta(getTagValue("ns:CodigoRespuesta", formattedSOAPResponse));
                dispertionResponse.setMensajeRespuesta(getTagValue("ns:MensajeRespuesta", formattedSOAPResponse));

            }
            return new DispertionResponse(dispertionResponse.getCodigoError(), dispertionResponse.getMensajeError(), dispertionResponse.getCodigoRespuesta(), dispertionResponse.getMensajeRespuesta(), dispertionResponse.getCodigoAutorizacion());

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new MalformedURLException();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    public static String getTagValue(String tagName, String xml) {
        System.out.println("respuesta: " + tagName);
        return xml.split("<" + tagName + ">")[1].split("</" + tagName + ">")[0];
    }

    private static void ignoreSSL() {
        try {
            ////////////////////////////////////////////////////////////////
            //SE COLOCAR PARA IGNORAR SSL
            ///////////////////////////////////////////////////////////////
            XTrustProvider.install();
            final String TEST_URL = "https://10.70.10.71:8000/Autorizacion?wsdl";
            URL url = new URL(TEST_URL);
            HttpsURLConnection httpsCon = (HttpsURLConnection) url.openConnection();
            httpsCon.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            httpsCon.connect();
            InputStream is = httpsCon.getInputStream();
            int nread = 0;
            byte[] buf = new byte[8192];
            while ((nread = is.read(buf)) != -1) {
                //System.out.write(buf, 0, nread);
            }
            ////////////////////////////////////////////////////////////////
            //SE COLOCAR PARA IGNORAR SSL
            ///////////////////////////////////////////////////////////////
        } catch (MalformedURLException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    // format the XML in pretty String
    private static String formatXML(String unformattedXml) {
        try {
            Document document = parseXmlFile(unformattedXml);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 3);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            transformer.transform(source, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    // parse XML

    private static Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

}
