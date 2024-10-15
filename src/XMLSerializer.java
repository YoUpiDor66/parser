import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class XMLSerializer {
    public static void serialize(Library library, String filePath) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("library");
        doc.appendChild(rootElement);

        for (Book book : library.getBooks()) {
            Element bookElement = doc.createElement("book");
            bookElement.setAttribute("id", String.valueOf(book.getId()));

            Element titleElement = doc.createElement("title");
            titleElement.appendChild(doc.createTextNode(book.getTitle()));
            bookElement.appendChild(titleElement);

            Element authorElement = doc.createElement("author");
            authorElement.appendChild(doc.createTextNode(book.getAuthor()));
            bookElement.appendChild(authorElement);

            Element yearElement = doc.createElement("year");
            yearElement.appendChild(doc.createTextNode(String.valueOf(book.getYear())));
            bookElement.appendChild(yearElement);

            Element genreElement = doc.createElement("genre");
            genreElement.appendChild(doc.createTextNode(book.getGenre()));
            bookElement.appendChild(genreElement);

            Element priceElement = doc.createElement("price");
            priceElement.setAttribute("currency", book.getPrice().getCurrency());
            priceElement.appendChild(doc.createTextNode(String.valueOf(book.getPrice().getAmount())));
            bookElement.appendChild(priceElement);

            if (book.getIsbn() != null) {
                Element isbnElement = doc.createElement("isbn");
                isbnElement.appendChild(doc.createTextNode(book.getIsbn()));
                bookElement.appendChild(isbnElement);
            }

            if (book.getFormat() != null) {
                Element formatElement = doc.createElement("format");
                formatElement.appendChild(doc.createTextNode(book.getFormat()));
                bookElement.appendChild(formatElement);
            }

            if (book.getPublisher() != null) {
                Element publisherElement = doc.createElement("publisher");
                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(book.getPublisher().getName()));
                publisherElement.appendChild(nameElement);

                Element addressElement = doc.createElement("address");
                Element cityElement = doc.createElement("city");
                cityElement.appendChild(doc.createTextNode(book.getPublisher().getAddress().getCity()));
                addressElement.appendChild(cityElement);

                Element countryElement = doc.createElement("country");
                countryElement.appendChild(doc.createTextNode(book.getPublisher().getAddress().getCountry()));
                addressElement.appendChild(countryElement);

                publisherElement.appendChild(addressElement);
                bookElement.appendChild(publisherElement);
            }

            if (!book.getReviews().isEmpty()) {
                Element reviewsElement = doc.createElement("reviews");
                for (Review review : book.getReviews()) {
                    Element reviewElement = doc.createElement("review");

                    Element userElement = doc.createElement("user");
                    userElement.appendChild(doc.createTextNode(review.getUser()));
                    reviewElement.appendChild(userElement);

                    Element ratingElement = doc.createElement("rating");
                    ratingElement.appendChild(doc.createTextNode(String.valueOf(review.getRating())));
                    reviewElement.appendChild(ratingElement);

                    Element commentElement = doc.createElement("comment");
                    commentElement.appendChild(doc.createTextNode(review.getComment()));
                    reviewElement.appendChild(commentElement);

                    reviewsElement.appendChild(reviewElement);
                }
                bookElement.appendChild(reviewsElement);
            }

            if (book.getLanguage() != null) {
                Element languageElement = doc.createElement("language");
                languageElement.appendChild(doc.createTextNode(book.getLanguage()));
                bookElement.appendChild(languageElement);
            }

            if (book.getTranslator() != null) {
                Element translatorElement = doc.createElement("translator");
                translatorElement.appendChild(doc.createTextNode(book.getTranslator()));
                bookElement.appendChild(translatorElement);
            }

            if (!book.getAwards().isEmpty()) {
                Element awardsElement = doc.createElement("awards");
                for (String award : book.getAwards()) {
                    Element awardElement = doc.createElement("award");
                    awardElement.appendChild(doc.createTextNode(award));
                    awardsElement.appendChild(awardElement);
                }
                bookElement.appendChild(awardsElement);
            }

            rootElement.appendChild(bookElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));

        transformer.transform(source, result);
    }
}
