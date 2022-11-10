package com.onz.modules.counsel.application;

import com.onz.common.exception.CustomException;
import com.onz.common.web.dto.response.enums.ErrorCode;
import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.common.util.FileUtil;
import com.onz.common.util.dto.AttachDto;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.pointHistory.domain.PointHistory;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import com.onz.modules.company.web.dto.reponse.CounselSearchCountDto;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.CounselRecommend;
import com.onz.modules.counsel.domain.enums.CounselState;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.counsel.domain.enums.QnaItem;
import com.onz.modules.counsel.domain.enums.RecommendGubn;
import com.onz.modules.counsel.infra.counsel.CounselRepository;
import com.onz.modules.counsel.infra.counselComment.CounselCommentRepository;
import com.onz.modules.counsel.infra.counselRecommend.CounselRecommendRepository;
import com.onz.modules.counsel.web.dto.request.counsel.*;
import com.onz.modules.counsel.web.dto.response.CounselAnswerListResponse;
import com.onz.modules.counsel.web.dto.response.counsel.CounselAnswerDetailResponse;
import com.onz.modules.counsel.web.dto.response.counsel.CounselDetailResponse;
import com.onz.modules.counsel.web.dto.response.counsel.CounselListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.onz.modules.common.pointHistory.domain.enums.PointTable.COUNCEL_SELECT;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CounselService {

    private final AccountService accountService;
    private final CounselRepository counselRepository;
    private final CounselCommentRepository counselCommentRepository;
    private final CounselRecommendRepository counselRecommendRepository;
    private final FileUtil fileUtil;

    Long q1;
    Long q2;
    Long q3;
    Long q4;
    Long q5;
    Long q6;
    Long q7;
//    public Page<Organization> list(OrganizationSearchRequest organizationSearchRequest) {
//        return CounselService.this.counselRepository.list(organizationSearchRequest);
//    }

    public void create(Counsel counsel) {
        CounselService.this.counselRepository.save(counsel);
    }

    public void create(CounselQCreateRequest counselQCreateRequest, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        Counsel counsel = new Counsel(counselQCreateRequest, account);
        accountService.createMyPointHistories(account, PointTable.COUNCEL_QUESTION_REGIST);
        Counsel saved = counselRepository.save(counsel);

        // Image File Upload
        if (counselQCreateRequest.getFiles() != null && counselQCreateRequest.getFiles().size() > 0) {
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(counselQCreateRequest.getFiles(), saved.getId());
                saved.setImages(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<CounselListResponse> list(CounselSearchRequest counselSearchRequest, Pageable pageable, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        List<Counsel> list = counselRepository.findCounselList(counselSearchRequest, pageable);
        List<CounselListResponse> result = list.stream().map(counsel -> new CounselListResponse(counsel, account)).collect(Collectors.toList());
        return result;
    }

    public CounselDetailResponse detail(Long id, UserPrincipal me) {
        Counsel counsel = counselRepository.findById(id).orElse(null);
        CounselDetailResponse result = null;
        if (counsel != null) {
            Account account = accountService.findOne(me.getId());
            result = new CounselDetailResponse(counsel, account);
        }
        return result;
    }

    public CounselDetailResponse updateCounsel(Long id, CounselQUpdateRequest counselQUpdateRequest, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        Counsel counsel = counselRepository.findById(id).get();
        counsel.updateCounsel(counselQUpdateRequest, account);
        Counsel saved = counselRepository.save(counsel);

        //TODO 검증필요
        //TODO 검증필요
        //TODO 검증필요
        // Image File Upload
        if (counselQUpdateRequest.getFiles() != null && counselQUpdateRequest.getFiles().size() > 0) {
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(counselQUpdateRequest.getFiles(), saved.getId());
                saved.setImages(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new CounselDetailResponse(saved, account);
    }

    public Counsel deleteCounselSoft(Long id) {
        Counsel counsel = counselRepository.findById(id).orElseThrow();
        Account account = accountService.findOne(counsel.getAccount().getId());
        if(counsel.getQnaGubn().equals(QnaGubn.Q)) {
            counsel.setIsDelete(YN.Y);
            counselRepository.save(counsel);
            accountService.createMyPointHistories(account, PointTable.COUNCEL_DELETE);
        }
        return counsel;
    }

    public List<CounselAnswerListResponse> answerList(Long counselId, Pageable pageable, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        //List<Counsel> list = counselRepository.findAll(pageable).get().collect(Collectors.toList());
        List<Counsel> list = counselRepository.findAnswerList(counselId, pageable);
        List<CounselAnswerListResponse> result =
                list.stream().map(counsel -> {
                            Long answerId = counsel.getId();
                            Long writer = counsel.getAccount().getId();
                            CounselAnswerListResponse res = new CounselAnswerListResponse(counsel, account);
                            long commentCnt = counselCommentRepository.countCommentList(answerId);
                            res.setCommentCnt(commentCnt);
                            List<CounselRecommend> recommendList = counselRecommendRepository.findRecommend(answerId);
                            res.setRecommendCnt(recommendList.size());
                            boolean isRecommand = recommendList.stream().anyMatch(recommend -> recommend.getAccount().getId() == me.getId());
                            res.setRecommend(isRecommand);
                            long adoptedCnt = counselRepository.countAdoptedAnswer(answerId, writer);
                            res.setStateAdoptedCnt(adoptedCnt);
                            long noticeCnt = counselRecommendRepository.countNotice(answerId, me.getId());
                            res.setNotice(noticeCnt > 0);
                            return res;
                        })
                        .collect(Collectors.toList());
        return result;
    }

    public CounselAnswerDetailResponse answerById(Long id) {
//        Account account = accountService.findOne(me.getId());
        //List<Counsel> list = counselRepository.findAll(pageable).get().collect(Collectors.toList());
        Counsel one = counselRepository.findById(id).orElseGet(null);
        CounselAnswerDetailResponse result = new CounselAnswerDetailResponse(one);
        return result;
    }

    public void createAnswer(CounselACreateRequest counselACreateRequest, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        Long parentCounselId = counselACreateRequest.getParentCounselId();
        if (parentCounselId != -1) {
            Counsel parentCounsel = counselRepository.findById(parentCounselId).orElseGet(null);
            long cnt = counselRepository.countByParentCounselId(parentCounselId);
            parentCounsel.setReportCnt(++cnt);
            counselACreateRequest.setParentCounsel(parentCounsel);
        }
        Counsel counsel = new Counsel(counselACreateRequest, account);
        Counsel saved = counselRepository.save(counsel);

        // Image File Upload
        if (counselACreateRequest.getFiles() != null && counselACreateRequest.getFiles().size() > 0) {
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(counselACreateRequest.getFiles(), saved.getId());
                saved.setImages(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Counsel updateAnswer(Long id, CounselAUpdateRequest counselAUpdateRequest, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        Counsel counsel = counselRepository.findById(id).get();
        counsel.updateAnswerCounsel(counselAUpdateRequest, account);
        Counsel saved = counselRepository.save(counsel);

        //TODO 검증필요
        //TODO 검증필요
        //TODO 검증필요
        // Image File Upload
        if (counselAUpdateRequest.getFiles() != null && counselAUpdateRequest.getFiles().size() > 0) {
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(counselAUpdateRequest.getFiles(), saved.getId());
                saved.setImages(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return saved;
    }

    public Counsel deleteAnswerSoft(Long id) {
        Counsel counsel = counselRepository.findById(id).orElseThrow();
        if(counsel.getQnaGubn().equals(QnaGubn.A)) {
            counsel.setIsDelete(YN.Y);
            counselRepository.save(counsel);
        }
        return counsel;
    }

    public Counsel updateAnswerAdopt(Long id, CounselAAdoptRequest counselAAdoptRequest, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        Counsel counsel = counselRepository.findById(id).get();
        Counsel parent = counselRepository.findById(counselAAdoptRequest.getParentCounselId()).orElseGet(null);
        Account answer = accountService.findOne(counsel.getAccount().getId());
        accountService.createMyPointHistories(account, PointTable.COUNCEL_SELECT);
        accountService.createMyPointHistories(answer, PointTable.COUNCEL_CHOSEN);
        parent.updateCounselAdopted();
        counselAAdoptRequest.setParentCounsel(parent);
        counsel.updateAnswerAdopt(counselAAdoptRequest);
        Counsel saved = counselRepository.save(counsel);
        return saved;
    }

    // 추천하기
    public void recommendAnswer(Long id, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        Counsel answerCounsel = counselRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        if(answerCounsel!=null){
            List<CounselRecommend>counselRecommends = counselRecommendRepository.findRecommendCheck(id,me.getId());
            if(counselRecommends.isEmpty()){
                CounselRecommend counselRecommend = new CounselRecommend(account,answerCounsel,RecommendGubn.R);
                counselRecommendRepository.save(counselRecommend);
            }else {
                throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
            }
        }

    }
    // 신고하기
    public void noticeAnswer(Long id, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        Counsel answerCounsel = counselRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND));
        if(answerCounsel!=null){
            List<CounselRecommend>counselRecommends = counselRecommendRepository.findRecommendCheckN(id,me.getId());
            if(counselRecommends.isEmpty()){
                CounselRecommend counselRecommad = new CounselRecommend(account, answerCounsel, RecommendGubn.N);
                counselRecommendRepository.save(counselRecommad);
            }else {
                throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
            }
        }
    }

    /**
     * 검색하기
     */

    public List<CounselListResponse> search(String tag) {
        List<Counsel> counsel = counselRepository.findAll();
        List<CounselListResponse> result = counsel.stream().map(res -> {
            if (null == res.getInputTag()) {

            } else {
                String temp = res.getInputTag().replaceAll(" ","");
                String date[] = temp.split("#");
                System.out.println(date);
                for (int i = 0; i < date.length; i++) {
                    if (date[i].equals(tag)) {
                        return new CounselListResponse(res);
                    }
                }
            }
            return null;
        }).collect(Collectors.toList());
        return result;
    }

    public CounselSearchCountDto tagmoa(String gubn) {
        List gubnCodeList = Arrays.stream(Gubn.values()).map(gubn1 -> gubn1.name()).collect(Collectors.toList());
        String gubnCode = Gubn.TEACHER.getCode();
        if(gubnCodeList.contains(gubn)){
            gubnCode = Gubn.valueOf(gubn).getCode();
        }
        List<CounselSearchCountDto.QnaItemInfo> qnaList = new ArrayList<>();
        q1 = 0L;
        q2 = 0L;
        q3 = 0L;
        q4 = 0L;
        q5 = 0L;
        q6 = 0L;
        q7 = 0L;
        List<Counsel> counsel = counselRepository.findAll();
        final String finalGubnCode = gubnCode;
        List<Counsel> result = counsel.stream().map(res -> {
                    if (res.getGubn().getCode().equals(finalGubnCode)) {
                        if (res.getQnaItem() == null) {
                        } else {
                            if (res.getGubn().getCode().equals("I")) {
                                {
                                    switch (res.getQnaItem().getCode() != null ? res.getQnaItem().getCode() : "06") {
                                        case "01":
                                            q1 += 1;
                                            break;
                                        case "02":
                                            q2 += 1;
                                            break;
                                        case "03":
                                            q3 += 1;
                                            break;
                                        case "04":
                                            q4 += 1;
                                            break;
                                        case "05":
                                            q5 += 1;
                                            break;
                                        case "06":
                                            q6 += 1;
                                            break;
                                    }
                                }
                            } else if (res.getGubn().getCode().equals("S")) {
                                switch (res.getQnaItem().getCode() != null ? res.getQnaItem().getCode() : "07") {
                                    case "01":
                                        q1 += 1;
                                        break;
                                    case "02":
                                        q2 += 1;
                                        break;
                                    case "03":
                                        q3 += 1;
                                        break;
                                    case "04":
                                        q4 += 1;
                                        break;
                                    case "05":
                                        q5 += 1;
                                        break;
                                    case "06":
                                        q6 += 1;
                                        break;
                                    case "07":
                                        q7 += 1;
                                        break;
                                }
                            }
                        }
                    }
                    return res;
                }).

                collect(Collectors.toList());
        CounselSearchCountDto counselSearchCountDto = new CounselSearchCountDto();
        if (finalGubnCode.equals("I")) {
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QI01).cnt(q1).build());
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QI02).cnt(q2).build());
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QI03).cnt(q3).build());
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QI04).cnt(q4).build());
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QI05).cnt(q5).build());
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QI06).cnt(q6).build());
            counselSearchCountDto.setQnaList(qnaList);
        } else if (finalGubnCode.equals("S")) {
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QS01).cnt(q1).build());
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QS02).cnt(q2).build());
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QS03).cnt(q3).build());
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QS04).cnt(q4).build());
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QS05).cnt(q5).build());
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QS06).cnt(q6).build());
            qnaList.add(CounselSearchCountDto.QnaItemInfo.builder().item(QnaItem.QS07).cnt(q7).build());
            counselSearchCountDto.setQnaList(qnaList);
        }

        return counselSearchCountDto;
    }

    /**
     * //FIXME  ---> Code로 검색하자!
     */
    public List<CounselListResponse> tagMoaGo(String qnaItem) {
        List<Counsel> counsel = counselRepository.findAll();
        List<CounselListResponse> result = counsel.stream().map(res -> {
            if (null == res.getInputTag()) {
                // Empty
            } else {
                if (res.getQnaItem().name().equals(qnaItem)) {
                    return new CounselListResponse(res);
                }else{

                }
            }
            return null;
        }).collect(Collectors.toList());
        return result;
    }

//    public void update(OrganizationUpdateRequest updateRequest) {
//        CounselService.this.counselRepository.update(updateRequest);
//    }
//
//    public Organization findOne(Long id) {
//        return CounselService.this.counselRepository.findById(id)
//            .orElseThrow();
//    }
}
