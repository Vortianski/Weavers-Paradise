package xox.labvorty.weaversparadise.init;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.network.OpenUpperWearMessage;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaversParadiseKeyMappings {
    public static KeyMapping MINIGAME_UP_BUTTON = new KeyMapping("weaversparadise.keybind.up_minigame", GLFW.GLFW_KEY_W, "key.categories.weaversparadise"){};
    public static KeyMapping MINIGAME_DOWN_BUTTON = new KeyMapping("weaversparadise.keybind.down_minigame", GLFW.GLFW_KEY_S, "key.categories.weaversparadise"){};
    public static KeyMapping MINIGAME_LEFT_BUTTON = new KeyMapping("weaversparadise.keybind.left_minigame", GLFW.GLFW_KEY_A, "key.categories.weaversparadise"){};
    public static KeyMapping MINIGAME_RIGHT_BUTTON = new KeyMapping("weaversparadise.keybind.right_minigame", GLFW.GLFW_KEY_D, "key.categories.weaversparadise"){};
    public static KeyMapping OPEN_UPPER_WEAR_BUTTON = new KeyMapping("weaversparadise.keybind.open_upper_wear", GLFW.GLFW_KEY_LEFT_BRACKET, "key.categories.weaversparadise"){
        private boolean isDownOld = false;
        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                WeaversParadiseMod.PACKET_HANDLER.sendToServer(new OpenUpperWearMessage(0, 0));
                //OpenUpperWearMessage.pressAction(Minecraft.getInstance().player, 0, 0);
                OPEN_UPPER_WEAR_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown && !isDown) {
                int dt = (int) (System.currentTimeMillis() - OPEN_UPPER_WEAR_LASTPRESS);
                WeaversParadiseMod.PACKET_HANDLER.sendToServer(new OpenUpperWearMessage(1, dt));
                //OpenUpperWearMessage.pressAction(Minecraft.getInstance().player, 1, dt);
            }
            isDownOld = isDown;
        }
    };
    private static long OPEN_UPPER_WEAR_LASTPRESS = 0;

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(MINIGAME_UP_BUTTON);
        event.register(MINIGAME_DOWN_BUTTON);
        event.register(MINIGAME_LEFT_BUTTON);
        event.register(MINIGAME_RIGHT_BUTTON);
        event.register(OPEN_UPPER_WEAR_BUTTON);
    }

    @Mod.EventBusSubscriber
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.ClientTickEvent.Phase.END) {
                OPEN_UPPER_WEAR_BUTTON.consumeClick();
            }
        }
    }
}
