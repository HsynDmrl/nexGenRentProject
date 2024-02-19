package com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.base.GetBaseResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerResponse extends GetBaseResponse {
    private GetUserResponse user;
}
