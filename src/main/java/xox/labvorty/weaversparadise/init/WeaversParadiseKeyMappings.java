package xox.labvorty.weaversparadise.init;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;
import xox.labvorty.weaversparadise.data.network.OpenUpperWearMessage;
import xox.labvorty.weaversparadise.data.network.TrinketSoundMessage;

@EventBusSubscriber(value = Dist.CLIENT)
public class WeaversParadiseKeyMappings {
    public static KeyMapping MINIGAME_UP_BUTTON = new KeyMapping("weaversparadise.keybind.up_minigame", GLFW.GLFW_KEY_W, "key.categories.weaversparadise");
    public static KeyMapping MINIGAME_DOWN_BUTTON = new KeyMapping("weaversparadise.keybind.down_minigame", GLFW.GLFW_KEY_S, "key.categories.weaversparadise");
    public static KeyMapping MINIGAME_LEFT_BUTTON = new KeyMapping("weaversparadise.keybind.left_minigame", GLFW.GLFW_KEY_A, "key.categories.weaversparadise");
    public static KeyMapping MINIGAME_RIGHT_BUTTON = new KeyMapping("weaversparadise.keybind.right_minigame", GLFW.GLFW_KEY_D, "key.categories.weaversparadise");
    public static KeyMapping OPEN_UPPER_WEAR_BUTTON = new KeyMapping("weaversparadise.keybind.open_upper_wear", GLFW.GLFW_KEY_LEFT_BRACKET, "key.categories.weaversparadise"){
        private boolean isDownOld = false;
        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);

            if (isDownOld != isDown && isDown) {
                PacketDistributor.sendToServer(new OpenUpperWearMessage(0, 0));
                OPEN_UPPER_WEAR_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown && !isDown) {
                int dt = (int) (System.currentTimeMillis() - OPEN_UPPER_WEAR_LASTPRESS);
                PacketDistributor.sendToServer(new OpenUpperWearMessage(1, dt));
            }

            isDownOld = isDown;
        }
    };
    private static long OPEN_UPPER_WEAR_LASTPRESS = 0;
    public static KeyMapping TRINKET_SOUND = new KeyMapping("weaversparadise.keybind.trinket_sound", GLFW.GLFW_KEY_X, "key.categories.weaversparadise") {
        private boolean isDownOld = false;
        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);

            if (isDownOld != isDown && isDown) {
                Minecraft minecraft = Minecraft.getInstance();
                Player player = minecraft.player;
                if (player != null) {
                    PacketDistributor.sendToServer(new TrinketSoundMessage(player.getX(), player.getY(), player.getZ()));
                }
            }

            isDownOld = isDown;
        }
    };

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(MINIGAME_UP_BUTTON);
        event.register(MINIGAME_DOWN_BUTTON);
        event.register(MINIGAME_LEFT_BUTTON);
        event.register(MINIGAME_RIGHT_BUTTON);
        event.register(OPEN_UPPER_WEAR_BUTTON);
        event.register(TRINKET_SOUND);
    }

    @EventBusSubscriber
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
            OPEN_UPPER_WEAR_BUTTON.consumeClick();
            TRINKET_SOUND.consumeClick();
        }
    }
}
