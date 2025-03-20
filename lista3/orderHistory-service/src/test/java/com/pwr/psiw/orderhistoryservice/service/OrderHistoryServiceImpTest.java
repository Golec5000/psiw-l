package com.pwr.psiw.orderhistoryservice.service;

import com.pwr.psiw.orderhistoryservice.exeption.custome.OrderHistoryNotFoundException;
import com.pwr.psiw.orderhistoryservice.model.OrderHistory;
import com.pwr.psiw.orderhistoryservice.repository.OrderHistoryRepository;
import com.pwr.psiw.orderhistoryservice.utils.PageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderHistoryServiceImpTest {

    @Mock
    private OrderHistoryRepository orderHistoryRepository;

    @InjectMocks
    private OrderHistoryServiceImp orderHistoryService;

    private OrderHistory sampleOrderHistory;

    @BeforeEach
    void setUp() {
        sampleOrderHistory = OrderHistory.builder()
                .id(1L)
                .customerName("John Doe")
                .deliveryStatus("DELIVERED")
                .productName("Laptop")
                .totalPrice(new BigDecimal("1200.50"))
                .build();
    }

    @Test
    void findAll_ShouldReturnPagedResultsWithHateoasLinks() {
        // Given
        Pageable pageable = PageRequest.of(0, 2);
        List<OrderHistory> orderHistories = List.of(sampleOrderHistory);
        Page<OrderHistory> page = new PageImpl<>(orderHistories, pageable, orderHistories.size());

        when(orderHistoryRepository.findAll(pageable)).thenReturn(page);

        // When
        PageResponse<EntityModel<OrderHistory>> response = orderHistoryService.findAll(0, 2);

        // Then
        assertEquals(0, response.currentPage());
        assertEquals(2, response.pageSize());
        assertEquals(1, response.totalPages());
        assertFalse(response.hasNext());
        assertTrue(response.last());
        assertEquals(1, response.content().size());

        // Sprawdzenie, czy linki HATEOAS istnieją
        EntityModel<OrderHistory> orderModel = response.content().getFirst();
        assertNotNull(orderModel.getLinks());
        assertFalse(orderModel.getLinks().isEmpty());

        verify(orderHistoryRepository, times(1)).findAll(pageable);
    }

    @Test
    void findById_ShouldReturnOrderHistory_WhenExists() {
        // Given
        when(orderHistoryRepository.findById(1L)).thenReturn(Optional.of(sampleOrderHistory));

        // When
        OrderHistory result = orderHistoryService.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals(sampleOrderHistory.getId(), result.getId());
        assertEquals(sampleOrderHistory.getCustomerName(), result.getCustomerName());

        verify(orderHistoryRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        // Given
        when(orderHistoryRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(OrderHistoryNotFoundException.class, () -> orderHistoryService.findById(1L));

        verify(orderHistoryRepository, times(1)).findById(1L);
    }

    @Test
    void save_ShouldSaveOrderHistory() {
        // Given
        when(orderHistoryRepository.save(sampleOrderHistory)).thenReturn(sampleOrderHistory);

        // When
        OrderHistory saved = orderHistoryService.save(sampleOrderHistory);

        // Then
        assertNotNull(saved);
        assertEquals(sampleOrderHistory.getId(), saved.getId());

        verify(orderHistoryRepository, times(1)).save(sampleOrderHistory);
    }

    @Test
    void update_ShouldUpdateOrderHistoryStatus() {
        // Given
        when(orderHistoryRepository.findById(1L)).thenReturn(Optional.of(sampleOrderHistory));
        when(orderHistoryRepository.save(any(OrderHistory.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        OrderHistory updated = orderHistoryService.update(1L, "SHIPPED");

        // Then
        assertEquals("SHIPPED", updated.getDeliveryStatus());

        verify(orderHistoryRepository, times(1)).findById(1L);
        verify(orderHistoryRepository, times(1)).save(sampleOrderHistory);
    }

    @Test
    void update_ShouldThrowException_WhenOrderNotFound() {
        // Given
        when(orderHistoryRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(OrderHistoryNotFoundException.class, () -> orderHistoryService.update(1L, "SHIPPED"));

        verify(orderHistoryRepository, times(1)).findById(1L);
        verify(orderHistoryRepository, never()).save(any(OrderHistory.class));
    }
}
