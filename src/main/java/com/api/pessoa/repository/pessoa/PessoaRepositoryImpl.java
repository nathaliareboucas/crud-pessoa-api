package com.api.pessoa.repository.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.api.pessoa.model.Pessoa;
import com.api.pessoa.model.Pessoa_;
import com.api.pessoa.repository.filter.PessoaFilter;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Pessoa> pesquisar(PessoaFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(builder, root, filter);
		criteria.where(predicates);
		TypedQuery<Pessoa> query = manager.createQuery(criteria);
		paginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, count(filter));
	}

	private Long count(PessoaFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(builder, root, filter);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	private void paginacao(TypedQuery<Pessoa> query, Pageable pageable) {
		int atual = pageable.getPageNumber();
		int qtdPorPagina = pageable.getPageSize();
		int primeiroDaPagina = atual * qtdPorPagina;
		
		query.setFirstResult(primeiroDaPagina);
		query.setMaxResults(qtdPorPagina);
	}

	private Predicate[] criarRestricoes(CriteriaBuilder builder, Root<Pessoa> root, PessoaFilter filter) {
		List<Predicate> restricoes = new ArrayList<>();
		
		if (!StringUtils.isEmpty(filter.getNome()))
			restricoes.add(builder.like(builder.lower(root.get(Pessoa_.nome)), "%" + filter.getNome() + "%"));
		if (!StringUtils.isEmpty(filter.getCpf()))
			restricoes.add(builder.equal(root.get(Pessoa_.cpf), filter.getCpf()));
		
		return restricoes.toArray(new Predicate[restricoes.size()]);
	}

}
