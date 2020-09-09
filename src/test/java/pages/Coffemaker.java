package pages;

import org.apache.logging.log4j.core.util.JsonUtils;

public class Coffemaker {

    protected String brandName;

    protected String[] type = {"Dripper", "French press", "Espresso"};

    protected boolean powerState;

    protected boolean brewingState;

    protected boolean overheat;

    public boolean isOverheat() {
        return overheat;
    }

    public void setOverheat(boolean overheatOn) {
        this.overheat = overheatOn;
    }

    public boolean isPowerState() {
        return powerState;
    }

    public void setPowerState(boolean powerSwitch) {
        this.powerState = powerSwitch;
    }

    public boolean isBrewingState() {
        return brewingState;
    }

    public void setBrewingState(boolean brewingSwitch) {
        this.brewingState = brewingSwitch;
    }



    public String getBrandName() {
        return brandName;
    }

    public String getType(int t) {
        String makerType = "";
        switch (t) {
            case 1 -> makerType = type[1];
            case 2 -> makerType = type[2];
            case 3 -> makerType = type[3];
        }
        return makerType + " type";
    }

    public String setBrandName(String brand) {
        this.brandName = brand.toUpperCase();

        return brandName;
    }




    public boolean isCoffeeBrewed (boolean powerState, boolean brewingState, boolean overheat) {
        if ((powerState && brewingState &! overheat)) {

            return true;
        }
            return false;

    }

    public void printBrandName() {
        System.out.println(setBrandName("rowenta"));
    }

}
