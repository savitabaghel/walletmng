package com.example.walletmng.Controller;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holder {
   // @NotNull
    String payembileno;
   // @NotNull
    String payermobileno;
    double amount;


}
