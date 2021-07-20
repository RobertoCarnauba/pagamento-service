package com.carnauba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carnauba.data.vo.VendaVO;
import com.carnauba.entity.ProdutoVenda;
import com.carnauba.entity.Venda;
import com.carnauba.exception.ResourceNotFoundException;
import com.carnauba.repository.ProdutoVendaRepository;
import com.carnauba.repository.VendaRepository;

import lombok.var;

@Service
public class VendaService {

	private final VendaRepository vendaRepository;
	private final ProdutoVendaRepository produtoVendaRepository;

	public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
		super();
		this.vendaRepository = vendaRepository;
		this.produtoVendaRepository = produtoVendaRepository;
	}

	public VendaVO create(VendaVO vendaVO) {
		Venda venda = vendaRepository.save(Venda.create(vendaVO));
		
		List<ProdutoVenda> produtosSalvos = new ArrayList<>();
		vendaVO.getProdutos().forEach(p -> {
			ProdutoVenda produtoVenda = ProdutoVenda.create(p);
			produtoVenda.setVenda(venda);
			produtosSalvos.add(produtoVendaRepository.save(produtoVenda));
		});
		venda.setProdutos(produtosSalvos);
		return VendaVO.create(venda);
	}

	public Page<VendaVO> findAll(Pageable pageable) {
		var page = vendaRepository.findAll(pageable);
		return page.map(this::convertToProdutoVO);
	}

	private VendaVO convertToProdutoVO(Venda venda) {
		return VendaVO.create(venda);
	}
	
	public VendaVO findById(Long id) {
		var entity = vendaRepository.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("not found resource"));
        return VendaVO.create(entity);
	}
		
		
		
		
}
