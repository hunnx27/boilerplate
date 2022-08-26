package com.onz.modules.review.web.dto;

import com.onz.common.enums.YN;
import com.onz.modules.account.domain.Account;
import com.onz.modules.company.domain.Company;

import java.time.ZonedDateTime;

public interface ReviewResponseDto {
    Long getid();
    YN getis_delete();
    String getModified_at();
    String getstate();
    String getCreated_at();
    YN getWork_exp_open_yn();
    String getItem_1();
    YN getitem_2();
    YN getItem_3();
    String getitem_4();
    String getitem_5();
    YN getItem_6();
    Long getCompany_Id();
    Long getAccount_Id();
    String gettxt_admin();
    Long getwork_exp();
    String getzonecode();
    Long getamt();
    Long getamt_old();
    String getgetappr_dt();
    String getappr_id();
    String getappr_txt();
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


}