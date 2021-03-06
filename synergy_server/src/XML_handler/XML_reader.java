package XML_handler;

import java.io.IOException;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

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
