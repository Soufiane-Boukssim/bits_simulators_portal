package com.simulator.globalService.atm;

import com.simulator.entities.atm.NdcDisplayLayout;
import com.simulator.entities.atm.NdcDisplayLayoutId;
import com.simulator.repository.atm.NdcDisplayLayoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NdcDisplayLayoutService {

	@Autowired
	private NdcDisplayLayoutRepository ndcDisplayLayoutRepository;
	private static final Logger logger = LoggerFactory.getLogger(NdcDisplayLayoutService.class);

	public void saveNewNdcDisplayLayout(NdcDisplayLayout ndcDisplayLayout) {
		logger.info("Starting saveNewNdcDisplayLayout method");
		ndcDisplayLayoutRepository.save(ndcDisplayLayout);
		logger.info("Completed saveNewNdcDisplayLayout method");
	}

	public List<NdcDisplayLayout> getAllNdcDisplayLayout() {
		logger.info("Starting getAllNdcDisplayLayout method");
		List<NdcDisplayLayout> result = ndcDisplayLayoutRepository.findAll();
		logger.info("Completed getAllNdcDisplayLayout method");
		return result;
	}

	public void updateNdcDisplayLayout(NdcDisplayLayout updatedNdcDisplayLayout, NdcDisplayLayoutId id) {
		logger.info("Starting updateNdcDisplayLayout method");
		NdcDisplayLayout existingNdcDisplayLayout = ndcDisplayLayoutRepository.findById(id).get();
		updatedNdcDisplayLayout.setId(id);
		existingNdcDisplayLayout = updatedNdcDisplayLayout;
		ndcDisplayLayoutRepository.save(existingNdcDisplayLayout);
		logger.info("Completed updateNdcDisplayLayout method");
	}

	public void deleteNdcDisplayLayout(NdcDisplayLayoutId id) {
		logger.info("Starting deleteNdcDisplayLayout method");
		ndcDisplayLayoutRepository.deleteById(id);
		logger.info("Completed deleteNdcDisplayLayout method");
	}
}