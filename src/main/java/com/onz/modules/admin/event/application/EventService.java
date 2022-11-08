package com.onz.modules.admin.event.application;

import com.onz.common.util.FileUtil;
import com.onz.common.util.dto.AttachDto;
import com.onz.common.web.ApiR;
import com.onz.common.web.dto.response.enums.Role;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.admin.event.domain.Event;
import com.onz.modules.admin.event.domain.enums.Landding;
import com.onz.modules.admin.event.domain.enums.PopupLocation;
import com.onz.modules.admin.event.infra.EventRepository;
import com.onz.modules.admin.event.web.dto.EventCreateRequestDto;
import com.onz.modules.admin.event.web.dto.EventSearchDetailResponseDto;
import com.onz.modules.admin.event.web.dto.EventSearchRequestDto;
import com.onz.modules.admin.event.web.dto.EventSearchResponseDto;
import com.onz.modules.admin.notice.domain.Notice;
import com.onz.modules.admin.notice.domain.enums.DeviceGubn;
import com.onz.modules.admin.notice.web.dto.NoticeRequestDto;
import com.onz.modules.admin.notice.web.dto.NoticeSearchDetailResponseDto;
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
import java.util.Optional;

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
        if (eventCreateRequestdto.getStartDt() != null) {
            LocalDate temp = LocalDate.parse(eventCreateRequestdto.getStartDt(), formatter);
            event.setStartDt(temp.atStartOfDay(ZoneId.of("Asia/Seoul")));
        }
        if (eventCreateRequestdto.getEndDt() != null) {
            LocalDate temp = LocalDate.parse(eventCreateRequestdto.getEndDt(), formatter);
            event.setEndDt(temp.atStartOfDay(ZoneId.of("Asia/Seoul")));
        }
        if (eventCreateRequestdto.getUseYn().equals("Y")) {
            event.setUseYn(YN.Y);
        } else {
            event.setUseYn(YN.N);
        }
        if (eventCreateRequestdto.getSubmit_popup().equals("Y")) {
            event.setSubmitPopup(YN.Y);
            switch (eventCreateRequestdto.getSubmit_popup_location()) {
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
        if (!eventCreateRequestdto.getButtonLanding().equals(Landding.L0.getName())) {
            switch (eventCreateRequestdto.getButtonLanding()) {
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

    public EventSearchDetailResponseDto eventSearchDetail(Long id, UserPrincipal me) {
        Optional<Event> result = eventRepository.findById(id);
        EventSearchDetailResponseDto eventSearchDetailResponseDto = new EventSearchDetailResponseDto(result);
        return eventSearchDetailResponseDto;
    }

    public EventSearchDetailResponseDto eventSearchDetailfix(Long id, EventCreateRequestDto eventCreateRequestDto, UserPrincipal me) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Optional<Event> event = eventRepository.findById(id);
        Event event1 = event.orElseGet(null);
        if(eventCreateRequestDto.getStartDt()!=null){
            if(eventCreateRequestDto.getEndDt()!=null){
                LocalDate end = LocalDate.parse(eventCreateRequestDto.getEndDt(), formatter);
                LocalDate start = LocalDate.parse(eventCreateRequestDto.getStartDt(), formatter);
                event1.setEndDt(end.atStartOfDay(ZoneId.of("Asia/Seoul")));
                event1.setStartDt(start.atStartOfDay(ZoneId.of("Asia/Seoul")));
            }
        }
        if(eventCreateRequestDto.getTitle()!=null) {
            event1.setTitle(eventCreateRequestDto.getTitle());
        }
        if(eventCreateRequestDto.getDeviceGubn()!=null) {
            event1.setDeviceGubn(DeviceGubn.valueOf(eventCreateRequestDto.getDeviceGubn()));
        }
        if(eventCreateRequestDto.getUseYn()!=null) {
            event1.setUseYn(YN.valueOf(eventCreateRequestDto.getUseYn()));
        }
        if(eventCreateRequestDto.getContent()!=null) {
            event1.setContent(eventCreateRequestDto.getContent());
        }
            if (eventCreateRequestDto.getFiles() != null && eventCreateRequestDto.getFiles().size() > 0) {
                try {
                    List<AttachDto> rs = fileUtil.uploadFiles(eventCreateRequestDto.getFiles(), event1.getId());
                    event1.setImgUrl(rs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        if(eventCreateRequestDto.getButtonLanding()!=null){
            event1.setButtonLanding(Landding.valueOf(eventCreateRequestDto.getButtonLanding()));
            switch (event1.getButtonLanding().getValue()){
                case "5":
                    if(eventCreateRequestDto.getNoticeId()!=null){
                        event1.setNoticeId(eventCreateRequestDto.getNoticeId());
                    }
                    break;
                case "6":
                    if(eventCreateRequestDto.getLandingUrl()!=null){
                        event1.setLandingUrl(eventCreateRequestDto.getLandingUrl());
                    }
                    break;
                default:
                    break;
            }
        }
        if(eventCreateRequestDto.getPopupShowOption()!=null){
            event1.setPopupShowOption(eventCreateRequestDto.getPopupShowOption());
        }
        if(eventCreateRequestDto.getSubmit_popup()!=null){
            event1.setSubmitPopup(YN.valueOf(eventCreateRequestDto.getSubmit_popup()));
            if(event1.getSubmitPopup().equals(YN.Y)){
                event1.setSubmitPopupLocation(PopupLocation.valueOf(eventCreateRequestDto.getSubmit_popup_location()));
            }
        }
        eventRepository.save(event1);
        EventSearchDetailResponseDto eventSearchDetailResponseDto = new EventSearchDetailResponseDto(event);
        return eventSearchDetailResponseDto;
    }
    public ResponseEntity eventSearchDelete(Long id, UserPrincipal me) {
        Event event = eventRepository.getById(id);
        eventRepository.delete(event);
        return null;
    }
}
