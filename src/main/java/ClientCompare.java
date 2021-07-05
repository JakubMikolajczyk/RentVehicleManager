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

        if (birthDateParam == 0){
            return intCompare(client.birthDate.year, fromDatabase.year, birthDateParam)
                    && intCompare(client.birthDate.month,fromDatabase.month,birthDateParam)
                    && intCompare(client.birthDate.day, fromDatabase.day, birthDateParam);
        }

        if(birthDateParam == 1 || birthDateParam == 3){
            return intCompare(client.birthDate.year, fromDatabase.year, birthDateParam)
                    || intCompare(client.birthDate.month,fromDatabase.month,birthDateParam)
                    || intCompare(client.birthDate.day, fromDatabase.day, birthDateParam);
        }

        if (birthDateParam == 2 || birthDateParam == 4){

            if (intCompare(client.birthDate.year, fromDatabase.year, 0))
                if (intCompare(client.birthDate.month,fromDatabase.month,0))
                    if (intCompare(client.birthDate.day, fromDatabase.day, 0))
                        return true;
                    else
                        return intCompare(client.birthDate.day, fromDatabase.day, birthDateParam);
                else
                    return intCompare(client.birthDate.month,fromDatabase.month,birthDateParam);
            else
                return intCompare(client.birthDate.year, fromDatabase.year, birthDateParam);

        }

        return false;

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
