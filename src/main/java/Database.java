import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Database implements Serializable {

        transient boolean saved;
        private HashSet <Client> clients;
        private HashSet <Car> cars;
        private HashSet <Truck> trucks;
        private HashSet <String> idInDatabase;
        private HashSet <String> registrationPlateInDatabase;
        private HashMap<String, String> rentRegistrationPlate;      //<registrationPlate, id>
        private HashMap<String, HashSet<String>> rentId;       //<Id, registrationPlates[]>

        public Database(){
                saved = true;
                idInDatabase = new HashSet<>();
                clients = new HashSet<>();
                registrationPlateInDatabase = new HashSet<>();
                trucks = new HashSet<>();
                cars = new HashSet<>();
                rentRegistrationPlate = new HashMap<>();
                rentId = new HashMap<>();
        }

        boolean hasRentVehicle(String id){
                return rentId.containsKey(id);
        }

        boolean isVehicleRent(String registrationPlate){
                return rentRegistrationPlate.containsKey(registrationPlate);
        }

        public void rent(String id, String registrationPlate){

                saved = false;
                rentRegistrationPlate.put(registrationPlate,id);

                HashSet<String> hashSet= rentId.get(id);

                if (hashSet == null)
                        hashSet = new HashSet<>();

                hashSet.add(registrationPlate);
                rentId.put(id,hashSet);
        }

        public ArrayList<Client> searchClientInDatabase(ClientCompare clientCompare){

                ArrayList<Client> list = new ArrayList<>();

                for(Client it : clients){
                        if (clientCompare.compare(it))
                                list.add(it);
                }

                return list;

        }



        public void addToDatabase(Client newC){

                saved = false;
                clients.add(newC);
                idInDatabase.add(newC.id);
        }

        public void addToDatabase(Car newC){

                saved = false;
                cars.add(newC);
                registrationPlateInDatabase.add(newC.registrationPlate);
        }


        public void addToDatabase(Truck newT){

                saved = false;
                trucks.add(newT);
                registrationPlateInDatabase.add(newT.registrationPlate);

        }

        private void changeRentId(String oldId,String newId){

                HashSet <String> hashSet = rentId.get(oldId);
                rentId.remove(oldId);
                rentId.put(newId, hashSet);

                for (String it : hashSet)
                        rentRegistrationPlate.put(it, newId);       //update

        }

        private void changeRentRegistrationPlate(String oldRegistrationPlate, String newRegistrationPlate){

                String id = rentRegistrationPlate.get(oldRegistrationPlate);
                rentRegistrationPlate.remove(oldRegistrationPlate);
                rentRegistrationPlate.put(newRegistrationPlate,id);

                HashSet<String> hashSet = rentId.get(id);
                hashSet.remove(oldRegistrationPlate);
                hashSet.add(newRegistrationPlate);

                rentId.put(id,hashSet);

        }

        public void editInDatabase(Client oldClient, Client newClient){

                if (hasRentVehicle(oldClient.id))
                        changeRentId(oldClient.id,newClient.id);        //changing in rent

                saved = false;
                clients.remove(oldClient);
                clients.add(newClient);


        }

        public void editInDatabase(Truck oldTruck, Truck newTruck){

                if (isVehicleRent(oldTruck.registrationPlate))
                        changeRentRegistrationPlate(oldTruck.registrationPlate,newTruck.registrationPlate);

                saved = false;
                trucks.remove(oldTruck);
                trucks.add(newTruck);

        }

        public void editInDatabase(Car oldCar, Car newCar){

                if (isVehicleRent(oldCar.registrationPlate))
                        changeRentRegistrationPlate(oldCar.registrationPlate,newCar.registrationPlate);
                saved = false;
                cars.remove(oldCar);
                cars.add(newCar);

        }



        public boolean idCheck(String id){         //Check
                return idInDatabase.contains(id);
        }       //true if id is in database

        public boolean registrationPlateCheck(String plate){
                return registrationPlateInDatabase.contains(plate);
        }
}
