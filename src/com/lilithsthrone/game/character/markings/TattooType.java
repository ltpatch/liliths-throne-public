package com.lilithsthrone.game.character.markings;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.utils.ColourListPresets;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class TattooType {

	public static AbstractTattooType NONE = new AbstractTattooType(
			"none",
			"none",
			"This tattoo has no particular pattern, and simply displays either writing or a counter.",
			ColourListPresets.JUST_GREY.getPresetColourList(),
			null,
			null,
			null);
	
	public static AbstractTattooType FLOWERS = new AbstractTattooType(
			"flowers",
			"flowers",
			"This tattoo depicts a flowing series of intertwined flowers.",
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null);

	public static AbstractTattooType TRIBAL = new AbstractTattooType(
			"tribal",
			"tribal",
			"A series of flowing lines and intricate patterns.",
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null);

	public static AbstractTattooType BUTTERFLIES = new AbstractTattooType(
			"butterflies",
			"butterflies",
			"An artistic depiction of a trio of butterflies in mid-flight.",
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			null);
	
	public static AbstractTattooType LINES = new AbstractTattooType(
			"lines",
			"lines",
			"A series of flowing, swirling lines.",
			ColourListPresets.ALL.getPresetColourList(),
			null,
			null,
			null);
	
	private static Map<AbstractTattooType, String> tattooToIdMap = new HashMap<>();
	private static Map<String, AbstractTattooType> idToTattooMap = new HashMap<>();
	
	public static AbstractTattooType getTattooTypeFromId(String id) {
		return idToTattooMap.get(id);
	}
	
	public static String getIdFromTattooType(AbstractTattooType tattooType) {
		return tattooToIdMap.get(tattooType);
	}
	
	public static List<AbstractTattooType> getAllTattooTypes() {
		List<AbstractTattooType> allTattoos = new ArrayList<>(tattooToIdMap.keySet());
		
		allTattoos.sort((t1, t2) -> t1.equals(TattooType.NONE)?-1:(t1.getName().compareTo(t2.getName())));
		
		return allTattoos;
	}
	
	static {
		
		File dir = new File("res/mods");
		
		if (dir.exists() && dir.isDirectory()) {
			File[] modDirectoryListing = dir.listFiles();
			if (modDirectoryListing != null) {
				for (File modAuthorDirectory : modDirectoryListing) {
					File modAuthorClothingDirectory = new File(modAuthorDirectory.getAbsolutePath()+"/items/tattoos");
					
					File[] clothingDirectoriesListing = modAuthorClothingDirectory.listFiles();
					if (clothingDirectoriesListing != null) {
						for (File clothingDirectory : clothingDirectoriesListing) {
							if (clothingDirectory.isDirectory()){
								File[] innerDirectoryListing = clothingDirectory.listFiles((path, filename) -> filename.endsWith(".xml"));
								if (innerDirectoryListing != null) {
									for (File innerChild : innerDirectoryListing) {
										AbstractTattooType ct = new AbstractTattooType(innerChild) {};
										String id = modAuthorDirectory.getName()+"_"+innerChild.getParentFile().getName()+"_"+innerChild.getName().split("\\.")[0];
//										System.out.println(id);
										tattooToIdMap.put(ct, id);
										idToTattooMap.put(id, ct);
									}
								}
							}
						}
					}
				}
			}
		}
		
		Field[] fields = TattooType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractTattooType.class.isAssignableFrom(f.getType())) {
				
				AbstractTattooType ct;
				try {
					ct = ((AbstractTattooType) f.get(null));

					// I feel like this is stupid :thinking:
					tattooToIdMap.put(ct, f.getName());
					idToTattooMap.put(f.getName(), ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
}
