package com.onz.modules.counsel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.domain.BaseEntity;
import com.onz.common.enums.*;
import com.onz.common.util.dto.AttachDto;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.embed.Images;
import com.onz.modules.counsel.domain.enums.*;
import com.onz.modules.counsel.web.dto.request.CounselQCreateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class CounselRecommand extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name="account_id")
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name="counsel_id")
    private Counsel counsel;
    @Enumerated(EnumType.STRING)
    private RecommandGubn recommandGubn;

    public CounselRecommand() {
    }

}
