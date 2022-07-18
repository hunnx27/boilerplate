package com.onz.modules.common.pointHistory.domain.embed;

import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.enums.IntrsOrg;
import com.onz.modules.account.domain.enums.IntrsOrgConverter;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import com.onz.modules.common.pointHistory.domain.enums.PointTableConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class PointInfo {
    @Convert(converter = PointTableConverter.class)
    private PointTable pointTable;
    private long totAmt;
    private int amt;
    private String description;


    public PointInfo() {
    }

    public PointInfo(PointTable pointTable, Account account) {
        this.pointTable = pointTable;
        this.totAmt = account.getPoint() + pointTable.getAmt();
        this.amt = pointTable.getAmt();
        this.description = pointTable.getCodeName();
    }
}
