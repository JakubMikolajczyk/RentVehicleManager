import java.util.HashSet;

public class Client {
    String id = "Id";
    String name = "Name";
    String surname = "Surname";
    Date birthDate = new Date(0,0,0);
    address address = new address();


    class address{
        String zipCode = "ZIP code";
        String street = "Street";
        String city = "City";
        int buildingNumber = 0;
        int apartmentNumber = 0;

    }


}
