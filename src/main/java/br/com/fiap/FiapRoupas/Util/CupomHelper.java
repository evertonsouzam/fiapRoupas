package br.com.fiap.FiapRoupas.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.fiap.FiapRoupas.model.Pedido;
import br.com.fiap.FiapRoupas.model.Produto;

public class CupomHelper {

    // URL do servidor JMSr. DEFAULT_BROKER_URL irá dizer que o server está no localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    //Nome da fila para onde enviaremos a mensagem
    private static String subject = "CUPON";
    
    public void imprimirCupom(Pedido pedido) {
		
		final String QUINZEESPACOS = "               ";
		final String DEZESPACOS = "          ";
		final String MUITOSTRACOS = "--------------------------------------------------------";
		final String CINCOESPACOS = "     ";
		
		Date dataCupom = new Date();
		
		int qtdProd = 0;
		Double valorTotal = 0.0;
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(QUINZEESPACOS + "FIAP ROUPA\n" );
		builder.append(DEZESPACOS + "Av. Lins de Vasconselos\n");
		builder.append(QUINZEESPACOS + "SÃO PAULO / SP\n");
		builder.append("\n");
		builder.append("CNPJ: 111111111.111-11 \n");
		builder.append(MUITOSTRACOS + "\n");
		builder.append(dataCupom.toString() + QUINZEESPACOS + "COO: " + pedido.getCoo() + "\n");
		builder.append("ITEM   CÓDIGO   DESCRIÇÃO  QTD UN. VL UNIT \n");
		builder.append(MUITOSTRACOS + "\n");
		
		for (Produto produto : pedido.getProdutos()) {
			qtdProd++;
			valorTotal += produto.getValor();
			builder.append(qtdProd + produto.getId() + CINCOESPACOS + produto.getDescricao()
			+ CINCOESPACOS + "1" + CINCOESPACOS + produto.getValor() + "\n");
		}
		
		builder.append("\n");
		builder.append("TOTAL R$" + QUINZEESPACOS + valorTotal);
		builder.append("\n");
		builder.append("\n");
		builder.append(pedido.getHash());
		
		System.out.println(builder.toString());
		
		gerarPDFCupom(builder, pedido.getCoo());
		
	}

    public void gerarPDFCupom(StringBuilder builder, String coo) {
	
        Document document = new Document();
        
        File file = new File("D:\\tmp\\nota" + coo + ".pdf");
        
        try {
        	
            PdfWriter.getInstance(document, new FileOutputStream(file));
        	
            document.open();
           
            // adicionando um parágrafo no documento
            document.add(new Paragraph(builder.toString()));
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
        
        
        
        try {
			enviarCupom(file);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }   
    	
    public void enviarCupom(File file) throws JMSException {
    	// Obtem uma conexão com o servidor JMS
    	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
    	Connection connection = connectionFactory.createConnection();
    	connection.start();


    	// 	Mensagens JMS são enviadas usando uma sessão. Nós criaremos aqui uma sessão não transacional, informando false para o primeiro parametro 
    	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

    	//	Destination é a fila para onde as mensagens serão enviadas. Se não existir a fial ela sera criada automaticamente.
    	Destination destination = session.createQueue(subject);
    	MessageProducer producer = session.createProducer(destination);
    	
    	ObjectMessage message = session.createObjectMessage(file);

    	// envia a mensagem
    	producer.send(message);
    	System.out.println("Mensagemada");

    	connection.close();
    }
    
}
