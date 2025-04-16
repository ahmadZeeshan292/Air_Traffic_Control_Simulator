public class TextfieldProperties {
    
    int size = 1;
    boolean begin_button_pressed = false;
    
   Countries_runway_gateway_status Contries_Status = new Countries_runway_gateway_status();
    
    JFrame frame = new JFrame();
    JFrame begin_frame = new JFrame();
    
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel begin_panel = new JPanel();

    JLabel label = new JLabel("Size:");
    
    JLabel [] labels;
    JTextField text = new JTextField();
    JLabel[] plane_timer_labels;
    JLabel[] plane_timer_display_labels;

    JButton start = new JButton("start");
    JButton begin = new JButton("begin");
    
    JLabel[] departure_label;
    JLabel[] destination_label;
    
    JComboBox<States.E_Countries>[]departure;  
    JComboBox<States.E_Countries>[]destination;
    
    JLabel[] path_label;
    JLabel[] path;
    
    JLabel[] cost_label;
    JLabel[] cost;
    
    JLabel[] speed_label;
    JLabel[] speed;
    
    JLabel[] status_label;
    JLabel[] status;
    
    JLabel Global_clock_label_display = new JLabel("Clock: ");
    JLabel Global_clock_label = new JLabel();
    
    Time global_clock = new Time();
    Timer Global_clock_timer;

    Timer[] planeclocktimer;
    Timer[] planespeedtimer;
    Timer GUI_timer;
    
    //Timer Status_timer;
    
    airplane[] planes ;

    public TextfieldProperties() {
        frame.setTitle("AirPlane Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().add(panel);
        
        panel.setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 5));
        panel.setLayout(new GridLayout(0, 2));
        
        begin.addActionListener(new beginbutton());
        start.addActionListener(new startbutton(text));
 
        panel1.setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 5));
        panel.add(label);
        
        //text = createLimitedDigitTextField(3);
        
        panel.add(text);
        panel.add(start);
        frame.pack();
        
        
        
        GUI_timer = new Timer(3000,new ActionListener(){
            @Override
        public void actionPerformed(ActionEvent e){
        if(begin_button_pressed){
        
        for(int i=0;i<size;i++){  
             if(planeclocktimer[i]==null || !planeclocktimer[i].isRunning()){
                 if(planes[i]!= null){
                   planeclocktimer[i] = startSwingTimer_plane(planeclocktimer[i],1000,plane_timer_display_labels[i],planes[i]);
                  }
               }
             
             if(Global_clock_timer == null || !Global_clock_timer.isRunning()){
              Global_clock_timer = startSwingTimer(Global_clock_timer,1000,Global_clock_label,global_clock);
               }
            }

        /*if(Status_timer == null || !Status_timer.isRunning()){
              Status_timer = startSwingTimer_Status(Status_timer);
               }*/
        for(int i=0;i<size;i++){
            if(planes[i] != null){
            status[i].setText(""+planes[i].getStatus_());
            }
        }
        
        for(int i=0;i<size;i++){
             status_report(i);  // for speed
           }
        }
    }
        });
        GUI_timer.start();
    }
    
    /*private Timer startSwingTimer_Status(Timer a) {
        a = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<size;i++){
            if(planes[i] != null){
            status[i].setText(""+planes[i].getStatus_());
            }
        }
            }
        });

        a.start();
        return a;
    }*/
    
    private Timer startSwingTimer(Timer a, int delay, JLabel label, Time time) {
        a = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time.stopwatch();
                label.setText(time.display());
            }
        });

        a.start();
        return a;
    }

     private Timer startSwingTimer_plane(Timer a, int delay, JLabel label, airplane plane) {
        a = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(plane.planedistancetime(Contries_Status));
            }
        });

        a.start();
        return a;
    }
     
    private Timer startSwingTimer(Timer a, int delay, JLabel label, airplane planes) {
        
        a = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(planes.getStatus_() == States.E_AirplaneState.Grounded){
                    label.setText("-");
                }else if(planes.getStatus_() == States.E_AirplaneState.Mid_air || planes.getStatus_() == States.E_AirplaneState.Departure_gateway || planes.getStatus_() == States.E_AirplaneState.Departure_runway){
                    if(planes.getspeed()<170){
                    planes.incrementspeed();
                    }
                    label.setText(""+planes.getspeed());
                }else if(planes.getStatus_() == States.E_AirplaneState.Descending || planes.getStatus_() == States.E_AirplaneState.Destination_gateway || planes.getStatus_() == States.E_AirplaneState.Destination_runway){
                    if(planes.getspeed()>0){
                    planes.decrementspeed();
                    }
                    label.setText(""+planes.getspeed());
                }
            }
        });

        a.start();
        return a;
    }

    private Timer stopSwingTimer(Timer a) {
        if (a != null && a.isRunning()) {
            a.stop();
        }
        return a;
    }
    
    class beginbutton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(!begin_button_pressed){
            begin_frame.setVisible(true);
            begin_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            begin_frame.add(begin_panel);
        
            status_label = new JLabel[size];
            status = new JLabel[size];
        
            cost_label = new JLabel[size];
            cost = new JLabel[size];
        
            speed_label = new JLabel[size];
            speed = new JLabel[size];
        
            path_label = new JLabel[size];
            path = new JLabel[size];
        
            begin_button_pressed = true;
     
            for(int i=0;i<size;i++){
                if(departure[i].getSelectedIndex() != 0 && destination[i].getSelectedIndex() != 0){
                    planes[i] = new airplane();
                    planes[i].setdeparture((States.E_Countries)departure[i].getSelectedItem());
                    planes[i].setdestination((States.E_Countries)destination[i].getSelectedItem());
                    planes[i].calculate_shortest_path();
                }
            }
        
            begin_panel.setLayout(new GridBagLayout());
        
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            int X=0;
            int Y=0;
        
            for (int i = 0; i < size; i++) {
                if(i%3==0 && i!=1 && i!=0){
                   X=0;
                   Y+=7;
                }
            labels[i] = new JLabel("airplane "+(i+1)+" : ");
            gbc.gridx = X;
            gbc.gridy = Y;
            begin_panel.add(labels[i],gbc);
            X+=2;
            }
            X=-1;Y=1;
            for (int i = 0; i < size; i++) {
                if(i%3==0 && i!=1 && i!=0){
                  X=-1;
                  Y+=7;
                }
            plane_timer_labels[i] = new JLabel("Timer: ");
            plane_timer_display_labels[i] = new JLabel("00:00:00");
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            begin_panel.add(plane_timer_labels[i],gbc);
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            begin_panel.add(plane_timer_display_labels[i],gbc);
            }
            X=-1;Y=2;
            for(int i=0;i<size;i++){
            
               if(i%3==0 && i!=1 && i!=0){
                X=-1;
                Y+=7;
             }
            path_label[i] = new JLabel("Path: ");
            path[i] = new JLabel();
            if(planes[i] != null){
            path[i].setText(""+planes[i].path());
            }else{
                path[i].setText("[ ]");
            }
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            begin_panel.add(path_label[i],gbc);
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            begin_panel.add(path[i],gbc);
            
         }
            X=-1;Y=3;
            for(int i=0;i<size;i++){
                if(i%3==0 && i!=1 && i!=0){
                    X=-1;
                    Y+=7;
                }
            cost_label[i] = new JLabel("Cost: ");
            cost[i] = new JLabel();
            if(planes[i] != null){
                cost[i].setText(""+planes[i].cost());
            }else{
                cost[i].setText("-");
            }
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            begin_panel.add(cost_label[i],gbc);
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            begin_panel.add(cost[i],gbc);
            }
            X=-1;Y=4;
            for(int i=0;i<size;i++){
                if(i%3==0 && i!=1 && i!=0){
                    X=-1;
                    Y+=7;
                }
            status_label[i] = new JLabel("Status: ");
            status[i] = new JLabel("-");
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            begin_panel.add(status_label[i],gbc);
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            begin_panel.add(status[i],gbc);
            }
            X=-1;Y=5;
            for(int i=0;i<size;i++){
                if(i%3==0 && i!=1 && i!=0){
                    X=-1;
                    Y+=7;
                }
            speed_label[i] = new JLabel("Speed: ");
            speed[i] = new JLabel("-");
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            begin_panel.add(speed_label[i],gbc);
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            begin_panel.add(speed[i],gbc);
            }
            X=-size;Y++;
            if(size%3==0){
                X+=1;
            }
            for(int i=0;i<size*3;i++){
                if(i == size%2+(size/2)*2){
                    X++;
                    gbc.gridx = X;
                    gbc.gridy = Y;
                    begin_panel.add(Global_clock_label_display,gbc);
                    X++;
                    gbc.gridx = X;
                    gbc.gridy = Y;
                    begin_panel.add(Global_clock_label,gbc);
                }else{
                    X++;
                }
            }
            begin_panel.revalidate();
            begin_panel.repaint(); 
        
            begin_frame.pack();
        }else{
            for(int i=0;i<size;i++){
                if(departure[i].getSelectedIndex() != 0 && destination[i].getSelectedIndex() != 0){
                    if(planes[i] == null){
                        planes[i] = new airplane();
                        planes[i].setdeparture((States.E_Countries)departure[i].getSelectedItem());
                        planes[i].setdestination((States.E_Countries)destination[i].getSelectedItem());
                        planes[i].calculate_shortest_path();
                        path[i].setText(""+planes[i].path());
                        cost[i].setText(""+planes[i].cost());
                    }
                }
            }
        }
    }
}
    
    class startbutton implements ActionListener {
    JTextField value ;
    
    startbutton(JTextField text){
    value = text;
    }
    @Override
    public void actionPerformed(ActionEvent e) {      // start button sets up departure and destination combo boxs in
        
        int a=1;
        if(value.getText().matches("[0-9]+")){
        a = Integer.parseInt(value.getText());  // panel alongside the begin button while removing the previous frame
        }
        
        size = a;
        planes = new airplane[a];
        planeclocktimer = new Timer[a];
        planespeedtimer = new Timer[a];
        labels = new JLabel[a];
        plane_timer_labels = new JLabel[a];
        plane_timer_display_labels = new JLabel[a];
        departure = new JComboBox[a];
        destination = new JComboBox[a];
        destination_label = new JLabel[a];
        departure_label = new JLabel[a];
        
        
        frame.remove(panel);
        frame.add(panel1);
        
        
        panel1.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        int X=0;
        int Y=0;
        
        
        for(int i=0;i<a;i++){
            if(i%3==0 && i!=1 && i!=0){
                X=0;
                Y+=3;
            }
            labels[i] = new JLabel("airplane "+(i+1)+" : ");
            gbc.gridx = X;
            gbc.gridy = Y;
            panel1.add(labels[i],gbc);
            X+=2;
        }
        X=-1;Y=1;
        for(int i=0;i<a;i++){
            if(i%3==0 && i!=1 && i!=0){
                X=-1;
                Y+=3;
            }
            departure_label[i] = new JLabel("departure: ");
            departure[i] = new JComboBox<>(States.E_Countries.values());
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            panel1.add(departure_label[i],gbc);
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            panel1.add(departure[i],gbc);
        }
        X=-1;Y=2;
        for(int i=0;i<a;i++){
            if(i%3==0 && i!=1 && i!=0){
                X=-1;
                Y+=3;
            }
            gbc.gridx = X;
            gbc.gridy = Y;
            destination_label[i] = new JLabel("destination: ");
            destination[i] = new JComboBox<>(States.E_Countries.values());
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            panel1.add(destination_label[i],gbc);
            X++;
            gbc.gridx = X;
            gbc.gridy = Y;
            panel1.add(destination[i],gbc);
        }
        gbc.gridx = 0;
        gbc.gridy = Y+1;
        panel1.add(begin,gbc);
        frame.pack();
    }
}
    public void status_report(int i){   // the status report function alters the speed according to the status
            if(planes[i] != null){
                if(planes[i].getStatus_() == States.E_AirplaneState.Grounded){
                    
                    if(planespeedtimer[i] == null || !planespeedtimer[i].isRunning()){
                       planespeedtimer[i] = startSwingTimer(planespeedtimer[i],1000,speed[i],planes[i]); // keeping in mind of the 20% rule
                    }
                }else if(planes[i].getStatus_() == States.E_AirplaneState.Departure_runway){
                      planespeedtimer[i] = stopSwingTimer(planespeedtimer[i]);
                    if(planespeedtimer[i]== null || !planespeedtimer[i].isRunning()){ // where stopswingtime is used the delay is 20% more than it appears 
                       planespeedtimer[i] = startSwingTimer(planespeedtimer[i],600,speed[i],planes[i]);
                      }
                }else if(planes[i].getStatus_() == States.E_AirplaneState.Departure_gateway){
                      planespeedtimer[i] = stopSwingTimer(planespeedtimer[i]);
                    if(planespeedtimer[i] == null || !planespeedtimer[i].isRunning()){
                       planespeedtimer[i] = startSwingTimer(planespeedtimer[i],1000,speed[i],planes[i]);
                      }    
                }else if(planes[i].getStatus_() == States.E_AirplaneState.Mid_air){
                     planespeedtimer[i] = stopSwingTimer(planespeedtimer[i]);
                    if(planespeedtimer[i] == null || !planespeedtimer[i].isRunning()){
                       planespeedtimer[i] = startSwingTimer(planespeedtimer[i],140,speed[i],planes[i]);
                    }
                }else if(planes[i].getStatus_() == States.E_AirplaneState.Descending){
                     planespeedtimer[i]= stopSwingTimer(planespeedtimer[i]);
                    if(planespeedtimer[i] == null || !planespeedtimer[i].isRunning()){
                       planespeedtimer[i] = startSwingTimer(planespeedtimer[i],130,speed[i],planes[i]);
                    }
                }else if(planes[i].getStatus_() == States.E_AirplaneState.Destination_runway){
                     planespeedtimer[i] = stopSwingTimer(planespeedtimer[i]);
                    if(planespeedtimer[i] == null || !planespeedtimer[i].isRunning()){
                       planespeedtimer[i] = startSwingTimer(planespeedtimer[i],600,speed[i],planes[i]);
                    }
                }else if(planes[i].getStatus_() == States.E_AirplaneState.Destination_gateway){
                     planespeedtimer[i] = stopSwingTimer(planespeedtimer[i]);
                    if(planespeedtimer[i] == null || !planespeedtimer[i].isRunning()){
                       planespeedtimer[i] = startSwingTimer(planespeedtimer[i],1000,speed[i],planes[i]);
                    }
                }
            }
        }
        /*protected static JTextField createLimitedDigitTextField(int maxlength){
        JTextField TextField = new JTextField();
        PlainDocument docu = (PlainDocument)TextField.getDocument();
        docu.setDocumentFilter(new DigitDocumentFilter(maxlength));
        return TextField;
        }*/
}
