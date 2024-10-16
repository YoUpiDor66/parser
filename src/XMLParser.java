import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    public static List<Book> parseXML(String filePath) throws IOException {
        //Метод для парсинга XML-файла
        List<Book> books = new ArrayList<>();
        Book currentBook = null;
        Publisher currentPublisher = null;
        Address currentAddress = null;
        Review currentReview = null;
        List<Review> currentReviews = null;
        List<String> currentAwards = null;

        //Чтение файла построчно
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            //Парсинг строк XML
            if (line.startsWith("<book")) {
                currentBook = new Book();
                String id = line.split("\"")[1];
                currentBook.setId(Integer.parseInt(id));
            } else if (line.startsWith("<title>")) {
                currentBook.setTitle(line.substring(7, line.length() - 8));
            } else if (line.startsWith("<author>")) {
                currentBook.setAuthor(line.substring(8, line.length() - 9));
            } else if (line.startsWith("<year>")) {
                currentBook.setYear(Integer.parseInt(line.substring(6, line.length() - 7)));
            } else if (line.startsWith("<genre>")) {
                currentBook.setGenre(line.substring(7, line.length() - 8));
            } else if (line.startsWith("<price")) {
                String currency = line.split("\"")[1];
                String price = line.split(">")[1].split("<")[0];
                currentBook.setPrice(Double.parseDouble(price));
                currentBook.setCurrency(currency);
            } else if (line.startsWith("<isbn>")) {
                currentBook.setIsbn(line.substring(6, line.length() - 7));
            } else if (line.startsWith("<format>")) {
                currentBook.setFormat(line.substring(8, line.length() - 9));
            } else if (line.startsWith("<publisher>")) {
                currentPublisher = new Publisher();
            } else if (line.startsWith("<name>")) {
                currentPublisher.setName(line.substring(6, line.length() - 7));
            } else if (line.startsWith("<address>")) {
                currentAddress = new Address();
            } else if (line.startsWith("<city>")) {
                currentAddress.setCity(line.substring(6, line.length() - 7));
            } else if (line.startsWith("<country>")) {
                currentAddress.setCountry(line.substring(9, line.length() - 10));
            } else if (line.startsWith("<reviews>")) {
                currentReviews = new ArrayList<>();
            } else if (line.startsWith("<review>")) {
                currentReview = new Review();
            } else if (line.startsWith("<user>")) {
                currentReview.setUser(line.substring(6, line.length() - 7));
            } else if (line.startsWith("<rating>")) {
                currentReview.setRating(Integer.parseInt(line.substring(8, line.length() - 9)));
            } else if (line.startsWith("<comment>")) {
                currentReview.setComment(line.substring(9, line.length() - 10));
            } else if (line.startsWith("<language>")) {
                currentBook.setLanguage(line.substring(10, line.length() - 11));
            } else if (line.startsWith("<translator>")) {
                currentBook.setTranslator(line.substring(12, line.length() - 13));
            } else if (line.startsWith("<awards>")) {
                currentAwards = new ArrayList<>();
            } else if (line.startsWith("<award>")) {
                currentAwards.add(line.substring(7, line.length() - 8));
            } else if (line.startsWith("</book>")) {
                books.add(currentBook);
            } else if (line.startsWith("</publisher>")) {
                currentPublisher.setAddress(currentAddress);
                currentBook.setPublisher(currentPublisher);
            } else if (line.startsWith("</review>")) {
                currentReviews.add(currentReview);
            } else if (line.startsWith("</reviews>")) {
                currentBook.setReviews(currentReviews);
            } else if (line.startsWith("</awards>")) {
                currentBook.setAwards(currentAwards);
            }
        }
        reader.close();
        return books;
    }

    public static void main(String[] args) {
        try {
            List<Book> books = parseXML("library.xml");
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
