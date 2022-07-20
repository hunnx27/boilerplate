package com.onz.modules.common.address.web;

import com.onz.common.web.BaseApiController;
import com.onz.modules.common.address.domain.Address;
import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.web.dto.AddressResponse;
import com.onz.modules.common.address.web.dto.AddressSidoResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AddressController extends BaseApiController {
    AddressRepository addressRepository;
    @GetMapping("/common/address")
    public ResponseEntity<List<AddressResponse>> getAllAddress(){
        List<Address> result = addressRepository.findAll();

        List<AddressResponse> result2 = result.stream().map(AddressResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(result2);

    }

    @GetMapping("/common/address/sido")
    public ResponseEntity<List<AddressSidoResponse>> getAddressGroupBySidoCode(){
        List<Address> result = addressRepository.findByAddressGroupBySidoCide();

        List<AddressSidoResponse> result2 = result.stream().map(AddressSidoResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(result2);

    }

    @GetMapping("/common/address/sido/{sidoCode}")
    public ResponseEntity<List<AddressResponse>> getAddressBySidoCode(@PathVariable int sidoCode, Pageable pageable){
        Page<Address> result = addressRepository.findBySidoCode(sidoCode, pageable);

        List<AddressResponse> result2 = result.stream().map(AddressResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(result2);

    }

}
