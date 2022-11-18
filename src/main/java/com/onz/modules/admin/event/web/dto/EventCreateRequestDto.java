package com.onz.modules.admin.event.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class EventCreateRequestDto {
    private String startDt;
    private String endDt;
    private String deviceGubn;
    private String useYn;
    private String title;
    private String content;
    private String buttonLanding;
    private String popupShowOption;
    private String submit_popup;
    private String submit_popup_location;
    private Long noticeId;
    private String landingUrl;
}
