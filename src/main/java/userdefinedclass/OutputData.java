package userdefinedclass;

import java.math.BigDecimal;

public class OutputData {
    BigDecimal speed;
    BigDecimal pressure;
    BigDecimal temp;

    public OutputData(BigDecimal s, BigDecimal p, BigDecimal t){
        speed = s;
        pressure = p;
        temp = t;
    }

    public BigDecimal getSpeed() {
        return speed;
    }
    public BigDecimal getPressure() {
        return pressure;
    }
    public BigDecimal getTemp() {
        return temp;
    }

}