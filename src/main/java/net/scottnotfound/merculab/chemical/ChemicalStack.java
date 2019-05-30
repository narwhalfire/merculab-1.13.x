package net.scottnotfound.merculab.chemical;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.registries.IRegistryDelegate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

public final class ChemicalStack extends CapabilityProvider<ChemicalStack> {

    private static final Logger LOGGER = LogManager.getLogger();

    private int amount;
    private NBTTagCompound tag;
    private NBTTagCompound capNBT;
    private IRegistryDelegate<Chemical> chemicalDelegate;

    public ChemicalStack(@Nonnull Chemical chemical, int amount) {
        super(ChemicalStack.class);
    }

}
