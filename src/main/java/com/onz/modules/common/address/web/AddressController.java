package com.onz.modules.common.address.web;

import com.onz.common.web.BaseApiController;
import com.onz.modules.common.address.domain.AddressCode;
import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.web.dto.AddressResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AddressController extends BaseApiController {
    AddressRepository addressRepository;
    @GetMapping("/common/address")
    public ResponseEntity<List<AddressResponse>> getAllAddressCode(){
        List<AddressCode> result = addressRepository.findAll();

        List<AddressResponse> result2 = result.stream().map(AddressResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(result2);

    }
}
