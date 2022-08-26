package com.onz.modules.review.domain;

import com.onz.common.domain.BaseEntity;
import com.onz.common.enums.YN;
import com.onz.common.util.dto.AttachDto;
import com.onz.modules.account.domain.Account;
import com.onz.modules.company.domain.Company;
import com.onz.modules.review.domain.embed.Images;
import com.onz.modules.review.domain.enums.ItemCode;
import com.onz.modules.review.web.dto.CompanyReviewRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DynamicInsert
public class CompanyReview extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;
//
    @Embedded
    private Images images;

    private String txt;
//    private String grade;
    private Long work_exp;

    @Enumerated(EnumType.STRING)
    private YN workExpOpenYn;
    @Column(name = "item_B1")
    @Enumerated(EnumType.STRING)
    private ItemCode itemB1;
    @Column(name = "item_B2")
    @Enumerated(EnumType.STRING)
    private ItemCode itemB2;
    @Column(name = "item_B3")
    @Enumerated(EnumType.STRING)
    private ItemCode itemB3;
    @Column(name = "item_C1")
    @Enumerated(EnumType.STRING)
    private ItemCode itemC1;
    @Column(name = "item_C2")
    @Enumerated(EnumType.STRING)
    private ItemCode itemC2;
    @Column(name = "item_C3")
    @Enumerated(EnumType.STRING)
    private ItemCode itemC3;
    @Column(name = "item_D1")
    @Enumerated(EnumType.STRING)
    private ItemCode itemD1;
    @Column(name = "item_D2")
    @Enumerated(EnumType.STRING)
    private ItemCode itemD2;
    @Enumerated(EnumType.STRING)
    private ItemCode likeCode;

    @ColumnDefault("'W'")
    private String state="W";

    @Builder
    public CompanyReview(CompanyReviewRequestDto companyReviewRequestDto, Company company, Account account) {
        this.account=account;
        this.company=company;
        this.txt=companyReviewRequestDto.getTxt();
        this.itemB1=companyReviewRequestDto.getItemB1();
        this.itemB2=companyReviewRequestDto.getItemB2();
        this.itemB3=companyReviewRequestDto.getItemB3();
        this.itemC1=companyReviewRequestDto.getItemC1();
        this.itemC2=companyReviewRequestDto.getItemC2();
        this.itemC3=companyReviewRequestDto.getItemC3();
        this.itemD1=companyReviewRequestDto.getItemD1();
        this.itemD2=companyReviewRequestDto.getItemD2();
        this.likeCode=companyReviewRequestDto.getLikeCode();
        this.work_exp= companyReviewRequestDto.getWorkExp();
        this.workExpOpenYn=companyReviewRequestDto.getWorkExpOpenYn();
//        this.state=getState();
    }


    public CompanyReview(CompanyReviewRequestDto companyReviewRequestDto,Long id) {
        this.id = id;
        this.work_exp=companyReviewRequestDto.getWorkExp();
        this.workExpOpenYn=companyReviewRequestDto.getWorkExpOpenYn();
        this.company=getCompany();
        this.likeCode=companyReviewRequestDto.getLikeCode();
        this.account=getAccount();
        this.txt = companyReviewRequestDto.getTxt();
    }

    public void setImages(List<AttachDto> filelist){
        com.onz.modules.review.domain.embed.Images embedImages = new com.onz.modules.review.domain.embed.Images();
        if(filelist!=null && filelist.size()>0){
            for(int i=0; i<filelist.size(); i++){
                AttachDto atttach = filelist.get(i);
                switch(i){
                    case 0: embedImages.setImage1(atttach.getSaveName());break;
                    case 1: embedImages.setImage2(atttach.getSaveName());break;
                    case 2: embedImages.setImage3(atttach.getSaveName());break;
                    case 3: embedImages.setImage4(atttach.getSaveName());break;
                    case 4: embedImages.setImage5(atttach.getSaveName());break;
                }
            }
            this.images = embedImages;
        }
    }
}
