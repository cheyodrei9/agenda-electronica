/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansCalendario;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author david.floresusam
 */
    @ManagedBean

public class BeanSelect {
    private String[] selectedCities;
    private List<String> cities;
 
    @PostConstruct
    public void init() {
        cities = new ArrayList<String>();
        cities.add("Administrativa");
        cities.add("Religiosa");
        cities.add("Personal");
        cities.add("Judicial");
        
    }
 
    public String[] getSelectedCities() {
        return selectedCities;
    }
 
    public void setSelectedCities(String[] selectedCities) {
        this.selectedCities = selectedCities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
    
    public List<String> getCities() {
        return cities;
    }
 
}
