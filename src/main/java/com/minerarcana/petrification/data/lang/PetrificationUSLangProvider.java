package com.minerarcana.petrification.data.lang;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;

import static com.minerarcana.petrification.Petrification.MOD_ID;
import static com.minerarcana.petrification.content.PetrificationEntities.COCKATRICE;
import static com.minerarcana.petrification.content.PetrificationItems.ITEMLIST;
import static com.minerarcana.petrification.content.PetrificationItems.POTIONLIST;

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

        for(RegistryObject<Item> object: POTIONLIST){
            if(object != null){
                this.addItem(object, WordUtils.capitalizeFully(object.getId().getPath().replace("_0","").replace("_", " ")).replace("1","II").replace("2","III").replace("3","IV").replace("4","V").replace("5","VI").replace("6","VII").replace("7","VIII").replace("8","IX").replace("9","X"));
            }
        }

        this.addEntityType(COCKATRICE,"Cockatrice");
        this.add("itemGroup.petrification","Petrification");
        this.add("potion.potency.6","VII");
        this.add("potion.potency.7","VIII");
        this.add("potion.potency.8","IX");
        this.add("potion.potency.9","X");
        this.add("effect.petrification.petrification","Petrification");
        this.add("effect.petrification.revivify","Revivify");
    }
}
