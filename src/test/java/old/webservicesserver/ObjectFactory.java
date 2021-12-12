
package old.webservicesserver;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dataaccess.webservicesserver package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dataaccess.webservicesserver
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link old.webservicesserver.NumberToWords }
     * 
     */
    public old.webservicesserver.NumberToWords createNumberToWords() {
        return new old.webservicesserver.NumberToWords();
    }

    /**
     * Create an instance of {@link old.webservicesserver.NumberToWordsResponse }
     * 
     */
    public old.webservicesserver.NumberToWordsResponse createNumberToWordsResponse() {
        return new old.webservicesserver.NumberToWordsResponse();
    }

    /**
     * Create an instance of {@link NumberToDollars }
     * 
     */
    public NumberToDollars createNumberToDollars() {
        return new NumberToDollars();
    }

    /**
     * Create an instance of {@link NumberToDollarsResponse }
     * 
     */
    public NumberToDollarsResponse createNumberToDollarsResponse() {
        return new NumberToDollarsResponse();
    }

}
