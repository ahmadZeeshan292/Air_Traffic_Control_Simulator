class Time {
    private int hour = 0;
    private int[] min = {0,0};
    private int[] sec = {0,0};

    public Time() {
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min[0];
    }

    public int getSec() {
        return this.sec[0];
    }
    public void set(int s){
        sec[0]+=s;
        if (this.sec[0] > 60) {
        this.min[0] = this.sec[0] / 60;
        this.sec[0] = this.sec[0] % 60;
        }
    }
    public void add_time(int sec){
        this.sec[0] += sec;
        if(this.sec[0]>60){
        this.min[0] = this.sec[0] / 60;
        this.sec[0] = this.sec[0] % 60;
        }
    }
    public void setsec(int sec){
        this.sec[0] = sec;
        if (this.sec[0] > 60) {
        this.min[0] = this.sec[0] / 60;
        this.sec[0] = this.sec[0] % 60;

        this.sec[1] = this.sec[0];
        this.min[1] = this.min[0];
       }else{
            this.sec[1] = this.sec[0];
            this.min[1] = this.min[0];
        }
    }
    public int getinitial_sec(){
        return sec[1];
    }
    public int getinitial_min(){
        return min[1];
    }

    public void decrementTime() {
      if(sec[0]!=0||min[0]!=0||hour!=0){
        if (sec[0] == 0) {
            sec[0] = 59;
            if (min[0] == 0) {
                min[0] = 59;
                if (hour > 0) {
                    hour--;
                }
            } else {
                min[0]--;
            }
        } else {
            sec[0]--;
          }
      }
    }

    public String display() {
        return String.format("%02d:%02d:%02d", hour, min[0], sec[0]);
    }

    public void reset() {
        sec[0] = 0;
        min[0] = 0;
        hour= 0;
    }

    public void stopwatch() {
        if (sec[0] == 60) {
            min[0]++;
            sec[0] = 0;
        }
        if (min[0] == 60) {
            hour++;
            min[0] = 0;
        }
        sec[0]++;
    }
}
