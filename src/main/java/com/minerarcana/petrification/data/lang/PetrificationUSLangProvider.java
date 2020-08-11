package com.minerarcana.petrification.data.lang;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;

import static com.minerarcana.petrification.Petrification.MOD_ID;
import static com.minerarcana.petrification.content.PetrificationEntities.COCKATRICE;
import static com.minerarcana.petrification.content.PetrificationItems.ITEMLIST;

public class PetrificationUSLangProvider extends LanguageProvider {
    public PetrificationUSLangProvider(DataGenerator gen) {
        super(gen, MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        for(RegistryObject<Item> object: ITEMLIST){
            if(object != null){
                this.addItem(object, WordUtils.capitalizeFully(object.getId().getPath().replace("_", " ")));
            }
        }

        this.addEntityType(COCKATRICE,"Cockatrice");
        this.add("itemGroup.petrification","Petrification");
    }
}
