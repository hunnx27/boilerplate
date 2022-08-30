package com.onz.modules.review.domain.dto;

import com.onz.common.enums.YN;

public interface ReviewAll {
    String getType();
    Long getId();
    YN getis_delete();
    String getmodified_at();
    String getstate();
    String getCreated_at();
    YN getwork_exp_open_yn();
    String getItem_1();
    YN getItem_2();
    YN getItem_3();
    String getItem_4();
    String getItem_5();
    YN getItem_6();
    String gettopQ1();
    Long getcompany_id();
    Long getaccount_id();
    String gettxt_admin();
    Long getwork_exp();
    String getZonecode();
    Long getamt();
    Long getamt_old();
    String getappr_dt();
    String getappr_txt();
    String getappr_id();
    String getend_atm_yn();
    String getetc_amt();
    String getetc_items();
    String getetc_yn();
    String getmapsidogungu_name();
    String gettopchoice_dt();
    YN gettopchoice_yn();
    YN getann_yn();
    String getimage1();
    String getimage2();
    String getimage3();
    String getimage4();
    String getimage5();
    String getitem_b1();
    String getitem_b2();
    String getitem_b3();
    String getitem_c1();
    String getitem_c2();
    String getitem_c3();
    String getitem_d1();
    String getitem_d2();
    String getlike_code();
    String getimpCost();
    String getworkCost();
    String getaddCost();
    String getetcCost();
    Long gettotalCost();
    String getTxt();


//
//    void setImporCost(String value);
//    void setWorkCost(String value);
//    void setAddCost(String value);
//    void setEtcCost(String value);
//    void setTotalCost(Long value);

}