package nl.prbed.hu.aviation.management.presentation.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.Customer;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CustomerOverviewResponseDto {
    private final List<Customer> customers;
}
