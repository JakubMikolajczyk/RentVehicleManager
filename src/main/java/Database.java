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
        private HashMap<String, String> rentRegistryPlate;      //<registryPlate, id>
        private HashMap<String, ArrayList<String>> rentId;       //<Id, registryPlates[]>

        public Database(){
                saved = true;
                idInDatabase = new HashSet<>();
                clients = new HashSet<>();
                registrationPlateInDatabase = new HashSet<>();
                trucks = new HashSet<>();
                cars = new HashSet<>();
                rentRegistryPlate = new HashMap<>();
                rentId = new HashMap<>();
        }

        boolean hasRentVehicle(String id){
                return rentId.containsKey(id);
        }

        boolean isVehicleRent(String registryPlate){
                return rentRegistryPlate.containsKey(registryPlate);
        }

        public void rent(String id, String registryPlate){

                saved = false;
                rentRegistryPlate.put(registryPlate,id);

                ArrayList <String> list= rentId.get(id);

                if (list == null)
                        list = new ArrayList<>();

                list.add(registryPlate);
                rentId.put(id,list);
        }

        public ArrayList<Client> searchClientInDatabase(ClientCompare clientCompare){

                ArrayList<Client> list = new ArrayList<>();

                for(Client it : clients){
                        if (clientCompare.compare(it))
                                list.add(it);
                }

                return list;

        }

//        private void

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

        public void editInDatabase(Client old, Client newClient){

                saved = false;
                clients.remove(old);
                clients.add(newClient);

        }

        public void editInDatabase(Truck old, Truck newTruck){

                saved = false;
                trucks.remove(old);
                trucks.add(newTruck);

        }

        public void editInDatabase(Car old, Car newCar){

                saved = false;
                cars.remove(old);
                cars.add(newCar);

        }



        public boolean idCheck(String id){         //Check
                return idInDatabase.contains(id);
        }       //true if id is in database

        public boolean registrationPlateCheck(String plate){
                return registrationPlateInDatabase.contains(plate);
        }
}
