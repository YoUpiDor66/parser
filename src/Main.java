public class Main {
    public static void main(String[] args) {
        try {
            Library library = XMLParser.parse("library.xml");
            System.out.println(library.toString());

            XMLSerializer.serialize(library, "output.xml");

            boolean isValid = XMLValidator.validate("output.xml", "library.xsd");
            System.out.println("XML is valid: " + isValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
