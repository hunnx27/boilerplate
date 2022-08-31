package com.onz.modules.review.infra;


import com.onz.modules.company.domain.Company;
import com.onz.modules.review.domain.dto.ReviewAll;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Company, Long>,
        ReviewRepositoryExtension {
    //String FIND_DISTINCT_IDS = "SELECT DISTINCT a.sigungu_code as sigunguCode , a.sido_name as sidoName , a.sigungu_name as sigunguName from address a where a.sigungu_code = :sigungu_code";

    String FIND_ALL_IDS =
            "SELECT " +
                    "'COMPANY' AS type, " +
                    "id AS id, " +
                    "is_delete AS isDelete , " +
                    "modified_at AS modifiedAt , " +
                    "state AS state , " +
                    "created_at AS createdAt,  " +
                    "work_exp_open_yn AS workExpOpenYn , " +
                    "company_id AS companyId, " +
                    "account_id  As accountId, " +
                    "txt AS txt , " +
                    "NULL AS item_1 , " +
                    "NULL AS item_2 , " +
                    "NULL AS item_3 , " +
                    "NULL AS item_4 , " +
                    "NULL AS item_5 , " +
                    "NULL AS item_6 , " +
                    "NULL AS topQ1 , " +
                    "NULL AS txtAdmin , " +
                    "work_exp AS workExp , " +
                    "NULL AS zonecode , " +
                    "NULL AS amt , " +
                    "NULL AS amtOld , " +
                    "NULL AS apprDt , " +
                    "NULL AS apprId , " +
                    "NULL AS apprTxt , " +
                    "NULL AS endAtmYn , " +
                    "NULL AS etcAmt , " +
                    "NULL AS etcItems , " +
                    "NULL AS etcYn , " +
                    "NULL AS mapsidogunguName , " +
                    "NULL AS topchoiceDt , " +
                    "NULL AS topchoiceYn , " +
                    "NULL AS annYn , " +
                    "image1 AS image1 , " +
                    "image2 AS image2 , " +
                    "image3 AS image3 , " +
                    "image4 AS image4 , " +
                    "image5 AS image5 , " +
                    "item_b1 AS itemB1 , " +
                    "item_b2 AS itemB2 , " +
                    "item_b3 AS itemB3 ,  " +
                    "item_c1 AS itemC1 , " +
                    "item_c2 AS itemC2 , " +
                    "item_c3 AS itemC3 , " +
                    "item_d1 AS itemD1 , " +
                    "item_d2 AS itemD2 , " +
                    "like_code AS likeCode " +
                    "FROM company_review cr  " +
                    "UNION ALL " +

                    "SELECT " +
                    "'INTERVIEW' AS type, " +
                    "id AS id, " +
                    "is_delete AS is_delete , " +
                    "modified_at AS modified_at , " +
                    "state AS state , " +
                    "created_at AS created_at , " +
                    "work_exp_open_yn AS work_exp_open_yn , " +
                    "company_id AS company_id, " +
                    "account_id  As account_id, " +
                    "null AS txt, " +
                    "item_1 AS item_1 , " +
                    "item_2 AS item_2 , " +
                    "item_3 AS item_3 , " +
                    "item_4 AS item_4 , " +
                    "item_5 AS item_5 , " +
                    "item_6 AS item_6 , " +
                    "topq1 AS topq1 , " +
                    "txt_admin  AS txt_admin , " +
                    "work_exp AS work_exp , " +
                    "zonecode AS zonecode , " +
                    "NULL AS amt , " +
                    "NULL AS amt_old , " +
                    "NULL AS appr_dt , " +
                    "NULL AS appr_id , " +
                    "NULL AS appr_txt , " +
                    "NULL AS end_atm_yn , " +
                    "NULL AS etc_amt , " +
                    "NULL AS etc_items , " +
                    "NULL AS etc_yn , " +
                    "NULL AS mapsidogungu_name , " +
                    "NULL AS topchoice_dt , " +
                    "NULL AS topchoice_yn , " +
                    "NULL AS ann_yn , " +
                    "NULL AS image1 , " +
                    "NULL AS image2 , " +
                    "NULL AS image3 , " +
                    "NULL AS image4 , " +
                    "NULL AS image5 , " +
                    "NULL AS item_b1 , " +
                    "NULL AS item_b2 , " +
                    "NULL AS item_b3 ,  " +
                    "NULL AS item_c1 , " +
                    "NULL AS item_c2 , " +
                    "NULL AS item_c3 , " +
                    "NULL AS item_d1 , " +
                    "NULL AS item_d2 , " +
                    "NULL AS like_code " +
                    "FROM interview_review ir where false " +

                    "UNION ALL " +

                    "SELECT " +
                    "'AMT' AS type, " +
                    "id AS id, " +
                    "is_delete AS is_delete , " +
                    "modified_at AS modified_at , " +
                    "state AS state , " +
                    "created_at AS created_at , " +
                    "work_exp_open_yn AS work_exp_open_yn , " +
                    "company_id AS company_id , " +
                    "account_id  As account_id , " +
                    "null AS txt, " +
                    "NULL AS item_1 , " +
                    "NULL AS item_2 , " +
                    "NULL AS item_3 , " +
                    "NULL AS item_4 , " +
                    "NULL AS item_5 , " +
                    "NULL AS item_6 , " +
                    "NULL AS topq1 , " +
                    "NULL AS txt_admin , " +
                    "work_exp AS work_exp , " +
                    "zonecode AS zonecode , " +
                    "amt AS amt , " +
                    "amt_old AS amt_old , " +
                    "appr_dt AS appr_dt , " +
                    "appr_id AS appr_id , " +
                    "appr_txt AS appr_txt , " +
                    "end_atm_yn AS end_atm_yn , " +
                    "etc_amt AS etc_amt , " +
                    "etc_items AS etc_items , " +
                    "etc_yn AS etc_yn , " +
                    "mapsidogungu_name AS mapsidogungu_name , " +
                    "topchoice_dt AS topchoice_dt , " +
                    "topchoice_yn AS topchoice_yn , " +
                    "ann_yn AS ann_yn , " +
                    "NULL AS image1 , " +
                    "NULL AS image2 , " +
                    "NULL AS image3 , " +
                    "NULL AS image4 , " +
                    "NULL AS image5 , " +
                    "NULL AS item_b1 , " +
                    "NULL AS item_b2 , " +
                    "NULL AS item_b3 ,  " +
                    "NULL AS item_c1 , " +
                    "NULL AS item_c2 , " +
                    "NULL AS item_c3 , " +
                    "NULL AS item_d1 , " +
                    "NULL AS item_d2 , " +
                    "NULL AS like_code " +
                    "FROM year_amt_review yar where false ";


//            "SELECT "
//            + "id AS id, is_delete AS is_delete , modified_at AS modified_at , state AS state , created_at AS created_at , " +
//            "work_exp_open_yn AS work_exp_open_yn , company_id AS company_id, account_id  As account_id,  item_1 AS item_1, " +
//            "  item_2 AS item_2,  item_3 AS item_3,  item_4 AS item_4,  item_5 AS item_5,  item_6 AS item_6,  topq1 AS topq1 " +
//            "FROM interview_review ir " +
//            "UNION ALL " +
//            "SELECT " +
//            "id AS id,  is_delete AS is_delete , modified_at AS modified_at , state AS state , created_at AS created_at , " +
//            "work_exp_open_yn AS work_exp_open_yn , company_id AS company_id  , account_id  As account_id, NULL AS item_1 , NULL AS item_2 , " +
//            "NULL AS item_3 , NULL AS item_4 , NULL AS item_5 , NULL AS item_6, NULL AS topq1 " +
//            "FROM year_amt_review yar " +
//            "UNION ALL " +
//            "SELECT " +
//            "id AS id, is_delete AS is_delete , modified_at AS modified_at , state AS state , created_at AS created_at , " +
//            "work_exp_open_yn AS work_exp_open_yn , company_id AS company_id , account_id  As account_id, NULL AS item_1 , " +
//            "NULL AS item_2 , NULL AS item_3 , NULL AS item_4 , NULL AS item_5 , NULL AS item_6, NULL AS topq1 " +
//            "FROM company_review cr";

    @Query(value = FIND_ALL_IDS, nativeQuery = true)
    List<ReviewAll> findByAllReview(Pageable pageable);

}
