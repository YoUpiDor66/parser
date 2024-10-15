import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLParser {
    public static Library parse(String filePath) throws Exception {
        File inputFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        Library library = new Library();
        NodeList bookNodes = doc.getElementsByTagName("book");

        for (int i = 0; i < bookNodes.getLength(); i++) {
            Element bookElement = (Element) bookNodes.item(i);
            Book book = parseBook(bookElement);
            library.addBook(book);
        }

        return library;
    }

    private static Book parseBook(Element bookElement) {
        Book book = new Book();
        book.setId(Integer.parseInt(bookElement.getAttribute("id")));

        Element titleElement = (Element) bookElement.getElementsByTagName("title").item(0);
        book.setTitle(titleElement.getTextContent());

        Element authorElement = (Element) bookElement.getElementsByTagName("author").item(0);
        book.setAuthor(authorElement.getTextContent());

        Element yearElement = (Element) bookElement.getElementsByTagName("year").item(0);
        book.setYear(Integer.parseInt(yearElement.getTextContent()));

        Element genreElement = (Element) bookElement.getElementsByTagName("genre").item(0);
        book.setGenre(genreElement.getTextContent());

        Element priceElement = (Element) bookElement.getElementsByTagName("price").item(0);
        Price price = new Price();
        price.setAmount(Double.parseDouble(priceElement.getTextContent()));
        price.setCurrency(priceElement.getAttribute("currency"));
        book.setPrice(price);

        if (bookElement.getElementsByTagName("isbn").getLength() > 0) {
            Element isbnElement = (Element) bookElement.getElementsByTagName("isbn").item(0);
            book.setIsbn(isbnElement.getTextContent());
        }

        if (bookElement.getElementsByTagName("format").getLength() > 0) {
            Element formatElement = (Element) bookElement.getElementsByTagName("format").item(0);
            book.setFormat(formatElement.getTextContent());
        }

        if (bookElement.getElementsByTagName("publisher").getLength() > 0) {
            Element publisherElement = (Element) bookElement.getElementsByTagName("publisher").item(0);
            Publisher publisher = parsePublisher(publisherElement);
            book.setPublisher(publisher);
        }

        if (bookElement.getElementsByTagName("reviews").getLength() > 0) {
            Element reviewsElement = (Element) bookElement.getElementsByTagName("reviews").item(0);
            NodeList reviewNodes = reviewsElement.getElementsByTagName("review");
            for (int j = 0; j < reviewNodes.getLength(); j++) {
                Element reviewElement = (Element) reviewNodes.item(j);
                Review review = parseReview(reviewElement);
                book.addReview(review);
            }
        }

        if (bookElement.getElementsByTagName("language").getLength() > 0) {
            Element languageElement = (Element) bookElement.getElementsByTagName("language").item(0);
            book.setLanguage(languageElement.getTextContent());
        }

        if (bookElement.getElementsByTagName("translator").getLength() > 0) {
            Element translatorElement = (Element) bookElement.getElementsByTagName("translator").item(0);
            book.setTranslator(translatorElement.getTextContent());
        }

        if (bookElement.getElementsByTagName("awards").getLength() > 0) {
            Element awardsElement = (Element) bookElement.getElementsByTagName("awards").item(0);
            NodeList awardNodes = awardsElement.getElementsByTagName("award");
            for (int j = 0; j < awardNodes.getLength(); j++) {
                Element awardElement = (Element) awardNodes.item(j);
                book.addAward(awardElement.getTextContent());
            }
        }

        return book;
    }

    private static Publisher parsePublisher(Element publisherElement) {
        Publisher publisher = new Publisher();
        Element nameElement = (Element) publisherElement.getElementsByTagName("name").item(0);
        publisher.setName(nameElement.getTextContent());

        Element addressElement = (Element) publisherElement.getElementsByTagName("address").item(0);
        Address address = parseAddress(addressElement);
        publisher.setAddress(address);

        return publisher;
    }

    private static Address parseAddress(Element addressElement) {
        Address address = new Address();
        Element cityElement = (Element) addressElement.getElementsByTagName("city").item(0);
        address.setCity(cityElement.getTextContent());

        Element countryElement = (Element) addressElement.getElementsByTagName("country").item(0);
        address.setCountry(countryElement.getTextContent());

        return address;
    }

    private static Review parseReview(Element reviewElement) {
        Review review = new Review();
        Element userElement = (Element) reviewElement.getElementsByTagName("user").item(0);
        review.setUser(userElement.getTextContent());

        Element ratingElement = (Element) reviewElement.getElementsByTagName("rating").item(0);
        review.setRating(Integer.parseInt(ratingElement.getTextContent()));

        Element commentElement = (Element) reviewElement.getElementsByTagName("comment").item(0);
        review.setComment(commentElement.getTextContent());

        return review;
    }
}
