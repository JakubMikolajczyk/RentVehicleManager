public class ClientCompare extends ParamCompare{

    private Client client;
    int idParam,
            nameParam,
            surnameParam,
            birthDateParam;
    addressParam addressParam = new addressParam();

    class addressParam{
        int zipCodeParam,
                streetParam,
                cityParam,
                buildingNumberParam,
                apartmentNumberParam;
    }

    public ClientCompare(Client client){
        this.client = client;
    }

    public boolean dateCompare(Date fromDatabase){
        //TODO dateCompare
        return true;
    }

    public boolean compare(Client fromDatabase){
        return stringCompare(client.name,fromDatabase.name,nameParam)
                && stringCompare(client.surname,fromDatabase.surname,surnameParam)
                && stringCompare(client.id, fromDatabase.id,idParam)
                && dateCompare(fromDatabase.birthDate)
                && stringCompare(client.address.zipCode, fromDatabase.address.zipCode,addressParam.zipCodeParam)
                && stringCompare(client.address.city, fromDatabase.address.city,addressParam.cityParam)
                && intCompare(client.address.buildingNumber, fromDatabase.address.buildingNumber,addressParam.buildingNumberParam)
                && intCompare(client.address.apartmentNumber, fromDatabase.address.apartmentNumber,addressParam.apartmentNumberParam);

    }

}
