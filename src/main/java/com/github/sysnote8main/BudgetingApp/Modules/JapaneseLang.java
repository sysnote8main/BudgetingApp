package com.github.sysnote8main.BudgetingApp.Modules;

import com.github.sysnote8main.BudgetingApp.Enums.CategoryTypes;
import com.github.sysnote8main.BudgetingApp.Enums.DataType;

import java.util.HashMap;

public class JapaneseLang {
    public static HashMap<String, CategoryTypes> CategoryTranslations = new HashMap<>();
    public static HashMap<DataType, String> DatatypeTranslations = new HashMap<>();
    public static HashMap<DataType, String> ValueTypeTranslations = new HashMap<>();
    public JapaneseLang() {
        // Translations of Category
        CategoryTranslations.put("食費",CategoryTypes.GROCERY_BILLS);
        CategoryTranslations.put("水道代",CategoryTypes.WATER_BILL);
        CategoryTranslations.put("電気代",CategoryTypes.ELECTRIC_BILL);
        CategoryTranslations.put("ガス代",CategoryTypes.GAS_BILL);
        CategoryTranslations.put("通信費",CategoryTypes.TELEPHONE_BILL);
        CategoryTranslations.put("携帯料金",CategoryTypes.MOBILE_PHONE_BILL);
        CategoryTranslations.put("固定電話料金",CategoryTypes.FIXED_PHONE_CHARGES);
        CategoryTranslations.put("日用品",CategoryTypes.DAILY_NECESSITIES);
        CategoryTranslations.put("雑費",CategoryTypes.MISCELLANEOUS_COSTS);

        // Translations of Datatype
        DatatypeTranslations.put(DataType.year,"年");
        DatatypeTranslations.put(DataType.month,"月");
        DatatypeTranslations.put(DataType.day,"日");
    }
}
