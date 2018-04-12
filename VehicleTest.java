abstract class Vehicle{
    private String modelName;
    private String company;
    private String owner;
    public String engineType;
    private double tankSize;
    public double fuelConsumption;
    Vehicle(){}
    Vehicle(String modelName, String company, String owner, String engineType, double tankSize, double fuelConsumption){
    this.modelName=modelName;
    this.company=company;
    this.owner=owner;
    this.engineType=engineType;
    this.tankSize=tankSize;
    this.fuelConsumption=fuelConsumption;
    }
    public String toString(){
    return "ModelName: "+modelName+" Company:"+company+" Owner: "+owner+" EngineType: "+engineType+" TankSize: "+tankSize+" FuelComsumption: "+fuelConsumption;
    }
    public double movableDistance(){
    return tankSize*fuelConsumption;
    }
    abstract public double costFor100Km(PetroleumPrice priceInfo);
    abstract void setAirConON();
    abstract void setAirConOFF();

}

class Car extends Vehicle{
  private boolean airConditionOn;
  private int numberOfSeat;

    Car(String modelName, String company, String owner, String engineType, double tankSize, double fuelConsumption, int numberOfSeat){
      super(modelName,company,owner,engineType,tankSize,fuelConsumption);
      setNumberOfSeat(numberOfSeat);
      airConditionOn=false;
    }
    public void setNumberOfSeat(int numberOfSeat){
      this.numberOfSeat=numberOfSeat;
    }

    public double costFor100Km(PetroleumPrice priceInfo){

        return (100/fuelConsumption)*priceInfo.getGasolineCost();
    }
    public void setAirConON(){
      airConditionOn=true;
      fuelConsumption*=0.85;
    }
    public void setAirConOFF(){
      airConditionOn=false;
    }

    public String toString(){
      return super.toString()+" NumberOfSeat: "+numberOfSeat;
    }

}

class MiniVan extends Vehicle{
  private int numberOfSeat;
  private boolean airConditionOn;
  private boolean hasAutoDoor;
    MiniVan(String modelName, String company, String owner, String engineType, double tankSize, double fuelConsumption, int numberOfSeat, boolean hasAutoDoor){
      super(modelName,company,owner,engineType,tankSize,fuelConsumption);
      setNumberOfSeat(numberOfSeat);
      setHasAutodoor(hasAutoDoor);
      airConditionOn=false;
  }
  public void setNumberOfSeat(int numberOfSeat){
    this.numberOfSeat=numberOfSeat;
  }
  public void setHasAutodoor(boolean hasAutoDoor){
    this.hasAutoDoor=hasAutoDoor;
  }
  public String toString(){
    return super.toString()+" NumberOfSeat: "+numberOfSeat+ " HasAutoDoor: "+hasAutoDoor ;
  }

  public double costFor100Km(PetroleumPrice priceInfo) {
      if ( engineType == "Gasoline" ) {
        return (100 / fuelConsumption) * priceInfo.getGasolineCost();
      } else {
        return (100 / fuelConsumption) * priceInfo.getDieselCost();
     }
   }

  public void setAirConON(){
    fuelConsumption*=0.75;
    airConditionOn=true;
  }
  public void setAirConOFF(){
    fuelConsumption/=0.75;
    airConditionOn=false;

  }

  // public String toString(){
  //   return super.toString()+" NumberOfSeat: "+numberOfSeat+ " HasAutoDoor: "+hasAutoDoor ;
  // }

}





class Truck extends Vehicle{
  private int numberOfSeat;
  private boolean airConditionOn;
  private int power;
    Truck(String modelName, String company, String owner, String engineType, double tankSize, double fuelConsumption, int numberOfSeat, int power){
      super(modelName,company,owner,engineType,tankSize,fuelConsumption);
      setNumberOfSeat(numberOfSeat);
      setPower(power);
      airConditionOn=false;
    }
    public void setNumberOfSeat(int numberOfSeat){
      this.numberOfSeat=numberOfSeat;
    }
    public void setPower(int power){
      this.power=power;
    }
    public double costFor100Km(PetroleumPrice priceInfo){
      if(airConditionOn){
        return (100/fuelConsumption)*priceInfo.getDieselCost();
    }else{
        return (100/fuelConsumption)*priceInfo.getDieselCost();
    }
}

    public void setAirConON(){
      fuelConsumption*=0.75;
      airConditionOn=true;
    }
    public void setAirConOFF(){
      fuelConsumption/=0.75;
      airConditionOn=false;
    }

    public String toString(){
      return super.toString()+" NumberOfSeat: "+numberOfSeat+ " HorsePower: "+power;
    }

}

class PetroleumPrice{
  private double gasolinePrice;
  private double dieselPrice;
  PetroleumPrice(double gasolinePrice, double dieselPrice){
    this.gasolinePrice=gasolinePrice;
    this.dieselPrice=dieselPrice;
  }
  public double getGasolineCost(){
    return gasolinePrice;
  }
  public double getDieselCost(){
    return dieselPrice;
  }
}


public class VehicleTest {

    public static void describe(Vehicle v) {
        System.out.println(v);
    }


    public static void main(String[] args) {
        Vehicle vehicles[] = {
         new Car("Camley", "Toyota", "Suzuki", "Gasoline", 70., 15.15, 5),
         new Car("Aqua", "Toyota", "Nakajima", "Hybrid", 36., 40.0, 5),
         new MiniVan("Sienna", "Toyota", "Tanaka", "Gasoline", 75.,  9.0, 8, true),
         new MiniVan("Odyssey", "Honda", "Kikuchi", "Diesel", 56., 11.0, 8, false),
         new MiniVan("Presage", "Nissan", "Shirotora", "Gasoline", 60., 7.0, 7, false),
         new Truck("Tundra", "Toyota", "Sugai", "Diesel", 100., 6.76, 5, 310),
         new Truck("Ridgeline", "Honda", "Watanabe", "Diesel", 83.279, 7.23, 5, 250)
            };

        PetroleumPrice priceInfo = new PetroleumPrice(128.7, 105.7);

        for(Vehicle v: vehicles) {
                  describe(v);
        System.out.println("Movable distance: " + v.movableDistance() + " Km");
        System.out.println("Cost for 100 Km: " + v.costFor100Km(priceInfo) + " Yen");
        System.out.println();
         }

        System.out.println();
        System.out.println("After Aircondition is ON\n");

        for(Vehicle v: vehicles) {
        v.setAirConON();
                  describe(v);
        System.out.println("Movable distance: " + v.movableDistance() + " Km");
        System.out.println("Cost for 100 Km: " + v.costFor100Km(priceInfo) + " Yen");
            System.out.println();
         }

    }  // end of main
}
