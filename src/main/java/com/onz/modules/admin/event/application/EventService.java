package com.onz.modules.admin.event.application;

import com.onz.common.util.FileUtil;
import com.onz.common.util.dto.AttachDto;
import com.onz.common.web.ApiR;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.admin.event.domain.Event;
import com.onz.modules.admin.event.domain.enums.Landding;
import com.onz.modules.admin.event.domain.enums.PopupLocation;
import com.onz.modules.admin.event.infra.EventRepository;
import com.onz.modules.admin.event.web.dto.EventCreateRequestDto;
import com.onz.modules.admin.event.web.dto.EventSearchRequestDto;
import com.onz.modules.admin.event.web.dto.EventSearchResponseDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.LocalDate.now;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;
    private final FileUtil fileUtil;

    public ResponseEntity<ApiR<?>> create(EventCreateRequestDto eventCreateRequestdto, UserPrincipal me) {
        Event event = new Event(eventCreateRequestdto);
        // Image File Upload
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (eventCreateRequestdto.getFiles() != null && eventCreateRequestdto.getFiles().size() > 0) {
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(eventCreateRequestdto.getFiles(), event.getId());
                event.setImgUrl(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(eventCreateRequestdto.getStartDt()!=null){
            LocalDate temp = LocalDate.parse(eventCreateRequestdto.getStartDt(),formatter);
            event.setStartDt(temp.atStartOfDay(ZoneId.of("Asia/Seoul")));
        }
        if(eventCreateRequestdto.getEndDt()!=null){
            LocalDate temp = LocalDate.parse(eventCreateRequestdto.getEndDt(),formatter);
            event.setEndDt(temp.atStartOfDay(ZoneId.of("Asia/Seoul")));
        }
        if (eventCreateRequestdto.getUseYn().equals("Y")) {
            event.setUseYn(YN.Y);
        } else {
            event.setUseYn(YN.N);
        }
        if (eventCreateRequestdto.getSubmit_popup().equals("Y")) {
            event.setSubmitPopup(YN.Y);
            switch (eventCreateRequestdto.getSubmit_popup_location()){
                case "L1":
                    event.setSubmitPopupLocation(PopupLocation.L1);
                    break;
                case "L2":
                    event.setSubmitPopupLocation(PopupLocation.L2);
                    break;
                case "L3":
                    event.setSubmitPopupLocation(PopupLocation.L3);
                    break;
                case "L4":
                    event.setSubmitPopupLocation(PopupLocation.L4);
                    break;
                case "L5":
                    event.setSubmitPopupLocation(PopupLocation.L5);
                    break;
                default:
                    break;
            }
        } else {
            event.setSubmitPopup(YN.N);
        }
        if(!eventCreateRequestdto.getButtonLanding().equals(Landding.L0.getName())){
            switch (eventCreateRequestdto.getButtonLanding()){
                case "L1":
                    event.setButtonLanding(Landding.L1);
                    break;
                case "L2":
                    event.setButtonLanding(Landding.L2);
                    break;
                case "L3":
                    event.setButtonLanding(Landding.L3);
                    break;
                case "L4":
                    event.setButtonLanding(Landding.L4);
                    break;
                case "L5":
                    event.setButtonLanding(Landding.L5);
                    event.setNoticeId(eventCreateRequestdto.getNoticeId());
                    break;
                case "L6":
                    event.setButtonLanding(Landding.L6);
                    event.setLandingUrl(eventCreateRequestdto.getLandingUrl());
                    break;
            }
        }
        LocalDate temp = LocalDate.parse(LocalDate.now().format(formatter));
        event.setCreateDt(temp.atStartOfDay(ZoneId.of("Asia/Seoul")));
        eventRepository.save(event);
        return null;
    }

    public List<EventSearchResponseDto> eventSearch(EventSearchRequestDto eventSearchRequestDto, Pageable pageable) {
        List<EventSearchResponseDto> list = eventRepository.findEventSearch(eventSearchRequestDto, pageable);
        return list;
    }
}
