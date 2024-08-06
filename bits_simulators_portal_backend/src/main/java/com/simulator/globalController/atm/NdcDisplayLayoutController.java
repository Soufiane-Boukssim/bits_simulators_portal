package com.simulator.globalController.atm;

import com.simulator.entities.atm.NdcDisplayLayout;
import com.simulator.entities.atm.NdcDisplayLayoutId;
import com.simulator.globalService.atm.NdcDisplayLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NdcDisplayLayoutController {

	@Autowired
	private NdcDisplayLayoutService ndcDisplayLayoutService;

	@PostMapping("/ndcDisplayLayout")
	public void saveNdcDisplayLayout(@RequestBody NdcDisplayLayout ndcDisplayLayout) {
		ndcDisplayLayoutService.saveNewNdcDisplayLayout(ndcDisplayLayout);
	}

	@GetMapping("/ndcDisplayLayout")
	public List<NdcDisplayLayout> getAllNdcDisplayLayout() {
		return ndcDisplayLayoutService.getAllNdcDisplayLayout();
	}

	@PutMapping("/ndcDisplayLayout/{_profileCode}/{_screenNumber}")
	public void updateNdcDisplayLayout(@RequestBody NdcDisplayLayout ndcDisplayLayout,
			@PathVariable String _profileCode, @PathVariable String _screenNumber) {
		NdcDisplayLayoutId id = new NdcDisplayLayoutId();
		id.setProfileCode(_profileCode);
		id.setScreenNumber(_screenNumber);
		ndcDisplayLayout.setId(id);
		ndcDisplayLayoutService.updateNdcDisplayLayout(ndcDisplayLayout, id);
	}

	@DeleteMapping("/ndcDisplayLayout")
	public String deleteNdcDisplayLayout(@RequestBody NdcDisplayLayoutId id) {
		ndcDisplayLayoutService.deleteNdcDisplayLayout(id);
		return "Deleted Successfully";
	}
}