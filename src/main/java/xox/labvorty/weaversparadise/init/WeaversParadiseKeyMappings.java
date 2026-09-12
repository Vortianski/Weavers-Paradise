package xox.labvorty.weaversparadise.init;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.glfw.GLFW;
import xox.labvorty.weaversparadise.data.network.OpenUpperWearMessage;
import xox.labvorty.weaversparadise.data.network.TrinketSoundMessage;
import xox.labvorty.weaversparadise.client.WPClientNetwork;

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
                WPClientNetwork.sendToServer(new OpenUpperWearMessage(0, 0));
                OPEN_UPPER_WEAR_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown && !isDown) {
                int dt = (int) (System.currentTimeMillis() - OPEN_UPPER_WEAR_LASTPRESS);
                WPClientNetwork.sendToServer(new OpenUpperWearMessage(1, dt));
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
                    WPClientNetwork.sendToServer(new TrinketSoundMessage(player.getX(), player.getY(), player.getZ()));
                }
            }

            isDownOld = isDown;
        }
    };
}
