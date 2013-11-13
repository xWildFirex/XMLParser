

public class DataCountries {

    String countryCode;
    String countryName;
    String continentName;
    String capital;
    Integer areaInSqKm;
    Integer population;

    public DataCountries(String countryCode, String countryName, String continentName, String capital) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.continentName = continentName;
        this.capital = capital;
    }

    public DataCountries() {
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Integer getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(Integer areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

}
