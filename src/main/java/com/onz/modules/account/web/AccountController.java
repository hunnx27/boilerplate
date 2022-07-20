package com.onz.modules.account.web;

import com.onz.common.enums.YN;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.web.dto.request.AccountCreateRequest;
import com.onz.modules.account.web.dto.request.AccountMyinfoUpdateRequest;
import com.onz.modules.account.web.dto.request.AccountSearchRequest;
import com.onz.modules.account.web.dto.request.AccountUpdateRequest;
import com.onz.common.web.BaseApiController;
import com.onz.modules.account.web.dto.response.AccountResponse;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.pointHistory.web.dto.request.PointHistorySearchRequest;
import com.onz.modules.common.pointHistory.web.dto.response.PointHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController extends BaseApiController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    @PostMapping("/accounts")
    public ResponseEntity<?> create(@RequestBody AccountCreateRequest accountCreateRequest) {
        Account account = accountService.create(
            modelMapper.map(accountCreateRequest, Account.class));
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> list(
        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
        Pageable pageable,
        AccountSearchRequest accountSearchRequest) {
        Page<Account> list = accountService.list(accountSearchRequest, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Account one = accountService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(one);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
        @RequestBody AccountUpdateRequest accountUpdateRequest) {

        accountUpdateRequest.setId(id);
        Long update = accountService.update(accountUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean delete = accountService.delete(id);
        if (delete) {
            SecurityContextHolder.clearContext();
        }
        return ResponseEntity.status(HttpStatus.OK).body(delete);
    }

    @GetMapping("/accounts/{id}/educations")
    public ResponseEntity<?> educations(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.educations(id));
    }

    @GetMapping("/accounts/me")
//    public ResponseEntity<?> me(@CurrentPrincipal UserPrincipal principal) {
    public ResponseEntity<?> me(@AuthenticationPrincipal UserPrincipal up) {
        AccountResponse ressponse = new AccountResponse(accountService.findOne(up.getId()));
        return ResponseEntity.ok(ressponse);
//        return ResponseEntity.ok(new Account());
    }

    @DeleteMapping("/accounts/me")
    public ResponseEntity<?> deleteMe(@AuthenticationPrincipal UserPrincipal up) {
        Account account = accountService.deleteMeSoft(up.getId());
        AccountResponse ressponse = new AccountResponse();
        return ResponseEntity.ok(account);
    }

    @PutMapping("/accounts/me/myinfo")
    public ResponseEntity<?> getMyinfo(@AuthenticationPrincipal UserPrincipal up, @RequestBody AccountMyinfoUpdateRequest request) {
        Account account = accountService.updateMyItem(up.getId(), request);
        return ResponseEntity.ok(account);
    }
    @GetMapping("/accounts/me/pointHistories")
    public ResponseEntity<?> getMyPointHistories(@AuthenticationPrincipal UserPrincipal up) {
        // FIXME Pageable추가 필수
        Page<PointHistoryResponse> list = accountService.getMyPointHistories(up.getId());
        return ResponseEntity.ok(list);
    }
}
