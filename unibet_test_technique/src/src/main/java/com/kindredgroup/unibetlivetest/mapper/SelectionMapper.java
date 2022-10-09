package com.kindredgroup.unibetlivetest.mapper;

import java.util.ArrayList;
import java.util.List;

import com.kindredgroup.unibetlivetest.dto.SelectionDto;
import com.kindredgroup.unibetlivetest.entity.Selection;

public class SelectionMapper {


	public static  List<SelectionDto> mapSelectionsToSelectionsDto(List<Selection> selections){
		List<SelectionDto> selectionsDto = new ArrayList<SelectionDto>();
		if(selections != null) {
			for(Selection selection: selections) {
				selectionsDto.add(mapSelectionToSelectionDto(selection));
			}
		}
		return selectionsDto;
	}

	public static SelectionDto mapSelectionToSelectionDto(Selection selection){
		SelectionDto selectionDto = new SelectionDto();
		selectionDto.setId(selection.getId());
		selectionDto.setName(selection.getName());
		selectionDto.setState(selection.getState());
		selectionDto.setResult(selection.getResult());
		selectionDto.setCurrentOdd(selection.getCurrentOdd());
		selectionDto.setMarketId(selection.getMarket().getId());
		return selectionDto;
	}
}
