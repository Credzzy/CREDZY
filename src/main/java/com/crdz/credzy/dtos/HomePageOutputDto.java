package com.crdz.credzy.dtos;


import com.crdz.credzy.model.Advertisements;
import com.crdz.credzy.model.Categories;
import com.crdz.credzy.model.CrazyDeals;
import lombok.Data;

@Data
public class HomePageOutputDto {

//    all values will come as per city selected.

    private CustomerOutputDto customerOutputDto;
    private CrazyDeals crazyDeals;
    private Advertisements ads;
    private String city;

//    future enhancements. will see if required then add later
//    private String offers;

    private TopBrandOutputDto topBrands;
    private Categories categories;

}
