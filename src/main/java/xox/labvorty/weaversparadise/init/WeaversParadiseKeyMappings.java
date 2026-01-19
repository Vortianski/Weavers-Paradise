package xox.labvorty.weaversparadise.init;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;
import xox.labvorty.weaversparadise.data.keybinds.DownMinigameButtonMessage;
import xox.labvorty.weaversparadise.data.keybinds.LeftMinigameButtonMessage;
import xox.labvorty.weaversparadise.data.keybinds.RightMinigameButtonMessage;
import xox.labvorty.weaversparadise.data.keybinds.UpMinigameButtonMessage;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class WeaversParadiseKeyMappings {
    public static KeyMapping MINIGAME_UP_BUTTON = new KeyMapping("weaversparadise.keybind.up_minigame", GLFW.GLFW_KEY_W, "key.categories.weaversparadise"){
        private boolean isDownOld = false;
        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                PacketDistributor.sendToServer(new UpMinigameButtonMessage(0, 0));
                UpMinigameButtonMessage.pressAction(Minecraft.getInstance().player, 0, 0);
                UP_MINIGAME_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown && !isDown) {
                int dt = (int) (System.currentTimeMillis() - UP_MINIGAME_LASTPRESS);
                PacketDistributor.sendToServer(new UpMinigameButtonMessage(1, dt));
                UpMinigameButtonMessage.pressAction(Minecraft.getInstance().player, 1, dt);
            }
            isDownOld = isDown;
        }
    };
    private static long UP_MINIGAME_LASTPRESS = 0;

    public static KeyMapping MINIGAME_DOWN_BUTTON = new KeyMapping("weaversparadise.keybind.down_minigame", GLFW.GLFW_KEY_S, "key.categories.weaversparadise"){
        private boolean isDownOld = false;
        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                PacketDistributor.sendToServer(new DownMinigameButtonMessage(0, 0));
                DownMinigameButtonMessage.pressAction(Minecraft.getInstance().player, 0, 0);
                DOWN_MINIGAME_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown && !isDown) {
                int dt = (int) (System.currentTimeMillis() - DOWN_MINIGAME_LASTPRESS);
                PacketDistributor.sendToServer(new DownMinigameButtonMessage(1, dt));
                DownMinigameButtonMessage.pressAction(Minecraft.getInstance().player, 1, dt);
            }
            isDownOld = isDown;
        }
    };
    private static long DOWN_MINIGAME_LASTPRESS = 0;

    public static KeyMapping MINIGAME_LEFT_BUTTON = new KeyMapping("weaversparadise.keybind.left_minigame", GLFW.GLFW_KEY_A, "key.categories.weaversparadise"){
        private boolean isDownOld = false;
        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                PacketDistributor.sendToServer(new LeftMinigameButtonMessage(0, 0));
                LeftMinigameButtonMessage.pressAction(Minecraft.getInstance().player, 0, 0);
                LEFT_MINIGAME_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown && !isDown) {
                int dt = (int) (System.currentTimeMillis() - LEFT_MINIGAME_LASTPRESS);
                PacketDistributor.sendToServer(new LeftMinigameButtonMessage(1, dt));
                LeftMinigameButtonMessage.pressAction(Minecraft.getInstance().player, 1, dt);
            }
            isDownOld = isDown;
        }
    };
    private static long LEFT_MINIGAME_LASTPRESS = 0;

    public static KeyMapping MINIGAME_RIGHT_BUTTON = new KeyMapping("weaversparadise.keybind.right_minigame", GLFW.GLFW_KEY_D, "key.categories.weaversparadise"){
        private boolean isDownOld = false;
        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                PacketDistributor.sendToServer(new RightMinigameButtonMessage(0, 0));
                RightMinigameButtonMessage.pressAction(Minecraft.getInstance().player, 0, 0);
                RIGHT_MINIGAME_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown && !isDown) {
                int dt = (int) (System.currentTimeMillis() - RIGHT_MINIGAME_LASTPRESS);
                PacketDistributor.sendToServer(new RightMinigameButtonMessage(1, dt));
                RightMinigameButtonMessage.pressAction(Minecraft.getInstance().player, 1, dt);
            }
            isDownOld = isDown;
        }
    };
    private static long RIGHT_MINIGAME_LASTPRESS = 0;

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(MINIGAME_UP_BUTTON);
        event.register(MINIGAME_DOWN_BUTTON);
        event.register(MINIGAME_LEFT_BUTTON);
        event.register(MINIGAME_RIGHT_BUTTON);
    }

    @EventBusSubscriber
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
            //MINIGAME_UP_BUTTON.consumeClick();
            //MINIGAME_DOWN_BUTTON.consumeClick();
            //MINIGAME_LEFT_BUTTON.consumeClick();
            //MINIGAME_RIGHT_BUTTON.consumeClick();
        }
    }
}
