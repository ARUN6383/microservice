package com.microservice.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.microservice.orderservice.dto.OrderLineItemsDto;
import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.model.Order;
import com.microservice.orderservice.model.OrderLineItems;
import com.microservice.orderservice.repo.OrderRepository;

import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class OrderService {

	private OrderRepository orderRepository;

	public void placeOrder(OrderRequest orderRequest)
	{
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> listOfOrderLine  = orderRequest.getOrderLineItemsDtoList()
				.stream()
				.map(this :: mapToDto)
				.toList();
		
		order.setOrderLineItemsList(listOfOrderLine);
		
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemDto){
		OrderLineItems orderLineItem = new OrderLineItems();
		orderLineItem.setPrice(orderLineItemDto.getPrice());
		orderLineItem.setQuantity(orderLineItemDto.getQuantity());
		orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());	
		return orderLineItem;
	}
	{
 	}
}
