package com.onz.modules.counsel.domain;

import com.onz.common.domain.BaseEntity;
import com.onz.common.enums.Gubn;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.web.dto.request.counselComment.CounselCommentCreateRequest;
import com.onz.modules.counsel.web.dto.request.counselComment.CounselCommentUpdateRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CounselComment extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name="counsel_id")
    private Counsel counsel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name="account_id")
    private Account account;
    @Enumerated(EnumType.STRING)
    private Gubn gubn;
    private String txt;


    public CounselComment() {
    }

    public CounselComment(CounselCommentCreateRequest req, Account account) {
    }

    public void updateCounselComment(CounselCommentUpdateRequest req, Account account){
    }
}
