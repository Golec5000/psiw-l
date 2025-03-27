package com.pwr.psiw.orderhistoryservice.configuration;

import com.pwr.psiw.orderhistoryservice.controller.OrderHistoryController;
import com.pwr.psiw.orderhistoryservice.model.OrderHistory;
import com.pwr.psiw.orderhistoryservice.model.OrderHistoryModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderHistoryModelAssembler extends RepresentationModelAssemblerSupport<OrderHistory, OrderHistoryModel> {

    public OrderHistoryModelAssembler() {
        super(OrderHistoryController.class, OrderHistoryModel.class);
    }

    public OrderHistoryModel toModel(OrderHistory entity, int pageNo, int pageSize) {
        OrderHistoryModel model = new OrderHistoryModel(entity);

        model.add(linkTo(methodOn(OrderHistoryController.class).getOrderById(entity.getId())).withSelfRel());
        model.add(linkTo(methodOn(OrderHistoryController.class).getAllOrders(pageNo, pageSize)).withRel("orders"));

        return model;
    }

    @Override
    public OrderHistoryModel toModel(OrderHistory entity) {
        // Default method implementation
        return toModel(entity, 0, 10);
    }
}
