package com.lilithsthrone.game.sex.managers.submission;

import java.util.ArrayList;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;

/**
 * @since 0.2.7
 * @version 0.2.7
 * @author Innoxia
 */
public class SMBreedingStallFront extends SexManagerDefault {

	public SMBreedingStallFront(boolean vaginalAllowed, boolean analAllowed, boolean oralAllowed, Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.BREEDING_STALL_FRONT,
				dominants,
				submissives);
		
		for(GameCharacter character : submissives.keySet()) {
			orificesBannedMap.put(character, new ArrayList<>());
		}
		
		if(!vaginalAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				orificesBannedMap.get(character).add(OrificeType.VAGINA);
			}
		}
		
		if(!analAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				orificesBannedMap.get(character).add(OrificeType.ANUS);
			}
		}
		
		if(!oralAllowed) {
			for(GameCharacter character : submissives.keySet()) {
				orificesBannedMap.get(character).add(OrificeType.MOUTH);
			}
		}
	}

	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return getDominants().containsKey(character);
	}
	
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character){
		return getDominants().containsKey(character);
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}
}
