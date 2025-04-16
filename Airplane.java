import java.util.List;

class airplane {
    private Time timer; // You need to instantiate timer in the constructor
    private States.E_Countries destination_;
    private States.E_Countries departure_;
    private int[] speed = {0}; // moving at what speed decrease at runways taxiways max in air
    private static int counter; // to keep track of all airplanes
    private int id; // current airplane id
    private java.util.List<States.E_Countries> shortest_path;  // gives the path required to get from departure to depot
    private int cost;        // cost of the shortest path 
    private States.E_AirplaneState AirplaneStates; // refers to what the airplane is currently doing moving, stationed, slowing down

    public airplane(){
        id = counter++;
        timer = new Time(); 
        AirplaneStates = States.E_AirplaneState.Grounded;
        
    }
    public airplane(States.E_Countries departure,States.E_Countries distination,int total_cost) {
        this.departure_ = departure;
        this.destination_ = distination;
        cost = total_cost;
        id = counter++;
        timer = new Time(); 
    }

    public int getid() {
        return id;
    }
    
    private void retain_state(){
        timer.add_time(5);
    }
    
    public States.E_Countries departure_(){
        return departure_;
    }
    
    public States.E_Countries destination_(){
        return destination_;
    }
    
    public java.util.List<States.E_Countries> path(){
        return shortest_path;
    }
    
    public double cost(){
        return cost;
    }

    public void incrementspeed() {
        speed[0]++;
    }
    private int getinitialsec(){
      return timer.getinitial_sec();
    }
    
    private int getinitialmin(){
      return timer.getinitial_min();
    }
    
    int getsec(){
        return timer.getSec();
    }

    public void decrementspeed() {
        speed[0]--;
    }

    public Time gettimeclass(){
        return timer;
    }
    public int gettime() {
        return timer.getSec();
    }

    public int getspeed() {
        return speed[0];
    }
    
    public States.E_AirplaneState getStatus_(){
        return AirplaneStates;
    }
    public String planedistancetime(Countries_runway_gateway_status Status){
        
        timer.decrementTime();
        if (timer.getSec() >= timer.getinitial_sec() - 10 && timer.getSec() <= (timer.getinitial_sec()-5) && timer.getMin() == timer.getinitial_min()) {
            Status.updateGatewayStatus(departure_, States.E_GatewayState.Occupied, this);
            if(Status.getOccupyingAirplane_Gateway(departure_) == this){
                AirplaneStates = States.E_AirplaneState.Departure_gateway;
            }else{
                retain_state();
            }
        }
        else if (timer.getSec() >= timer.getinitial_sec() - 15 && timer.getSec() <= (timer.getinitial_sec()-10) && timer.getMin() == timer.getinitial_min()) {
            Status.removeAirplaneGateway(departure_, this);
            Status.updateRunwayStatus(departure_, States.E_RunwayState.Occupied, this);
            if(Status.getOccupyingAirplane_Runway(departure_) == this){
                AirplaneStates = States.E_AirplaneState.Departure_runway;
            }else{
                retain_state();
            }
       } 
        else if (timer.getMin() == 0 && timer.getSec() <= 5 && timer.getSec() > 0) {
           Status.removeAirplaneRunway(destination_, this);
           Status.updateGatewayStatus(destination_, States.E_GatewayState.Occupied, this);
            if(Status.getOccupyingAirplane_Gateway(destination_) == this){
                AirplaneStates = States.E_AirplaneState.Destination_gateway;
            }else{
                retain_state();
            }
        } 
        else if (timer.getMin() == 0 && timer.getSec() >= 5 && timer.getSec() <= 10) {
            Status.updateRunwayStatus(destination_, States.E_RunwayState.Occupied, this);
            if(Status.getOccupyingAirplane_Runway(destination_) == this){
                AirplaneStates = States.E_AirplaneState.Destination_runway;
            }else{
                retain_state();
            }
        } 
        else if (timer.getMin() == 0 && timer.getSec() > 10 && timer.getSec() <= 30) {
            AirplaneStates = States.E_AirplaneState.Descending;  // 20 
        }
        else if((timer.getSec()==0 && timer.getMin()==0)||(timer.getSec()>timer.getinitial_sec()-5&&timer.getSec()<timer.getinitial_sec())){ 
            if((timer.getSec()==0 && timer.getMin()==0)){
                Status.removeAirplaneGateway(destination_, this);
            }
            AirplaneStates = States.E_AirplaneState.Grounded; // 5
        }
        else {
            AirplaneStates = States.E_AirplaneState.Mid_air;  // cost
            Status.removeAirplaneRunway(departure_, this);
        }
        return timer.display(); 
    }
    
    public void setdeparture(States.E_Countries country){
        departure_ = country;
    }
    
    public void setdestination(States.E_Countries country){
        destination_ = country;
    }

    public String planetimer() {
        timer.stopwatch();
        return timer.display();
    }
    
    public void calculate_shortest_path(){
        if(departure_!= States.E_Countries.none && destination_ != States.E_Countries.none){
        greedy_alg calculator = new greedy_alg();
        calculator.addAirplane(new airplane(States.E_Countries.NZ, States.E_Countries.Australia, 500));     // in this function we are calculating shortest path by
        calculator.addAirplane(new airplane(States.E_Countries.Australia, States.E_Countries.Europe, 1000)); // instantiating diffferent plane class by giving
        calculator.addAirplane(new airplane(States.E_Countries.Europe, States.E_Countries.Iceland, 300));   //  them different places with different departures,destination and cost
        calculator.addAirplane(new airplane(States.E_Countries.Pakistan,States.E_Countries.Australia,300));   //  for the greedy method algorithm to kick in
        calculator.addAirplane(new airplane(States.E_Countries.Indonasia,States.E_Countries.Iceland,600));
        
       java.util.List<States.E_Countries> bestpath = calculator.calculateBestPath(departure_, destination_);
       shortest_path = bestpath;
       cost = calculator.calculateTotalCost(bestpath);
       if(cost!=0){
       timer.setsec(45+(cost/10));
       }
        }else{
            AirplaneStates = States.E_AirplaneState.Grounded;
        }
    }
}
