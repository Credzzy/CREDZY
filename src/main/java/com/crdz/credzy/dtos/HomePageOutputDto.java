package com.crdz.credzy.dtos;


import com.crdz.credzy.model.Advertisements;
import com.crdz.credzy.model.Categories;
import com.crdz.credzy.model.CrazyDeals;
import lombok.Data;

import java.util.List;

@Data
public class HomePageOutputDto {

//    all values will come as per city selected.

    private List<CrazyDeals> crazyDeals;
    private List<Advertisements> ads;
    private String city;
    private List<TopBrandOutputDto> topBrands;
    private List<Categories> categories;

//    future enhancements. will see if required then add later
//    private String offers;

}
