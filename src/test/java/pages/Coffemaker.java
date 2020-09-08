package pages;

public class Coffemaker {

    protected String brandName;

    protected String[] type = {"Dripper", "French press", "Espresso"};

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

    public void setBrandName(String brand) {
        this.brandName = brand.toUpperCase();
    }













}
