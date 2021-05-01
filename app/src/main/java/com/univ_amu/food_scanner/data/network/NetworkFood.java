package com.univ_amu.food_scanner.data.network;

import androidx.lifecycle.LiveData;

import com.univ_amu.food_scanner.data.Quantity;

import java.util.List;

public class NetworkFood {
    String code;
    String name;
    String brands;
    String nutriscore;
    List<NetworkQuantity> quantities;

   /* public String toString(){
        String res="[";
        res+= "code :"+ code;
        res+=", name :"+ name ;
        res+= ", brands :"+  brands;
        res+=", nutriscore :"+ nutriscore ;
        res+=quantities;
        res+="]";
        return res;
    }
*/
    public static class NetworkQuantity {
        String name;
        double quantity;
        int rank;
        int level;
        String unit;

        public String toString(){
          /*  String res="[ ";
            res+=", name :"+ name ;
            res+= ", quantity :" + quantity;
            res+=", rank :"+ rank ;
            res+=", level :"+ level ;
            res+=", unit :"+ unit ;
            res+="]";
            return res;*/
            return name + "rank : "+rank+ " level : "+level +quantity+unit;
        }
    }
}