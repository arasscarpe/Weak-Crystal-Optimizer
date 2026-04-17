package net.crystal.bounce.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.EndCrystalItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow protected int itemUseCooldown;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        if (client.player != null) {
            boolean holdingCrystal = client.player.getMainHandStack().getItem() instanceof EndCrystalItem || 
                                     client.player.getOffHandStack().getItem() instanceof EndCrystalItem;
            
            if (holdingCrystal) {
                itemUseCooldown = 0;
            }
        }
    }
}