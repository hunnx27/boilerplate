package com.onz.modules.admin.LiveMember.domain;

import com.onz.common.enums.Gubn;
import com.onz.common.enums.GubnConverter;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.enums.AuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@NoArgsConstructor
public class LiveMemberResponseDto {

    @Getter
    private Long id;
    private Gubn gubn;
    private String gubnName;
    @Getter
    private String userId;

    private AuthProvider snsType;
    private String snsTypeName;

    @Getter
    private String rank;
    @Getter
    private Long point;

    private ZonedDateTime createdAt;

    @Getter
    private long companyReviewCnt;
    @Getter
    private long interviewReviewCnt;
    @Getter
    private long amtReviewCnt;
    @Getter
    private long counselQCnt;
    @Getter
    private long counselACnt;

    public LiveMemberResponseDto(Long id){
        this.id=id;
    }

    public String getGubnName() {
        return this.gubn.getName();
    }

    public String getSnsTypeName() {
        return this.snsType.getName();
    }

    public String getCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.createdAt.format(formatter);

    }
}
