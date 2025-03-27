package com.pwr.psiw.orderhistoryservice.service;

import com.pwr.psiw.orderhistoryservice.configuration.OrderHistoryModelAssembler;
import com.pwr.psiw.orderhistoryservice.exeption.custome.OrderHistoryNotFoundException;
import com.pwr.psiw.orderhistoryservice.model.OrderHistory;
import com.pwr.psiw.orderhistoryservice.model.OrderHistoryModel;
import com.pwr.psiw.orderhistoryservice.repository.OrderHistoryRepository;
import com.pwr.psiw.orderhistoryservice.utils.PageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderHistoryServiceImpTest {

    @Mock
    private OrderHistoryRepository orderHistoryRepository;

    @Mock
    private OrderHistoryModelAssembler assembler;

    @InjectMocks
    private OrderHistoryServiceImp orderHistoryService;

    private OrderHistory sampleOrderHistory;
    private OrderHistoryModel sampleModel;

    @BeforeEach
    void setUp() {
        sampleOrderHistory = OrderHistory.builder()
                .id(1L)
                .customerName("John Doe")
                .deliveryStatus("DELIVERED")
                .productName("Laptop")
                .totalPrice(new BigDecimal("1200.50"))
                .build();

        sampleModel = new OrderHistoryModel(sampleOrderHistory);
    }

    @Test
    void findAll_ShouldReturnPagedResultsWithHateoasLinks() {
        // Given
        Pageable pageable = PageRequest.of(0, 2);
        List<OrderHistory> orderHistories = List.of(sampleOrderHistory);
        Page<OrderHistory> page = new PageImpl<>(orderHistories, pageable, orderHistories.size());

        // When
        when(orderHistoryRepository.findAll(pageable)).thenReturn(page);
        when(assembler.toModel(sampleOrderHistory, 0, 2)).thenReturn(sampleModel);
        PageResponse<OrderHistoryModel> response = orderHistoryService.findAll(0, 2);

        // Then
        assertEquals(0, response.currentPage());
        assertEquals(2, response.pageSize());
        assertEquals(1, response.totalPages());
        assertFalse(response.hasNext());
        assertTrue(response.last());
        assertEquals(1, response.content().size());
        assertEquals(sampleModel, response.content().getFirst());

        verify(orderHistoryRepository, times(1)).findAll(pageable);
        verify(assembler, times(1)).toModel(sampleOrderHistory, 0, 2);
    }

    @Test
    void findById_ShouldReturnOrderHistory_WhenExists() {
        when(orderHistoryRepository.findById(1L)).thenReturn(Optional.of(sampleOrderHistory));

        OrderHistory result = orderHistoryService.findById(1L);

        assertNotNull(result);
        assertEquals(sampleOrderHistory.getId(), result.getId());
        verify(orderHistoryRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        when(orderHistoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderHistoryNotFoundException.class, () -> orderHistoryService.findById(1L));
        verify(orderHistoryRepository, times(1)).findById(1L);
    }

    @Test
    void save_ShouldSaveOrderHistory() {
        when(orderHistoryRepository.save(sampleOrderHistory)).thenReturn(sampleOrderHistory);

        OrderHistory saved = orderHistoryService.save(sampleOrderHistory);

        assertNotNull(saved);
        assertEquals(sampleOrderHistory.getId(), saved.getId());
        verify(orderHistoryRepository, times(1)).save(sampleOrderHistory);
    }

    @Test
    void update_ShouldUpdateOrderHistoryStatus() {
        when(orderHistoryRepository.findById(1L)).thenReturn(Optional.of(sampleOrderHistory));
        when(orderHistoryRepository.save(any(OrderHistory.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OrderHistory updated = orderHistoryService.update(1L, "SHIPPED");

        assertEquals("SHIPPED", updated.getDeliveryStatus());
        verify(orderHistoryRepository, times(1)).findById(1L);
        verify(orderHistoryRepository, times(1)).save(sampleOrderHistory);
    }

    @Test
    void update_ShouldThrowException_WhenOrderNotFound() {
        when(orderHistoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderHistoryNotFoundException.class, () -> orderHistoryService.update(1L, "SHIPPED"));

        verify(orderHistoryRepository, times(1)).findById(1L);
        verify(orderHistoryRepository, never()).save(any(OrderHistory.class));
    }
}
