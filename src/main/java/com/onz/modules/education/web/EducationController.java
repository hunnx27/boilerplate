package com.onz.modules.education.web;

import com.onz.common.web.BaseApiController;
import com.onz.modules.education.application.EducationService;
import com.onz.modules.education.application.request.EducationCreateRequest;
import com.onz.modules.education.application.request.EducationSearchRequest;
import com.onz.modules.education.application.request.EducationUpdateRequest;
import com.onz.modules.education.domain.Education;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EducationController extends BaseApiController {

    private final EducationService eduCationService;
    private final ModelMapper modelMapper;

    @PostMapping("/educations")
    public ResponseEntity<?> create(@RequestBody EducationCreateRequest educationCreateRequest) {
        return ResponseEntity.ok(
            eduCationService.create(modelMapper.map(educationCreateRequest, Education.class)));
    }

    @GetMapping("/educations")
    public ResponseEntity<?> list(
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
        Pageable pageable,
        EducationSearchRequest educationSearchRequest) {
        return ResponseEntity.ok(eduCationService.list(educationSearchRequest, pageable));
    }

    @GetMapping("/educations/{id}")
    public ResponseEntity<?> list(@PathVariable Long id) {
        return ResponseEntity.ok(eduCationService.detail(id));
    }

    @PutMapping("/educations/{id}")
    public ResponseEntity<?> update(@RequestBody EducationUpdateRequest educationUpdateRequest,
        @PathVariable Long id) {
        educationUpdateRequest.setId(id);
        return ResponseEntity.ok(eduCationService.update(educationUpdateRequest));
    }

    @DeleteMapping("/educations/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(eduCationService.delete(id));
    }

    @PostMapping("/educations/{id}/join")
    public ResponseEntity<?> join(@PathVariable Long id, Long accountId) {
        return ResponseEntity.ok(eduCationService.join(id, accountId));
    }

    @PostMapping("/educations/{id}/leave")
    public ResponseEntity<?> leave(@PathVariable Long id, Long accountId) {
        return ResponseEntity.ok(eduCationService.leave(id, accountId));
    }

}
