package com.onz.modules.review.infra;

import com.onz.modules.company.domain.Company;
import com.onz.modules.review.domain.dto.ReviewAllDto;
import com.onz.modules.review.web.dto.FindEstaRequestDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

import static com.onz.modules.review.infra.ReviewRepository.FIND_ALL_IDS;


//@Repository
//public class AccountRepositoryExtensionImpl extends QuerydslRepositorySupport implements
//        AccountRepositoryExtension {
//
//    private final JPAQueryFactory qf;
//    private final EntityManager em;
//
//    public AccountRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
//        super(Account.class);
//        this.qf = qf;
//        this.em = em;
//    }


public class ReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        ReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    public ReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory, EntityManager em) {
        super(Company.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.em = em;
    }

    public List<ReviewAllDto> findByAllReview(FindEstaRequestDto findEstaRequestDto, Pageable pageable) {
        Query nativequery =em
                .createNativeQuery(FIND_ALL_IDS,"reviewUnion");
        List<ReviewAllDto> resultList=nativequery.getResultList();

        return resultList;

//        Map<String, String> paramaterMap = new HashMap<String, String>();
//        StringBuilder queryBuilder = new StringBuilder();
//        queryBuilder.append(FIND_ALL_IDS);
////
//        Query jpaQuery = em.createNativeQuery(queryBuilder.toString());
////
//        for (String key : paramaterMap.keySet()) {
//            jpaQuery.setParameter(key, paramaterMap.get(key));
//        }

//        List<ReviewAllDto> result = new  ArrayList<ReviewAllDto>();
//        for(int i = 0; i < jpaQuery.getResultList().size(); i++){ // resultList
//            if(jpaQuery.getResultList().get(i) instanceof Object[]) {
//                Object[] objects = (Object[]) jpaQuery.getResultList().get(i);
//                List<ReviewAllDto> list = new ArrayList<>(); // 49개 짜리 담을 리스트
//                for (int j = 0; j < objects.length; j++) {
//                    ReviewAllDto d = (ReviewAllDto) objects[j]; // 0~49 해당 dto로 캐스팅
//                    list.add(d); // 리스트에 추가
//                }
//                result.add((ReviewAllDto) list); // 49개 짜리 리스트 resultList에 추가
//            }
//        }
//        List<ReviewAllDto> review = new ArrayList<>();
//        List b = jpaQuery.getResultList();
//        review.add((ReviewAllDto) b);
//        List<ReviewAll> list2 = list.stream().map(o -> {
//            return new ReviewAllDto(
//            );
////        }).collect(Collectors.toList());
//    List test = new ArrayList();
//    test.add(jpaQuery.getResultList());
//    List<ReviewAllDto> result= new ArrayList();
//    for(Object object : test){
//        result.add((ReviewAllDto) object);
//    }
//        return  resultList;
//    }

        //em.createQuery(query, RESPONSE_DTO.class)
//        for(Object o : r)
//        {
    }
}
