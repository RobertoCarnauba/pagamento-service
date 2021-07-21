package com.carnauba.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.carnauba.data.vo.ProdutoVO;
import com.carnauba.repository.ProdutoRepository;

@Component
public class ProdutoReceiveMessage {
	
	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoReceiveMessage(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload ProdutoVO produtoVO ) {
		produtoRepository.save(ProdutoVO.create(produtoVO));
	}
	
	

}
