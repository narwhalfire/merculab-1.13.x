package net.scottnotfound.merculab.chemical;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.registries.IRegistryDelegate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

public class ChemicalStack {

    private static final Logger LOGGER = LogManager.getLogger();

    public int amount;
    public NBTTagCompound tag;
    private IRegistryDelegate<Chemical> chemicalDelegate;

    public ChemicalStack(@Nonnull Chemical chemical, int amount) {

    }


}
