package XML_handler;

import java.io.IOException;

import org.xml.sax.*;
import org.xml.sax.helpers.*;





import nu.xom.*;

public class XML_reader {

    Document doc;
    Builder builder;
	
	public XML_reader(String xMLmessage){


      	builder = new Builder();

			   

		
        
		try {
			doc = builder.build(xMLmessage, null);
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Document getXMLDocument(){
		return doc;
	}
	
}
