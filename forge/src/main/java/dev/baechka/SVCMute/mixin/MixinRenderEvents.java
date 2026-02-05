package dev.baechka.SVCMute.mixin;

import de.maxhenkel.voicechat.VoicechatClient;
import de.maxhenkel.voicechat.voice.client.ClientManager;
import de.maxhenkel.voicechat.voice.client.ClientPlayerStateManager;
import de.maxhenkel.voicechat.voice.client.GroupChatManager;
import de.maxhenkel.voicechat.voice.client.RenderEvents;
import dev.baechka.SVCMute.MuteStateManager;
import dev.baechka.SVCMute.SVCMute;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderEvents.class)
public abstract class MixinRenderEvents {

    @Unique
    private static final ResourceLocation MUTE_ICON = new ResourceLocation(SVCMute.MODID, "textures/icons/mute.png");

    @Shadow(remap = false)
    private void renderIcon(GuiGraphics guiGraphics, ResourceLocation texture) {
        throw new AssertionError();
    }

    @Inject(method = "onRenderHUD", at = @At("HEAD"), cancellable = true, remap = false)
    private void onRenderHUD(GuiGraphics guiGraphics, float tickDelta, CallbackInfo ci) {
        if (VoicechatClient.CLIENT_CONFIG.hideIcons.get() || !VoicechatClient.CLIENT_CONFIG.showHudIcons.get()) {
            return;
        }

        ClientPlayerStateManager manager = ClientManager.getPlayerStateManager();
        if (manager == null || manager.isDisconnected() || manager.isDisabled()) {
            return;
        }

        if (MuteStateManager.INSTANCE.isServerMuted()) {
            renderIcon(guiGraphics, MUTE_ICON);

            if (manager.getGroupID() != null && VoicechatClient.CLIENT_CONFIG.showGroupHud.get()) {
                GroupChatManager.renderIcons(guiGraphics);
            }

            ci.cancel();
        }
    }
}
