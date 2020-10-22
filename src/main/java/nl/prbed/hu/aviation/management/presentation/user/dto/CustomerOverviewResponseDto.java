package nl.prbed.hu.aviation.management.presentation.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.Customer;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CustomerOverviewResponseDto {
    @ApiModelProperty(notes = "The list of customers")
    private final List<Customer> customers;
}
